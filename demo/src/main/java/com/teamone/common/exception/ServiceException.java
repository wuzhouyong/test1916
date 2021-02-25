package com.teamone.common.exception;


/**
 * 业务异常
 *
 * @author lw
 * @date 2019/4/3 11:09 上午
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
