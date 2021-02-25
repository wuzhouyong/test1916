package com.teamone.common.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.teamone.common.utils.ContextUtils;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author： lw
 * @email：salecoding@gmail.com
 * @date：2020/6/4
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        String username = ContextUtils.getUsername();
        //this.fillStrategy(metaObject, "showStatus", 1); // 起始版本 3.3.0(推荐使用)
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        String username = ContextUtils.getUsername();
        //this.fillStrategy(metaObject, "showStatus", 1); // 起始版本 3.3.0(推荐使用)
    }
}
