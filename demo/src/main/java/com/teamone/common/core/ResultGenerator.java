package com.teamone.common.core;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static ResponseResult genSuccessResult() {
        return new ResponseResult()
                .setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static ResponseResult genSuccessResult(Object data) {
        return new ResponseResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static ResponseResult genFailResult(String message) {
        return new ResponseResult()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    public static ResponseResult genFailResult(ResultCode resultCode, String message) {
        return new ResponseResult()
                .setCode(resultCode)
                .setMessage(message);
    }
}
