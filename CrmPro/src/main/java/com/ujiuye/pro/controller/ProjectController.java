package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/pro")
public class ProjectController {
    @Resource
    private ProjectService projectService;

    @RequestMapping("saveInfo")
     public String saveInfo(Project project,String newcomname){
         project.setComname(Integer.parseInt(newcomname.split(",")[0]));
         boolean b = projectService.saveInfo(project);
         if(b){
             return "redirect:../project-base.jsp";
         }else{
             return "error";
         }
     }

    @RequestMapping("showInfo")
    @ResponseBody
    public List<Project> showInfo(String  mark){
        ProjectExample pe = new ProjectExample();
        if(mark!=null&&mark.equals("0")){
            // 需求
            return projectService.getInfo(null);
        }else{
            //所有的
            return projectService.getInfo(pe);
        }
    }

}
