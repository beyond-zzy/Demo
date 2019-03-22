package com.ujiuye.bm.service;

import com.ujiuye.bm.bean.Datacollect;
import com.ujiuye.bm.bean.DatacollectQueryVo;

import java.util.List;

public interface DataCollectService {


    boolean  BatchInsert(List<Datacollect> list);

    List<Datacollect> getInfoGroup();

    Datacollect getOneByCname(String cname);

    List<Datacollect> getPng(DatacollectQueryVo vo);

}
