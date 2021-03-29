package com.teamoneit.warn.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;
@Data
public class JsonResult {
    private int code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public JsonResult(String message, Object data) {
        this(200, message, data);
    }

    public JsonResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static JsonResult success(String message) {
        return new JsonResult(message, null);
    }

    public static JsonResult success(Object data) {
        return new JsonResult(null, data);
    }

    public static JsonResult success(IPage<?> page) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("total", page.getTotal());
        data.put("pages", page.getPages());
        data.put("list", page.getRecords());
        return new JsonResult(null, data);
    }

    public static JsonResult error(String message) {
        return error(500, message);
    }

    public static JsonResult error(int code, String message) {
        return new JsonResult(code, message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
