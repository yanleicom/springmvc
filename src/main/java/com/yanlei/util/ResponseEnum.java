package com.yanlei.util;

/**
 * @Author: x
 * @Date: Created in 10:12 2018/3/13
 */
public enum ResponseEnum {
    SUCCESS("调用成功",200),
    Error("调用失败",500);

    private String msg;
    private Integer code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    ResponseEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }
}
