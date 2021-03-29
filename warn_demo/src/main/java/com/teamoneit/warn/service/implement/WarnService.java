package com.teamoneit.warn.service.implement;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teamoneit.warn.entity.WarnParam;
import com.teamoneit.warn.entity.WarnResult;
import com.teamoneit.warn.entity.WarnStatus;
import com.teamoneit.warn.mapper.WarnMapper;
import com.teamoneit.warn.service.IWarnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WarnService implements IWarnService {

    @Autowired
    private WarnMapper mapper;

    @Override
    @DS("#prefix")
    public List<WarnResult> getWarnList(String prefix, WarnParam param) {
        List<WarnResult> list = mapper.getWarnList(param);
        return list;
    }

    @Override
    @DS("#prefix")
    public IPage<WarnResult> getWarnList(String prefix, Page<WarnResult> page, WarnParam param) {
        IPage<WarnResult> list = mapper.getWarnList(page, param);
        return list;
    }

    @Override
    public IPage<WarnResult> getWarnListByTag(String key, String valu, Page<WarnResult> page) {
        IPage<WarnResult> warnListByTag = mapper.getWarnListByTag(page, key, valu);
        return warnListByTag;
    }

    @Override
    public IPage<WarnStatus> getWarnListByState(String key, String valu, Page<WarnResult> page) {
        return mapper.getWarnListByState(page, key, valu);
    }

    @Override
    public int updateWarnStatus(WarnStatus status) {
        int i = 0;
        try {
            i = mapper.updateWarnStatus(status);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int insertWarnStatus(List<WarnResult> status) {
        int i = 0;
        try {
            i = mapper.insertWarnStatus(status);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<String> selectWarnStatusList(List<String> collect) {
        return mapper.selectWarnStatusList(collect);
    }

    @Override
    public String selectWarnStatus(WarnStatus status) {
        return mapper.selectWarnStatus(status);
    }

}
