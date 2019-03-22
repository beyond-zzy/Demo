package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Function;

import java.util.List;

public interface FunctionService {


    public boolean  saveInfo(Function function);


    public List<Function> getFunByMid(Integer mid);
}
