package com.ujiuye.pro.serviceimpl;

import com.ujiuye.pro.bean.Function;
import com.ujiuye.pro.mapper.FunctionMapper;
import com.ujiuye.pro.service.FunctionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {

    @Resource
    private FunctionMapper functionMapper;

    @Override
    public boolean saveInfo(Function function) {
        int insert = functionMapper.insert(function);
        if(insert>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Function> getFunByMid(Integer mid) {
        return functionMapper.getFunByMid(mid);
    }
}
