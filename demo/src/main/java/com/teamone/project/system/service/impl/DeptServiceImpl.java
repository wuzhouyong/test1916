package com.teamone.project.system.service.impl;

import com.teamone.project.system.domain.entity.Dept;
import com.teamone.project.system.mapper.DeptMapper;
import com.teamone.project.system.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Autowired
    public DeptMapper deptMapper;

    @Override
    public IPage<Dept> deptPageList(Dept dept) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        IPage<Dept> page = new Page<>(dept.getPageNum(), dept.getPageSize());
        return deptMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<Dept> deptList(Dept dept) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        return deptMapper.selectList(queryWrapper);
    }

    @Override
    public Dept deptInfo(Long deptId) {
        return deptMapper.selectById(deptId);
    }

    @Override
    public void saveDept(Dept dept) {
        deptMapper.insert(dept);
    }

    @Override
    public void updateDept(Dept dept) {
        deptMapper.updateById(dept);
    }

    @Override
    public void removeDeptByIds(Long[] deptIds) {
        deptMapper.deleteBatchIds(Arrays.asList(deptIds));
    }
}
