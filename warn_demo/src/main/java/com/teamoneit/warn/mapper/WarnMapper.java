package com.teamoneit.warn.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teamoneit.warn.entity.WarnParam;
import com.teamoneit.warn.entity.WarnResult;
import com.teamoneit.warn.entity.WarnStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarnMapper {
    List<WarnResult> getWarnList(WarnParam param);

    IPage<WarnResult> getWarnList(Page<WarnResult> page, WarnParam param);

    IPage<WarnResult> getWarnListByTag(Page<WarnResult> page, @Param("key") String key, @Param("valu") String valu);

    IPage<WarnStatus> getWarnListByState(Page<WarnResult> page, @Param("key") String key, @Param("valu") String valu);

    int updateWarnStatus(WarnStatus status);

    int insertWarnStatus(List<WarnResult> status);

    List<String> selectWarnStatusList(List<String> collect);

    String selectWarnStatus(WarnStatus status);

}
