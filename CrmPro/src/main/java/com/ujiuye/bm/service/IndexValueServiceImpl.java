package com.ujiuye.bm.service;

import com.ujiuye.bm.bean.Indexvalue;
import com.ujiuye.bm.mapper.IndexvalueMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IndexValueServiceImpl implements  IndexValueServie {

    @Resource
    private IndexvalueMapper indexvaluMapper;

    @Override
    public boolean saveInfo(Indexvalue indexvalue) {
        int i = indexvaluMapper.insertSelective(indexvalue);
        return i>0;
    }
}
