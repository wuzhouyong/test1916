package com.teamone.project.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teamone.common.core.ResponseResult;
import com.teamone.project.system.domain.dto.LoginDTO;
import com.teamone.project.system.domain.entity.User;


public interface AuthService extends IService<User> {
    /**
     * 用户登录
     *
     * @param loginDTO
     * @return
     */
    ResponseResult login(LoginDTO loginDTO);

    /**
     * 刷新token
     *
     * @param token
     * @return
     */
    ResponseResult refreshToken(String token);
}
