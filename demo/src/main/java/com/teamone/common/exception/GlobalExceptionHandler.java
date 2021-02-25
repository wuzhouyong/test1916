package com.teamone.common.exception;

import com.teamone.common.core.ResponseResult;
import com.teamone.common.core.ResultCode;
import com.teamone.common.core.ResultGenerator;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * 统一异常处理
 *
 * @author lw
 * @date 2019/4/3 11:09 上午
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 默认异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception exception) {
        log.error("exception:", exception);
        return ResultGenerator.genFailResult(ResultCode.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    /**
     * 请求方法不支持异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return ResultGenerator.genFailResult(ResultCode.METHOD_NOT_ALLOWD, exception.getMessage());
    }

    /**
     * 404
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseResult noHandlerFoundException(NoHandlerFoundException exception) {
        return ResultGenerator.genFailResult(ResultCode.NOT_FOUND, "请求接口不存在" + exception.getRequestURL());
    }

    /**
     * 请求参数异常
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, MissingServletRequestPartException.class})
    public ResponseResult parameterException() {
        return ResultGenerator.genFailResult(ResultCode.FAIL, "请求参数异常");
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public ResponseResult validatedBindException(BindException e) {
        StringBuilder sbMessage = new StringBuilder();
        for (ObjectError error : e.getAllErrors()) {
            sbMessage.append(error.getDefaultMessage());
        }
        return ResultGenerator.genFailResult(ResultCode.FAIL, sbMessage.toString());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseResult serviceException(ServiceException e) {
        return ResultGenerator.genFailResult(ResultCode.FAIL, e.getMessage());
    }

    /**
     * token异常
     */
    @ExceptionHandler(TokenException.class)
    public ResponseResult tokenException() {
        return ResultGenerator.genFailResult(ResultCode.UNAUTHORIZED, "令牌失效，请重新获取");
    }

    /**
     * 授权码错误
     */
    @ExceptionHandler(AuthCodeException.class)
    public ResponseResult authCodeException() {
        return ResultGenerator.genFailResult(ResultCode.UNAUTHORIZED, "授权码错误");
    }
}
