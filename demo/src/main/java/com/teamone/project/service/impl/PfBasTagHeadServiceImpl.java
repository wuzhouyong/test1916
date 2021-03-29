package com.teamone.project.service.impl;

import com.teamone.project.domain.PfBasTagHead;
import com.teamone.project.mapper.PfBasTagHeadMapper;
import com.teamone.project.service.PfBasTagHeadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wzy
 * @since 2021-02-23
 */
@Service
public class PfBasTagHeadServiceImpl extends ServiceImpl<PfBasTagHeadMapper, PfBasTagHead> implements PfBasTagHeadService {
    @Autowired
    public PfBasTagHeadMapper pfBasTagHeadMapper;

    @Override
    public IPage<PfBasTagHead> pfBasTagHeadPageList(PfBasTagHead pfBasTagHead) {
        QueryWrapper<PfBasTagHead> queryWrapper = new QueryWrapper<>();
        IPage<PfBasTagHead> page = new Page<>(pfBasTagHead.getPageNum(), pfBasTagHead.getPageSize());
        return pfBasTagHeadMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<PfBasTagHead> pfBasTagHeadList(PfBasTagHead pfBasTagHead) {
        QueryWrapper<PfBasTagHead> queryWrapper = new QueryWrapper<>();
        return pfBasTagHeadMapper.selectList(queryWrapper);
    }

    @Override
    public PfBasTagHead pfBasTagHeadInfo(String tagHeadId) {
        return pfBasTagHeadMapper.selectById(tagHeadId);
    }

    @Override
    public void savePfBasTagHead(PfBasTagHead pfBasTagHead) {
        pfBasTagHeadMapper.insert(pfBasTagHead);
    }

    @Override
    public void updatePfBasTagHead(PfBasTagHead pfBasTagHead) {
        pfBasTagHeadMapper.updateById(pfBasTagHead);
    }

    @Override
    public void removePfBasTagHeadByIds(String[] tagHeadIds) {
        pfBasTagHeadMapper.deleteBatchIds(Arrays.asList(tagHeadIds));
    }
}
