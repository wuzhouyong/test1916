package com.topcent.project.mapper;


import com.topcent.project.model.PfBasSourceUser;

import java.util.List;

/**
 * 企业用户Mapper接口
 *
 * @author ruoyi
 * @date 2020-05-18
 */
public interface PfBasSourceUserMapper
{

    public List<PfBasSourceUser> selectPfBasSourceUserList(PfBasSourceUser pfBasSourceUser);

}
