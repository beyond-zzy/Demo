package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeServiceImpl implements  NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public boolean saveInfo(Notice notice) {
        int insert = noticeMapper.insert(notice);
        if(insert>0){
            return true;
        }
        return false;
    }

    public List<Notice> getAll(){
        return noticeMapper.selectByExample(null);
    }

    @Override
    public List<Notice> getMainPage() {
        return noticeMapper.showMainPage();
    }

    @Override
    public Notice getOneByPrimary(Integer nid) {
        return noticeMapper.selectByPrimaryKey(nid);
    }
}
