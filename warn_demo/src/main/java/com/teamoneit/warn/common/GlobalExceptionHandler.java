package com.teamoneit.warn.common;

import com.teamoneit.warn.entity.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public JsonResult exception(Exception ex) {
        logger.error(ex.getMessage());
        return JsonResult.error("系统错误，请联系管理员！");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public JsonResult notFoundException() {
        return JsonResult.error(404, "请求的接口不存在");
    }

    @ExceptionHandler({
            BindException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
            MissingServletRequestPartException.class,
            MissingServletRequestParameterException.class
    })
    public JsonResult notValidException(MethodArgumentNotValidException ex) {
        if (ex.getBindingResult().hasErrors()) {
            ObjectError error = ex.getBindingResult().getAllErrors().get(0);
            return JsonResult.error(400, error.getDefaultMessage());
        }
        return JsonResult.error(400, "参数错误");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult notSupportedException(HttpRequestMethodNotSupportedException ex) {
        return JsonResult.error(405, "不支持的请求方法");
    }
}
