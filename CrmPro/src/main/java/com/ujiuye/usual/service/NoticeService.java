package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Notice;

import java.util.List;

public interface NoticeService {
    // 添加
    public boolean saveInfo(Notice notice);

    //查看
     public List<Notice> getAll();

     public List<Notice> getMainPage();

    // 查看单个信息
    public Notice getOneByPrimary(Integer nid);
}
