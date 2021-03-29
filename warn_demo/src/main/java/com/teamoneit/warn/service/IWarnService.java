package com.teamoneit.warn.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teamoneit.warn.entity.WarnParam;
import com.teamoneit.warn.entity.WarnResult;
import com.teamoneit.warn.entity.WarnStatus;

import java.util.List;

public interface IWarnService {
    @DS("#prefix")
    List<WarnResult> getWarnList(String prefix, WarnParam param);

    @DS("#prefix")
    IPage<WarnResult> getWarnList(String prefix, Page<WarnResult> page, WarnParam param);
    IPage<WarnResult> getWarnListByTag(String key, String valu, Page<WarnResult> page);
    IPage<WarnStatus> getWarnListByState(String key, String valu, Page<WarnResult> page);
    int updateWarnStatus(WarnStatus status);
    int insertWarnStatus(List<WarnResult> status);
    List<String> selectWarnStatusList(List<String> collect);
    String selectWarnStatus(WarnStatus status);


}
