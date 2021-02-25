package com.teamone.project.system.controller;

import com.teamone.common.core.ResponseResult;
import com.teamone.common.core.ResultGenerator;
import com.teamone.project.system.domain.entity.Dept;
import com.teamone.project.system.service.DeptService;

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
 * 部门表 前端控制器
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    public DeptService deptService;

    /**
     * 部门表列表（分页）
     */
    @GetMapping("/page/list")
    public ResponseResult deptPageList(Dept dept) {
        return ResultGenerator.genSuccessResult(deptService.deptPageList(dept));
    }

    /**
     * 部门表列表
     */
    @GetMapping("/list")
    public ResponseResult deptList(Dept dept) {
        return ResultGenerator.genSuccessResult(deptService.deptList(dept));
    }

    /**
     * 获取部门表详情
     */
    @GetMapping("/{deptId}")
    public ResponseResult deptInfo(@PathVariable Long deptId) {
        return ResultGenerator.genSuccessResult(deptService.deptInfo(deptId));
    }

    /**
     * 新增部门表
     */
    @PostMapping
    public ResponseResult saveDept(@RequestBody Dept dept) {
        deptService.saveDept(dept);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 修改部门表
     */
    @PutMapping
    public ResponseResult updateDept(@RequestBody Dept dept) {
        deptService.updateDept(dept);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除部门表
     */
    @DeleteMapping("/{deptIds}")
    public ResponseResult removeDeptByIds(@PathVariable Long[] deptIds) {
        deptService.removeDeptByIds(deptIds);
        return ResultGenerator.genSuccessResult();
    }
}

