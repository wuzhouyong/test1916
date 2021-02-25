package com.topcent.project.service;


import com.topcent.project.model.PfBasSourceUser;

import java.util.List;


public interface IPfBasSourceUserService
{
    /**
     * 查询企业用户列表
     *
     * @param pfBasSourceUser 企业用户
     * @return 企业用户集合
     */
    public List<PfBasSourceUser> selectPfBasSourceUserList(PfBasSourceUser pfBasSourceUser);

}