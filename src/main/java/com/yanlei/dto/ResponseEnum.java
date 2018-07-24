package com.yanlei.dto;

public enum ResponseEnum {

    SUCCESS("调用成功",200),
    ERROR("调用失败",500);

    private String msg;

    private Integer code;

    ResponseEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

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
}
