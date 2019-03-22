package com.ujiuye.pro.serviceimpl;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.bean.AnalysisExample;
import com.ujiuye.pro.mapper.AnalysisMapper;
import com.ujiuye.pro.service.AnalysisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Resource
    private AnalysisMapper analysisMapper;

    @Override
    public boolean saveAnInfo(Analysis analysis) {
        int insert = analysisMapper.insert(analysis);
        if(insert>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Analysis> getAnalysis(AnalysisExample example) {
        return analysisMapper.selectByExample(example);
    }
}
