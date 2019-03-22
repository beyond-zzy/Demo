package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.bean.BaoxiaoExample;

import java.util.List;

public interface BaoXiaoService {


    public  boolean saveInfo(Baoxiao baoxiao);

    public List<Baoxiao> show(BaoxiaoExample baoxiaoExample);

     public List<Baoxiao> showBaoxiaoAndEmp(int status);

     public boolean updateBaoxiao(Baoxiao bx);


}
