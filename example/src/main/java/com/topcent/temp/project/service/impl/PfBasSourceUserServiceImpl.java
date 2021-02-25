package com.topcent.project.service.impl;


import com.topcent.project.mapper.PfBasSourceUserMapper;
import com.topcent.project.model.PfBasSourceUser;
import com.topcent.project.service.IPfBasSourceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业用户Service业务层处理
 *
 * @author ruoyi
 * @date 2020-05-18
 */
@Service
public class PfBasSourceUserServiceImpl implements IPfBasSourceUserService
{
    @Autowired
    private PfBasSourceUserMapper pfBasSourceUserMapper;

    @Override
    public List<PfBasSourceUser> selectPfBasSourceUserList(PfBasSourceUser pfBasSourceUser)
    {
        return pfBasSourceUserMapper.selectPfBasSourceUserList(pfBasSourceUser);
    }


}
