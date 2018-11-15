
package com.besjon.pojo;
import java.io.Serializable;
import java.util.List;

/**
* @Author: xiayuanlei
* @Date: 2018/1/19 10:55
*/
public class JsonRootBean4 implements Serializable {

    private List<Dlists4> Dlists;
    private List<String> lists;
    private List<Long> longList;
    private String name;
    private String thirteenName;
    private String fourteenName;
    private String fifteenName;


    public List<Long> getLongList() {
        return longList;
    }

    public void setLongList(List<Long> longList) {
        this.longList = longList;
    }

    public List<Dlists4> getDlists() {
        return Dlists;
    }

    public void setDlists(List<Dlists4> dlists) {
        Dlists = dlists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }
    public List<String> getLists() {
        return lists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThirteenName() {
        return thirteenName;
    }

    public void setThirteenName(String thirteenName) {
        this.thirteenName = thirteenName;
    }

    public String getFourteenName() {
        return fourteenName;
    }

    public void setFourteenName(String fourteenName) {
        this.fourteenName = fourteenName;
    }

    public String getFifteenName() {
        return fifteenName;
    }

    public void setFifteenName(String fifteenName) {
        this.fifteenName = fifteenName;
    }
}