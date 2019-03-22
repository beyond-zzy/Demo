package com.ujiuye.pro.serviceimpl;

import com.ujiuye.pro.bean.Module;
import com.ujiuye.pro.bean.ModuleExample;
import com.ujiuye.pro.mapper.ModuleMapper;
import com.ujiuye.pro.service.ModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Resource
    private ModuleMapper moduleMapper;

    @Override
    public boolean saveModInfo(Module module) {
        int insert = moduleMapper.insert(module);
        if(insert>0){
            return true;
        }
        return false;
    }
    @Override
    public List<Module> getModule(ModuleExample example) {
        return moduleMapper.selectByExample(example);
    }
}
