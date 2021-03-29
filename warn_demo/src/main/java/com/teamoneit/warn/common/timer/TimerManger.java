package com.teamoneit.warn.common.timer;

import com.teamoneit.warn.controller.WarnController;
import com.teamoneit.warn.entity.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author solang
 * @date 2020-03-18 21:53
 */
@Slf4j
@Component
public class TimerManger {

    @Autowired
    WarnController controller;

    //第一次启动延迟1分钟启动，启动之后方法结束延迟15分钟启动 1000毫秒就是1秒
    // @Scheduled(cron = "0 0 * * * ? *")
    @Scheduled(initialDelay = 1000 * 60 * 1, fixedDelay = 1000 * 60 * 15)
    @Async
    public void pushExceptionTask() {
        try {
            log.info("推送任务开始");
            JsonResult tag = controller.getWarnListByTag();
            log.warn("推送信息:" + tag.toString());
            log.info("推送任务结束");
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

    //     @Scheduled(cron = "0 40 * * * ? *")
    @Scheduled(initialDelay = 1000 * 60 * 1, fixedDelay = 1000 * 60 * 15)
    @Async
    public void changeExceptionTaskStatus() {
        try {
            log.info("修改状态开始");
            JsonResult tag = controller.updateState();
            log.warn("修改状态:" + tag.toString());
            log.info("修改状态结束");
            Thread.sleep(1000 * 10);//睡眠10s
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

}
