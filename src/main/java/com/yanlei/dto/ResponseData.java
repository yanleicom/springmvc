package com.yanlei.dto;

/**
 * @Author: x
 * @Date: Created in 9:06 2018/5/11
 */
public class ResponseData {
    private String s;
    private String s1;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public ResponseData(String s, String s1) {
        this.s = s;
        this.s1 = s1;
    }

    public ResponseData(String s) {
        this.s = s;
    }
}
