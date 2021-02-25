package com.topcent.project.controller;

import java.util.List;

import com.topcent.project.model.PfBasSourceUser;
import com.topcent.project.service.IPfBasSourceUserService;
import com.topcent.project.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 企业用户Controller
 *
 * @author ruoyi
 * @date 2020-05-18
 */
@RestController
@RequestMapping("/enterprise/user")
public class EnterUserController
{
    @Autowired
    private IPfBasSourceUserService pfBasSourceUserService;

    /**
     * 查询企业用户列表
     */
    @GetMapping("/list")
    public AjaxResult list(PfBasSourceUser user)
    {
        List<PfBasSourceUser> users = pfBasSourceUserService.selectPfBasSourceUserList(user);
        return AjaxResult.success(users);
    }

}