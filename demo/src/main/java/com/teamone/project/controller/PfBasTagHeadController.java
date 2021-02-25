package com.teamone.project.controller;

import com.teamone.common.core.ResponseResult;
import com.teamone.common.core.ResultGenerator;
import com.teamone.project.service.PfBasTagHeadService;
import com.teamone.project.domain.PfBasTagHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzy
 * @since 2021-02-23
 */
@RestController
@RequestMapping("/pf-bas-tag-head")
public class PfBasTagHeadController {
    @Autowired
    public PfBasTagHeadService pfBasTagHeadService;

    /**
     * 列表（分页）
     */
    @GetMapping("/page/list")
    public ResponseResult pfBasTagHeadPageList(PfBasTagHead pfBasTagHead) {
        return ResultGenerator.genSuccessResult(pfBasTagHeadService.pfBasTagHeadPageList(pfBasTagHead));
    }

    /**
    * 列表
    */
    @GetMapping("/list")
    public ResponseResult pfBasTagHeadList(PfBasTagHead pfBasTagHead) {
        return ResultGenerator.genSuccessResult(pfBasTagHeadService.pfBasTagHeadList(pfBasTagHead));
    }

    /**
     * 获取详情
     */
    @GetMapping("/{tagHeadId}")
    public ResponseResult pfBasTagHeadInfo(@PathVariable String tagHeadId) {
        return ResultGenerator.genSuccessResult(pfBasTagHeadService.pfBasTagHeadInfo(tagHeadId));
    }

    /**
     * 新增
     */
    @PostMapping
    public ResponseResult savePfBasTagHead(@RequestBody PfBasTagHead pfBasTagHead) {
        pfBasTagHeadService.savePfBasTagHead(pfBasTagHead);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 修改
     */
    @PutMapping
    public ResponseResult updatePfBasTagHead(@RequestBody PfBasTagHead pfBasTagHead) {
        pfBasTagHeadService.updatePfBasTagHead(pfBasTagHead);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{tagHeadIdS}")
    public ResponseResult removePfBasTagHeadByIds(@PathVariable String[]tagHeadIds) {
        pfBasTagHeadService.removePfBasTagHeadByIds(tagHeadIds);
        return ResultGenerator.genSuccessResult();
    }
}

