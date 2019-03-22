package com.ujiuye.usual.controller;

import com.ujiuye.emp.bean.Employee;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.bean.BaoxiaoExample;
import com.ujiuye.usual.service.BaoXiaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/baoxiao")
public class BaoXiaoController {

    @Resource
    private BaoXiaoService baoXiaoService;

    @RequestMapping("saveInfo")
    public  String  saveInfo(Baoxiao baoxiao,HttpSession session){
        baoxiao.setBxid(UUID.randomUUID().toString());
        Employee  emp = (Employee)session.getAttribute("activeUser");
        baoxiao.setEmpFk(emp.getEid());
        boolean b = baoXiaoService.saveInfo(baoxiao);
        if(b){
            return "redirect:show";
        }else{
            return "error";
        }
    }

    @RequestMapping("show")
    public  String  show(Model model,HttpSession session){
        BaoxiaoExample be = new BaoxiaoExample();
        BaoxiaoExample.Criteria cc = be.createCriteria();
        Employee  emp = (Employee)session.getAttribute("activeUser");
        cc.andEmpFkEqualTo(emp.getEid());
        List<Baoxiao> show = baoXiaoService.show(be);
        session.setAttribute("baoxiao",show);
        return "redirect:/mybaoxiao-base.jsp";
   }


    //财务人员查看待审批报销单
    @RequestMapping("udobaoxiao")
    public  String  udobaoxiao(HttpSession session){
        List<Baoxiao> baoxiaos = baoXiaoService.showBaoxiaoAndEmp(0);
        session.setAttribute("udobaoxiao",baoxiaos);
        return "redirect:/baoxiao-task.jsp";
    }

}
