package com.teamone.common.core.interceptor;

import com.teamone.common.ann.IgnoreToken;
import com.teamone.common.core.Constant;
import com.teamone.common.exception.TokenException;
import com.teamone.common.utils.ContextUtils;
import com.teamone.common.utils.JWTUtils;
import com.teamone.project.system.domain.entity.User;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token 拦截器
 *
 * @author lw
 * @date 2019/10/6 10:16 上午
 */
public class TokenInterceptor implements HandlerInterceptor {
    /**
     * access-token
     */
    @Value("${spring-boot-api.jwt.access-token}")
    private String accessToken;
    /**
     * 是否开启token验证
     */
    @Value("${spring-boot-api.jwt.enabled}")
    private boolean enabled;

    @Autowired
    private JWTUtils jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws TokenException {
        //是否开启token验证
        if (!enabled) return true;
        //类或者方法有@Anonymous注解的放行
        HandlerMethod handler = (HandlerMethod) o;
        if (handler.hasMethodAnnotation(IgnoreToken.class) || handler.getBeanType().getAnnotation(IgnoreToken.class) != null)
            return true;
        //获取请求头token
        String token = request.getHeader(accessToken);

        if (StringUtils.isBlank(token)) {
            throw new TokenException();
        }
        //获取token的数据
        Map<String, Object> tokenData = jwtUtil.getJWTData(token);

        if (tokenData == null) {
            throw new TokenException();
        }

        //存储数据逻辑省略...
        String userId = (String) tokenData.get(Constant.USER_ID);
        String username = (String) tokenData.get(Constant.USERNAME);
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        ContextUtils.setUser(user);
        return true;
    }
}
