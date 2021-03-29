package com.teamone.project.system.service;

import com.teamone.project.system.domain.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface DeptService extends IService<Dept> {
    IPage<Dept> deptPageList(Dept dept);

    List<Dept> deptList(Dept dept);

    Dept deptInfo(Long deptId);

    void saveDept(Dept dept);

    void updateDept(Dept dept);

    void removeDeptByIds(Long[] deptIds);
}
