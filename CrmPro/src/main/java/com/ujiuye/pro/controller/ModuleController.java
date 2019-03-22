package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Module;
import com.ujiuye.pro.bean.ModuleExample;
import com.ujiuye.pro.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController {


    @Resource
    private ModuleService moduleService;

    @RequestMapping("saveInfo")
    public String saveInfo(Module module){
        boolean b = moduleService.saveModInfo(module);
        if(b){
             return "redirect:../project-model.jsp";
        }else{
            return "error";
        }
    }
    @RequestMapping("getModInfo")
    @ResponseBody
    public List<Module> getModInfo(Integer  mid){
        ModuleExample ex = new ModuleExample();
        ModuleExample.Criteria cc = ex.createCriteria();
        cc.andAnalysisFkEqualTo(mid);
        return moduleService.getModule(ex);
    }

}
