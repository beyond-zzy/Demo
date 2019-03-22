package com.ujiuye.usual.service;

import com.ujiuye.emp.mapper.EmployeeMapper;
import com.ujiuye.usual.bean.Evaluate;
import com.ujiuye.usual.mapper.EvaluateMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EvaluateServiceImpl implements  EvaluateService {

    @Resource
    private EvaluateMapper evaluateMapper;

    @Override
    public boolean addEval(Evaluate evaluate) {
        int i = evaluateMapper.insertSelective(evaluate);
        return i>0;
    }
}
