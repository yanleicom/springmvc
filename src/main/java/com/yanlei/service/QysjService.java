package com.yanlei.service;

import com.yanlei.model.Qysj;

import java.util.List; /**
 * @Author: x
 * @Date: Created in 16:31 2018/3/5
 */
public interface QysjService {
    void dealBathAdd(List<Qysj> qysjDay, List<Qysj> qysjMonth, List<Qysj> qysjYear);

}
