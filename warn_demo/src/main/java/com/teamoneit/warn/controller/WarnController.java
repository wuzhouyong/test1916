package com.teamoneit.warn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teamoneit.warn.common.utils.HttpClientUtils;
import com.teamoneit.warn.entity.JsonResult;
import com.teamoneit.warn.entity.WarnParam;
import com.teamoneit.warn.entity.WarnResult;
import com.teamoneit.warn.entity.WarnStatus;
import com.teamoneit.warn.service.IWarnService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/teamone/warn")
@Slf4j
public class WarnController {

    private static final Logger file_response = LoggerFactory.getLogger("file_response");

    private RestTemplate restTemplate;

    private HttpHeaders headers;

    @Autowired
    private IWarnService service;

    @PostMapping("/list_temp")
    public JsonResult getWarnList(@PathVariable String prefix, Page<WarnResult> page, @RequestBody WarnParam param) {
        param.setTagHeadId("1012");
        IPage<WarnResult> list = service.getWarnList(prefix, page, param);
        return JsonResult.success(list);
    }

    @GetMapping("/list")
    public JsonResult getWarnListByTag() { //推送，一个分库推送到结束，在执行另一个分库，一次性推送20条
        int total = 0, res, retu = 0;
        for (Map.Entry<String, String> entry : getMap().entrySet()) {
            IPage<WarnResult> page = service.getWarnListByTag(entry.getKey(), entry.getValue(), new Page<>(1, 20));
            if (page.getTotal() != 0) {
                long pages = page.getPages();
                for (int i = 1; i <= pages; i++) {
                    page = service.getWarnListByTag(entry.getKey(), entry.getValue(), new Page<>(i, 20));
                    List<WarnResult> warnResultList = page.getRecords();
                    if (warnResultList == null || warnResultList.size() <= 0) continue;
                    res = uploadData(warnResultList);
                    if (res == 0) continue;
                    total += res;
                }
            }
            retu += total;
            try {
                Thread.sleep(1 * 1000);//睡眠1秒
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
        return JsonResult.success("成功推送异常任务" + retu + "条");
    }

    @Transactional
    public int uploadData(List<WarnResult> warnResultList) {//一次性操作20条
        int count = 0;
        if (warnResultList != null && warnResultList.size() > 0) {
            warnResultList = warnResultList.stream().collect(Collectors.collectingAndThen(
                    Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(WarnResult::getTaskId))), ArrayList::new));
            for (WarnResult result : warnResultList) {
                if (result.getLevel1() != null) result.setLevel(result.getLevel1());
                if (result.getLatitude() != null) result.setLatitude(new BigDecimal(0));
                if (result.getLongitude() != null) result.setLongitude(new BigDecimal(0));
            }
        }
        String token = getAccessToken();
        JSONObject jsonObject = getTransfer(warnResultList, token);
        if ("leanworkflow-40101".equals(jsonObject.getString("code"))) {
            token = getAccessToken();
            jsonObject = getTransfer(warnResultList, token);
        }
        if ("leanworkflow-40001".equals(jsonObject.getString("code"))) { //参数不对，记录下来，下次重新发送
            String message = "{\"state\":\"2\",\"message\":\"参数格式不对\"}";
            jsonObject = JSONObject.parseObject(message);
        }

        if ("leanworkflow-50000".equals(jsonObject.getString("code"))) { //参数为null，重新赋值，然后在发送
            assert warnResultList != null;
            warnResultList.forEach(this::setValue);
            jsonObject = getTransfer(warnResultList, token);
        }
        String message = jsonObject.getString("message");
        if (jsonObject.getInteger("state") == 1 || message.contains("任务已存在")) {
            warnResultList.stream().forEach(x -> x.setLevel1(1));
            List<String> collect = warnResultList.stream().map(WarnResult::getTaskId).collect(toList());
            //如果数据库中已经存在，则查找出来，不用添加
            List<String> taskIds = service.selectWarnStatusList(collect);
            if (taskIds != null && taskIds.size() > 0) {
                //数据库中存在，假如发送过来20条，数据库中找到13条(交集)，则添加另外7条数据，20条中取数据库中不存在的数据
                List<WarnResult> results = warnResultList.stream().filter(x -> !taskIds.contains(x.getTaskId())).collect(toList());
                if (results == null || results.size() <= 0) return count;
                count = service.insertWarnStatus(results);//推送成功，记录到数据库中
                return count;
            }
            count = service.insertWarnStatus(warnResultList);//推送成功，记录到数据库中
        }
        return count;
    }

