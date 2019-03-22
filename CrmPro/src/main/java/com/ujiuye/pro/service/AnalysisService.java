package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.bean.AnalysisExample;

import java.util.List;

public interface AnalysisService {

    public boolean saveAnInfo(Analysis analysis);

    public List<Analysis>  getAnalysis(AnalysisExample example);
}
