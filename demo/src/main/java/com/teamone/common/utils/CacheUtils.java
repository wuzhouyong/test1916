package com.teamone.common.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author： lw
 * @email：salecoding@gmail.com
 * @date：2020/8/5
 */
@Component
public class CacheUtils {
    private CacheUtils() {
    }

    private final static Cache<String, String> cache = CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值
            .initialCapacity(10)
            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
            .concurrencyLevel(5)
            //设置cache中的数据在写入之后的存活时间为30分钟
            .expireAfterWrite(60 * 30, TimeUnit.SECONDS)
            //构建cache实例
            .build();

    public Cache<String, String> cache() {
        return cache;
    }
}
