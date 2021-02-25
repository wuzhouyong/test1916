package com.teamoneit.warn.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teamoneit.warn.entity.WarnParam;
import com.teamoneit.warn.entity.WarnResult;

import java.util.List;

public interface IWarnService {
    @DS("#prefix")
    List<WarnResult> getWarnList(String prefix, WarnParam param);

    @DS("#prefix")
    IPage<WarnResult> getWarnList(String prefix, Page<?> page, WarnParam param);
}
