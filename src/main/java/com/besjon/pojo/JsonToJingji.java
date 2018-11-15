package com.besjon.pojo;

import java.util.List;
import java.util.Map;

/**
 * @Author: x
 * @Date: Created in 10:16 2018/5/8
 */
public class JsonToJingji {

    private List<JingJiPageDlists> dlists;
    private List<String> lists;
    private String head;
    private List<String> name;
    private List<List<Long>> intValue;
    private List<String> list;
    private List<List<Object>> value;

    public List<JingJiPageDlists> getDlists() {
        return dlists;
    }

    public void setDlists(List<JingJiPageDlists> dlists) {
        this.dlists = dlists;
    }

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

   /* public List<Long> getIntValue() {
        return intValue;
    }

    public void setIntValue(List<Long> intValue) {
        this.intValue = intValue;
    }*/

    public List<List<Long>> getIntValue() {
        return intValue;
    }

    public void setIntValue(List<List<Long>> intValue) {
        this.intValue = intValue;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public List<List<Object>> getValue() {
        return value;
    }

    public void setValue(List<List<Object>> value) {
        this.value = value;
    }
}
