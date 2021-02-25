package com.teamone.project.system.service;

import com.teamone.common.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;


public interface TokenService {

    /**
     * 创建token
     *
     * @return
     */
    String createToken();

    /**
     * 检验token
     *
     * @param request
     * @return
     */
    void checkToken(HttpServletRequest request) throws ServiceException;
}
