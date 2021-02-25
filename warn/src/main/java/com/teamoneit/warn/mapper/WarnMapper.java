package com.teamoneit.warn.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teamoneit.warn.entity.WarnParam;
import com.teamoneit.warn.entity.WarnResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarnMapper {
    List<WarnResult> getWarnList(WarnParam param);
    IPage<WarnResult> getWarnList(Page<?> page, WarnParam param);
}
