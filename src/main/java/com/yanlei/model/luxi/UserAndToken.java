package com.yanlei.model.luxi;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: x
 * @Date: Created in 9:52 2018/5/11
 */
public class UserAndToken implements Serializable {
    private String echostr;
    private String authToken;
    private String userId;
    private String userName;
    private String code;
    private String cShowId;
    private String trueName;

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getcShowId() {
        return cShowId;
    }

    public void setcShowId(String cShowId) {
        this.cShowId = cShowId;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }
}
