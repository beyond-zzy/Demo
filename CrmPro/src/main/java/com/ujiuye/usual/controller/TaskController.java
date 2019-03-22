package com.ujiuye.usual.controller;

import com.ujiuye.emp.bean.Employee;
import com.ujiuye.usual.bean.Task;
import com.ujiuye.usual.service.TaskService;
import com.ujiuye.util.Messages;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Resource
    private TaskService taskService;

    @RequestMapping("addInfo")
    public String addInfo(Task task, HttpSession session){
        Employee employee = (Employee) session.getAttribute("activeUser");
        int emp_fk= 0;
        if(employee!=null){
            emp_fk=employee.getEid();
        }
        task.setEmpFk(emp_fk);
        task.setStatus(0);
        boolean flag = taskService.saveInfo(task);
        if(flag){
            return  "redirect:../task.jsp";
        }
        return "error.jsp";
    }


    // 查询经理分配的任务
    @RequestMapping("showTaskAndEmp")
    @ResponseBody
    public List<Task> showTaskAndEmp(HttpSession session){
        int eid= 0;
        Employee employee = (Employee) session.getAttribute("activeUser");
        if(employee!=null){
            eid=employee.getEid();
        }
        List<Task> taskAndEmp = taskService.getTaskAndEmp(eid);
        return taskAndEmp;
    }

    // 查询员工接收任务的
    @RequestMapping("showTaskAndEmp1")
    @ResponseBody
    public List<Task> showTaskAndEmp1(HttpSession session){
        int eid= 0;
        Employee employee = (Employee) session.getAttribute("activeUser");
        if(employee!=null){
            eid=employee.getEid();
        }
        List<Task> taskAndEmp1 = taskService.getTaskAndEmp1(eid);
        return taskAndEmp1;
    }

    // 任务状态的改变；
    @RequestMapping("updateStatus")
    @ResponseBody
    public Messages updateStatus(Task task ){
        boolean b = taskService.updateStatus(task);
        Messages msg = new Messages();
        if(b){
            msg.setAjaxstatus(true);
        }else{
            msg.setAjaxstatus(false);
        }
        return msg;
    }




 }
