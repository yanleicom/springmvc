package com.besjon.pojo;

import java.io.Serializable;

/**
 * @Author: x
 * @Date: Created in 9:37 2018/3/7
 */
public class Fenlist5 implements Serializable{
    private Integer id;
    private String name;
    private Long value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Fenlist5(Integer id, String name, Long value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
}
