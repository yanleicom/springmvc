package com.yanlei.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class ResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = 5477769401425140086L;

    private String msg = ResponseEnum.SUCCESS.getMsg();

    private Integer code = ResponseEnum.SUCCESS.getCode();

    private T data;

    public ResponseDTO error() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(ResponseEnum.ERROR.getCode());
        responseDTO.setMsg(ResponseEnum.ERROR.getMsg());
        return responseDTO;
    }

    public ResponseDTO() {

    }

    public ResponseDTO(T data) {
        this.data = data;
    }

    public ResponseDTO(String msg, Integer code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
