
package com.besjon.po;
import java.io.Serializable;
import java.util.List;

/**
* @Author: xiayuanlei
* @Date: 2018/1/23 10:28
*/
public class JsonRootBean3 implements Serializable {

    private List<Dlists2> Dlists;
    private List<String> lists;
    private Long total;
    private String name;
    private String column3;
    public List<Dlists2> getDlists() {
        return Dlists;
    }

    public void setDlists(List<Dlists2> dlists) {
        Dlists = dlists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }
    public List<String> getLists() {
        return lists;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }
}