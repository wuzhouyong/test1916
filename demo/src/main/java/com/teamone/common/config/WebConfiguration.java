package com.teamone.common.config;

import com.teamone.common.core.interceptor.AuthorizationInterceptor;
import com.teamone.common.core.interceptor.AutoIdempotentInterceptor;
import com.teamone.common.core.interceptor.TokenInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * web相关配置类
 *
 * @author lw
 * @date 2019/10/6 10:42 上午
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Bean
    public AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor();
    }

    @Bean
    public AutoIdempotentInterceptor autoIdempotentInterceptor() {
        return new AutoIdempotentInterceptor();
    }

    /**
     * 重写添加拦截器方法并添加配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //token拦截配置
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/**");
        //授权码
        registry.addInterceptor(authorizationInterceptor());
        //幂等性
        registry.addInterceptor(autoIdempotentInterceptor());
    }

    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }

    /**
     * 参数解析器
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
    }
}
