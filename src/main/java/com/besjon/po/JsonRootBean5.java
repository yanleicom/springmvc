package com.besjon.po;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: x
 * @Date: Created in 9:42 2018/3/7
 */
public class JsonRootBean5 implements Serializable {
    private List<Fenlist5> fenlist5s;
    private String Column5;
    private Long Column6;
    private String Column7;

    public List<Fenlist5> getFenlist5s() {
        return fenlist5s;
    }

    public void setFenlist5s(List<Fenlist5> fenlist5s) {
        this.fenlist5s = fenlist5s;
    }

    public String getColumn5() {
        return Column5;
    }

    public void setColumn5(String column5) {
        Column5 = column5;
    }

    public Long getColumn6() {
        return Column6;
    }

    public void setColumn6(Long column6) {
        Column6 = column6;
    }

    public String getColumn7() {
        return Column7;
    }

    public void setColumn7(String column7) {
        Column7 = column7;
    }
}
