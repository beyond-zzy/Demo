package com.ujiuye.usual.controller;

import com.ujiuye.emp.bean.Employee;
import com.ujiuye.usual.bean.Evaluate;
import com.ujiuye.usual.bean.Forumpost;
import com.ujiuye.usual.service.EvaluateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/eval")
public class EvaluateController {

    @Resource
    private EvaluateService evaluateService;

    @RequestMapping("add")
    public String add(String evacontent,HttpSession session){
        //boolean b = evaluateService.addEval(evaluate);
        System.out.println(evacontent);
        Evaluate ev = new Evaluate();
        // 设置帖子内容
        ev.setEvacontent(evacontent);
        //设置发帖人的id
        Employee emp = (Employee)session.getAttribute("activeUser");
        ev.setEmpFk4(emp.getEid());
        // 设置帖子id
        Forumpost forum = (Forumpost)session.getAttribute("fe");
        ev.setForumFk(forum.getForumid());
        boolean b  = evaluateService.addEval(ev);
        if(b){
              return "redirect:../forum/getForumpostByid?id="+forum.getForumid();
        }else{
            return "redirect:../error.jsp";
        }
    }

}
