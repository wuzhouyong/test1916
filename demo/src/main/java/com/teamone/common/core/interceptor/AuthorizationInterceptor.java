package com.teamone.common.core.interceptor;

import com.teamone.common.exception.AuthCodeException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author： lw
 * @email：salecoding@gmail.com
 * @date：2020/9/17
 */
public class AuthorizationInterceptor implements HandlerInterceptor {
    /**
     * 是否开启授权码验证
     */
    @Value("${spring-boot-api.autoIdempotent.enabled}")
    private boolean enabled;
    /**
     * auth-code
     */
    @Value("${spring-boot-api.autoIdempotent.auth-code}")
    private String authCode;
    /**
     * 授权码
     */
    @Value("${spring-boot-api.autoIdempotent.codes}")
    private List<String> codes;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AuthCodeException {
        //是否开启授权码验证
        if (!enabled) return true;
        String code = request.getHeader(authCode);
        if (!codes.contains(code)) {
            throw new AuthCodeException();
        }
        return true;
    }
}
