package com.gpagers.cn.handle.model;

/**
 * Created by fred on 16/9/5.
 */
public class SimpleRsult {

    public static final String systemError="系统繁忙";
    public static final String success="处理成功";
    public Object data;
    public int code = 200;

    private String message=systemError;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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
}
