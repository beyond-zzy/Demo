package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.bean.AnalysisExample;
import com.ujiuye.pro.service.AnalysisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/analysis")
public class AnalysisController {

    @Resource
    private AnalysisService analysisService;

    @RequestMapping("saveInfo")
    public String saveInfo(Analysis analysis){
        boolean b = analysisService.saveAnInfo(analysis);
        if(b){
            return "redirect:../project-need.jsp";
        }else{
            return "error";
        }
    }

    @RequestMapping("getAnaInfo")
    @ResponseBody
    public Analysis getAnaInfo(int  pid){
        System.out.println(pid+"=======");
        AnalysisExample ae = new AnalysisExample();
        AnalysisExample.Criteria cc = ae.createCriteria();
        cc.andIdEqualTo(pid);
        List<Analysis> analysis = analysisService.getAnalysis(ae);
        return analysis.get(0);
    }

}
