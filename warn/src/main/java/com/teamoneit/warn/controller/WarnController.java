package com.teamoneit.warn.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teamoneit.warn.entity.JsonResult;
import com.teamoneit.warn.entity.WarnParam;
import com.teamoneit.warn.entity.WarnResult;
import com.teamoneit.warn.service.IWarnService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{prefix}/warn")
public class WarnController {
    private final IWarnService service;

    public WarnController(IWarnService service) {
        this.service = service;
    }

    @PostMapping("/list")
    public JsonResult getWarnList(@PathVariable String prefix, Page<?> page, @RequestBody WarnParam param) {
        param.setTagHeadId("1012");
        IPage<WarnResult> list = service.getWarnList(prefix, page, param);
        return JsonResult.success(list);
    }
}