    public JSONObject getTransfer(List<WarnResult> warnStatusList, String token) {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<List<WarnResult>> httpEntity = new HttpEntity<>(warnStatusList, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://19.104.43.66:8014/api/leanworkflow/v1/task-info", httpEntity, String.class);
        file_response.warn("推送信息:" + response.getBody());
        return JSONObject.parseObject(response.getBody());
    }

    @GetMapping("/update")
    public JsonResult updateState() {//修改状态，一个分库推送到结束，在执行另一个分库，只能一条一条修改
        int total = 0, res = 0, retu = 0;
        for (Map.Entry<String, String> entry : getMap().entrySet()) {
            IPage<WarnStatus> page = service.getWarnListByState(entry.getKey(), entry.getValue(), new Page<>(1, 20));
            if (page.getTotal() != 0) {
                long pages = page.getPages();
                for (int i = 1; i <= pages; i++) {
                    page = service.getWarnListByState(entry.getKey(), entry.getValue(), new Page<>(1, 20));
                    List<WarnStatus> warnStatusList = page.getRecords();
                    if (warnStatusList == null || warnStatusList.size() <= 0) continue;
                    res = updateTaskState(warnStatusList);
                    if (res == 0) continue;
                    total += res;
                }
            }
            retu += total;
            try {
                Thread.sleep(1 * 1000);//睡眠秒
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
        return JsonResult.success("成功修改异常任务状态" + retu + "条");
    }

    public int updateTaskState(List<WarnStatus> warnStatusList) {
        int count = 0;
        String token = getAccessToken();
        if (warnStatusList != null && warnStatusList.size() > 0) {
            warnStatusList = warnStatusList.stream().collect(Collectors.collectingAndThen(
                    Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(WarnStatus::getTaskId))), ArrayList::new));
            for (WarnStatus status : warnStatusList) {
                JSONObject res = updateWarnState(status, token);
                if ("leanworkflow-40101".equals(res.getString("code"))) {//token不对，重新获取
                    token = getAccessToken();
                    res = updateWarnState(status, token);
                }
                if ("leanworkflow-40001".equals(res.getString("code"))) { //参数不对，记录下来，下次重新发送
                    String message = "{\"state\":\"2\",\"message\":\"参数格式不对\"}";
                    res = JSONObject.parseObject(message);
                }
                if ("leanworkflow-50000".equals(res.getString("code"))) { //参数为null，重新赋值，然后在发送
                    status = setValue(status);
                    res = updateWarnState(status, token);
                }
                String message = res.getString("message");
                if (res.getInteger("state") == 1 || message.contains("任务已反馈")) {//修改指挥调度状态成功的，记录在数据库中。
                    String taskid = service.selectWarnStatus(status);
                    if (taskid != null) continue;
                    service.updateWarnStatus(status);//设置为成功修改状态
                    count++;
                } else {
                    continue;
                }
                // 不用记录修改失败：比如id重复，任务已经反馈;宕机，网络不通，参数不对，
                // 这些不用考虑，只记录成功的，数据库中默认是失败，没有成功的那些定时器会自动在重新发送
            }
        }
        return count;
    }

    public JSONObject updateWarnState(WarnStatus status, String token) {
        String string = "";
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + token);
            headers.put("Content-Type", "application/json");
            headers.put("Accept-Language", "zh-CN,zh;q=0.9");
            headers.put("Accept-Encoding", "gzip, deflate, br");
            String s = JSON.toJSONString(status);//将数据格式变成字符串的格式,也就是参数的格式
            string = HttpClientUtils.sendPut("http://19.104.43.66:8014/api/leanworkflow/v1/task-status", headers, s);
            file_response.warn("修改状态信息:" + string);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return JSONObject.parseObject(string);
    }

    public LinkedHashMap<String, String> getMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap();
        map.put("youyan", "yy_warn_type");
//        map.put("jiayou", "jy_warn_type");
//        map.put("voc", "voc_warn_type");
//        map.put("qixiu", "qx_warn_type");
//        map.put("hbgc", "gc_warn_type");
//        map.put("jinshu", "js_warn_type");
//        map.put("lingxing", "lx_warn_type");
//        map.put("zaozhi", "zz_warn_type");
//        map.put("paishuihu", "psh_warn_type");
        return map;
    }

    public String getAccessToken() {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("client_id", "r_cad_back_end");
        params.add("client_secret", "ed98130b-368c-4320-8014-f9830b5b16d2");
        params.add("grant_type", "client_credentials");
        params.add("scope", "offline_access");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://19.104.43.66:8014/auth/realms/cad/protocol/openid-connect/token", requestEntity, String.class);
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        return jsonObject.getString("access_token");
    }

    public <T> T setValue(T t) { //如果属性为null，则赋值为" "
        try {
            if (t != null) {
                Field[] fields = t.getClass().getDeclaredFields();
                for (Field f : fields) {
                    f.setAccessible(true);
                    if (f.get(t) == null) {
                        String simpleName = f.getType().getSimpleName();
                        if (simpleName.equals("String")) {
                            f.set(t, " ");
                        }
                        if ("completionTime".equals(f.getName())) {
                            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(0));
                            f.set(t, format);
                        }
                        if ("createTime".equals(f.getName())) {
                            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(0));
                            f.set(t, format);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        }
        return t;
    }

}