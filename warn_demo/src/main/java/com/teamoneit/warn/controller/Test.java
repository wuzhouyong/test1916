package com.teamoneit.warn.controller;

/**
 * @author solang
 * @date 2021-03-26 10:41
 */

import com.alibaba.fastjson.JSONObject;
import com.teamoneit.warn.service.IWarnService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class Test {

    public static final String ADD_URL = "http://19.104.43.66:8014/auth/realms/cad/protocol/openid-connect/token";

    public static final String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ5eVJvQXNNUUQ5Qmh1aUtYZDV2dy10WGU2QW43aldwcUNockRFRnBieVBBIn0.eyJleHAiOjE2MTY3NDg2NzUsImlhdCI6MTYxNjcxOTg3NSwianRpIjoiZTBiZTIxMWEtNTk5Ny00Njg0LWFhZTAtNjM5NTVjY2IwYzNhIiwiaXNzIjoiaHR0cDovLzE5LjEwNC40My41OTo4MTgwL2F1dGgvcmVhbG1zL2NhZCIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIxZDJkMzc2OC03YTA5LTRmYWMtYjg4OS1jYzcxMWVjODA1ZGUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ6X2NhZF9iYWNrX2VuZCIsInNlc3Npb25fc3RhdGUiOiIwYmVhZmJkNy1iOWNkLTQ3N2ItYTg3Yy02MjgyMzhiOTc2ZjAiLCJhY3IiOiIxIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJ6X2NhZF9iYWNrX2VuZCI6eyJyb2xlcyI6WyJ1bWFfcHJvdGVjdGlvbiJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvZmZsaW5lX2FjY2VzcyBwcm9maWxlIGVtYWlsIiwiY2xpZW50SWQiOiJ6X2NhZF9iYWNrX2VuZCIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwiY2xpZW50SG9zdCI6IjE5LjEwNC40My42MCIsInByZWZlcnJlZF91c2VybmFtZSI6InNlcnZpY2UtYWNjb3VudC16X2NhZF9iYWNrX2VuZCIsImNsaWVudEFkZHJlc3MiOiIxOS4xMDQuNDMuNjAifQ.MxKOlJ0x7hUTq0Qw1C5wNyULOi_e-m12IGdiBWiXg4zLAnBCJLDmlDzKTJHW3PjEpMJAZraVIdVDo9-ThucIRRbUudfcX71SWUbIlvyQ8MNyBALwHv3x8gxu_FaE9PnEfsG2TIu3qGXzetFJL_HflxnK9P514hG6EouYRmivBcqgQtwQEGb4CB2_wQfQZS2j9xIgeHIRmlWhNCj8fKRo9bmModTqZuL45S0Qr6Yw2KiNNAYUOYFOR1g8QeACOMGP3RIqB3z28E2wrqtgl94fXMyS10OU7uJ-byKuSduQTFtFdVY9H-pOjX2IAu4hDHJqntAtyDabBpajPhHZUx36yg";

    public static final String postData = "http://19.104.43.66:8014/api/leanworkflow/v1/task-info";

    public static final String townCode = "http://19.104.43.66:8014/api/leanworkflow/v1/town-street";


    @Autowired
    private IWarnService service;

    private static Test test;

    @PostConstruct
    public void init() {
        test = this;
        test.service = this.service;
    }

    public static void main(String[] arg) throws Exception {

        //getparame();
        String data1 = "{\"client_id:\":\"z_cad_back_end\",\"client_secret\":\"111d4ebe-2c2b-42e7-9c10-3808be672ab8\"," + "\"grant_type\":\"client_credentials\",\"scope\":\"offline_access\"}";

        /*String data1 = "grant_type,client_id:z_cad_back_end,client_secret:111d4ebe-2c2b-42e7-9c10-3808be672ab8,";
        String data3 = "scope:offline_access";*/

       /* HashMap<String,String> te=new HashMap<String,String>();
        te.put("grant_type", "client_credentials");
        te.put("client_id", "z_cad_back_end");
        te.put("client_secret", "111d4ebe-2c2b-42e7-9c10-3808be672ab8");
        te.put("scope", "offline_access");*/
        //String s= postFormUrlEncoded(ADD_URL,null,data1 );
        // sendPost(ADD_URL, data1);
        //posts(ADD_URL,data1 + data3);
        //String s=  doGet(townCode,null,token);
        Test te = new Test();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("grant_type", "client_credentials");
        map.put("client_id", "z_cad_back_end");
        map.put("client_secret", "111d4ebe-2c2b-42e7-9c10-3808be672ab8");
        map.put("scope", "offline_access");
        JSONObject jsonObj = new JSONObject(map);
        //doToken(ADD_URL,jsonObj.toString());
//        te.postData("");
    }

    public static String post(String strURL, String params) {
        BufferedReader reader = null;
        try {

            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            // connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            //因为要登陆才可以执行请求，所以这里要带cookie的header
            connection.setRequestProperty("Content-Type", "application/json");        // 设置发送数据的格式
            connection.connect();
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();
            return res;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }
/*
    public void postData(String strURL) {  //用这种方式来取10个，只是效率底，没分页那么好
        String retus = "";
        JTaskhead jTaskhead = new JTaskhead();
        try {
            //    List<SysDept> dps = deptService.setAllDeptChild();
            List<JTaskhead> list = test.jTaskheadService.selectHeadStatus(jTaskhead);
            JSONObject jsonObj = new JSONObject();
            String json = "";
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("taskId", list.get(i).getZtaskhid());
                map.put("targetId", "");
                map.put("targetName", list.get(i).getZtasktitle());
                map.put("longitude", "0");
                map.put("latitude", "0");
                map.put("townStreetCode", "ae9127d9-0605-47e6-8829-3ef9f9c75eec");
                map.put("townStreetFullName", list.get(i).getZhandlerdeptname());
                map.put("level", "1");
                map.put("taskClassify", "1");
                map.put("creatorName", list.get(i).getZhandlerusername());
                map.put("taskType", "1");
                map.put("createTime", list.get(i).getCreateTime().toString());
                map.put("brief", list.get(i).getZtasktitle());
                map.put("detail", list.get(i).getZtaskcontent());
                map.put("abnormalInfo", "");
                map.put("timeLimit", "12");
                map.put("implement", "1");
                jsonObj = new JSONObject(map);
                if (i % 10 != 0) {
                    json += jsonObj.toString() + ",";
                } else {
                    json += jsonObj.toString();
                    StringBuffer stringBuffer = new StringBuffer(json.toString());
                    stringBuffer.insert(0, "[");
                    stringBuffer.insert(stringBuffer.length(), "]");
                    doPost(postData, stringBuffer.toString());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "";
    }*/

    public static String sendPost(String url, String pamare) {

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(4 * 1000);
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
            out.println(pamare);
            // flush输出流的缓冲
            out.flush();
            InputStream is = null;
            if (conn.getResponseCode() >= 400) {
                is = conn.getErrorStream();
            } else {
                is = conn.getInputStream();
            }

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static void getparame() {
/*        String data1 = "{\"client_id:\":\"z_cad_back_end\",\"client_secret\":\"111d4ebe-2c2b-42e7-9c10-3808be672ab8\",";
        String data3 = "\"grant_type\":\"client_credentials\",\"scope\":\"offline_access\"}";*/
        String data1 = "{client_id:z_cad_back_end,client_secret:111d4ebe-2c2b-42e7-9c10-3808be672ab8,";
        String data3 = "grant_type:client_credentials,scope:offline_access}";
        String params = data1 + data3;
        System.out.println(params);
        String reString = post(ADD_URL, params);
        System.out.println(reString);
    }

    public static String postFormUrlEncoded(String path, HashMap<String, String> Headers, String requestBody) {
        String result = " ";

        HttpURLConnection connection = null;
        try {
            URL url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 不使用缓存
            connection.setUseCaches(false);
            if (Headers != null) {
                if (Headers.size() > 0) {
                    for (Map.Entry<String, String> entry : Headers.entrySet()) {
                        connection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
            }
            connection.connect();
            PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8));
            out.print(requestBody);
            out.flush();

            int resultCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == resultCode) {
                StringBuffer stringBuffer = new StringBuffer();
                String readLine;
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                while ((readLine = responseReader.readLine()) != null) {
                    stringBuffer.append(readLine).append("\n");
                }
                responseReader.close();
                result = stringBuffer.toString();
            } else {
                result = "{\"code\":\"" + resultCode + "\"}";
            }
            out.close();
        } catch (Exception e) {
            return "{\"code\":500,\"result\":\"x-www-form-urlencoded请求 " + path + " 时出现异常\"}";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

/*
    public static String posts(String strURL, String params) {
        System.out.println(strURL);
        System.out.println(params);
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            int length = (int) connection.getContentLength();// 获取长度
            InputStream is = connection.getErrorStream();
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8"); // utf-8编码
                System.out.println(result);
                return result;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }
*/

    public static String doGet(String url, Map params, String token) {
        BufferedReader in = null;
        CloseableHttpResponse response = null;
        //1.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            httpClient = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder(url);
            //设置参数
            if (params != null && params.size() > 0) {
                for (Object param : params.keySet()) {
                    uriBuilder.setParameter(param + "", params.get(param) + "");
                }
            }
            //2.创建HttpGet对象，设置URL地址
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            if (!StringUtils.isBlank(token)) { //设置请求token
                httpGet.setHeader("Authorization", "Bearer " + token);
            }
            System.out.println("发送请求的信息：" + httpGet);
            //使用httpClient发起响应获取repsonse
            response = httpClient.execute(httpGet);
            //4.解析响应，获取数据   判断状态码是否是200
            if (response.getStatusLine().getStatusCode() == 200) {
                in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();
                return sb.toString();
            }
            System.out.println("请求状态码----" + response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    public static String doPost(String url, String params) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost

        httpPost.setHeader("Content-Type", "application/json-patch+json");
        httpPost.setHeader("Authorization", "Bearer " + token);
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            } else {
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static String doToken(String url, String params) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost

        httpPost.setHeader("Content-Type", "application/json-patch+json");

        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            } else {
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
