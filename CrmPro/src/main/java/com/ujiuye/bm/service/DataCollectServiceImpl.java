package com.ujiuye.bm.service;

import com.ujiuye.bm.bean.Datacollect;
import com.ujiuye.bm.bean.DatacollectQueryVo;
import com.ujiuye.bm.mapper.DatacollectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataCollectServiceImpl implements DataCollectService {

    @Resource
    private DatacollectMapper  datacollectMapper;

    @Override
    public boolean BatchInsert(List<Datacollect> list) {
        int i = datacollectMapper.BatchInsert(list);
        return i>0;
    }

    @Override
    public List<Datacollect> getInfoGroup() {
        return datacollectMapper.getInfoGroup();
    }

    @Override
    public Datacollect getOneByCname(String cname) {
        return datacollectMapper.getOneBycname(cname);
    }

    @Override
    public List<Datacollect> getPng(DatacollectQueryVo vo) {
        return datacollectMapper.showPng(vo);
    }
}
