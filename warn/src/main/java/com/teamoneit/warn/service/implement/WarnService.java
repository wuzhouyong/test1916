package com.teamoneit.warn.service.implement;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teamoneit.warn.entity.WarnParam;
import com.teamoneit.warn.entity.WarnResult;
import com.teamoneit.warn.mapper.WarnMapper;
import com.teamoneit.warn.service.IWarnService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarnService implements IWarnService {
    private final WarnMapper mapper;

    public WarnService(WarnMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @DS("#prefix")
    public List<WarnResult> getWarnList(String prefix, WarnParam param) {
        List<WarnResult> list = mapper.getWarnList(param);
        return list;
    }

    @Override
    @DS("#prefix")
    public IPage<WarnResult> getWarnList(String prefix, Page<?> page, WarnParam param) {
        IPage<WarnResult> list = mapper.getWarnList(page, param);
        return list;
    }
}
