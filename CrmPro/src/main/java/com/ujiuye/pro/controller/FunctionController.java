package com.ujiuye.pro.controller;


import com.ujiuye.pro.bean.Function;
import com.ujiuye.pro.bean.Module;
import com.ujiuye.pro.service.FunctionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/function")
public class FunctionController {

     @Resource
     private FunctionService functionService;

     @RequestMapping("saveInfo")
     public String saveInfo(Function function){
        boolean b = functionService.saveInfo(function);
        if(b){
            return "redirect:../project-function.jsp";
        }else{
            return "error";
        }
    }


    @RequestMapping("showFunInfo")
    @ResponseBody
    public List<Function> showFunInfo(Integer mid ){
      return functionService.getFunByMid(mid);
    }


}
