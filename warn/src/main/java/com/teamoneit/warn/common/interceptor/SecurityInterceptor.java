package com.teamoneit.warn.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamoneit.warn.entity.JsonResult;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class SecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Token");
        if (Objects.equals(token, "fcxEtliDYmyX9ncgY6pUE84gTe4d4qhn")) {  //请求头携带参数: Token:fcxEtliDYmyX9ncgY6pUE84gTe4d4qhn
            return true;
        }
        return write(response, 401, "非法请求");
    }

    private boolean write(HttpServletResponse response, int code, String message) {
        JsonResult result = JsonResult.error(code, message);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(result);
            response.getWriter().println(json);
        } catch (IOException ignored) {

        }
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        return false;
    }
}
