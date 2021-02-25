package com.teamone.project.service;

import com.teamone.project.domain.PfBasTagHead;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzy
 * @since 2021-02-23
 */
public interface PfBasTagHeadService extends IService<PfBasTagHead> {
    IPage<PfBasTagHead> pfBasTagHeadPageList(PfBasTagHead pfBasTagHead);

    List<PfBasTagHead> pfBasTagHeadList(PfBasTagHead pfBasTagHead);

    PfBasTagHead pfBasTagHeadInfo(String tagHeadId);

    void savePfBasTagHead(PfBasTagHead pfBasTagHead);

    void updatePfBasTagHead(PfBasTagHead pfBasTagHead);

    void removePfBasTagHeadByIds(String [] tagHeadIds);
}
