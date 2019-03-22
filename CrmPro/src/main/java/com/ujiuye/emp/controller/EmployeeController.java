package com.ujiuye.emp.controller;

import com.ujiuye.emp.bean.*;
import com.ujiuye.emp.mapper.DeptMapper;
import com.ujiuye.emp.mapper.LevelMapper;
import com.ujiuye.emp.mapper.PositionMapper;
import com.ujiuye.emp.service.EmployeeService;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.service.BaoXiaoService;
import com.ujiuye.util.Messages;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;
    @Resource
    private BaoXiaoService baoXiaoService;

    @Resource
    private PositionMapper positionMapper;
    @Resource
    private LevelMapper levelMapper;
    @Resource
    private DeptMapper deptMapper;






    @RequestMapping("showInfoByPname")
    @ResponseBody
    public List<Employee> showInfoByPname(){
        QueryVo qv = new QueryVo();
        qv.setPid(4);
        return employeeService.getEmp(qv);
    }

    @RequestMapping("getEmpByPid")
    @ResponseBody
    public List<Employee> getEmpByPid(){
        return employeeService.getEmpByPid();
    }

    //登录方法
    @RequestMapping("login")
    public String login(String username,String password,HttpSession session){
        Employee one = employeeService.getOne(username, password);
        if(one!=null){
             session.setAttribute("activeUser",one);
             return "redirect:../index.jsp";
        }else{
            return "redirect:../login.jsp";
        }
    }
    //审批报销
    @RequestMapping("shenpi")
    @ResponseBody
    public Messages shenpi(Baoxiao baoxiao){
        System.out.println(baoxiao.getBxremark()+"===");
        if(baoxiao.getResult().equals("")){
            baoxiao.setResult(null);
        }
        boolean b = baoXiaoService.updateBaoxiao(baoxiao);
        Messages msg = new Messages();
        if(b){
             msg.setAjaxstatus(true);
        }
        return msg;
    }

   // 根据我们的登录的主键值获取关联对象信息
     @RequestMapping("showEmpAllInfo")
     @ResponseBody
     public Employee showEmpAllInfo(HttpSession session){
         Employee  emp = (Employee)session.getAttribute("activeUser");
         return employeeService.getEmpAllInfo(emp.getEid());
     }


    //非登录用户的人员信息
    @RequestMapping("getEmpexcludeSelf")
    @ResponseBody
    public List<Employee> getEmpexcludeSelf(HttpSession session){
        Employee  emp = (Employee)session.getAttribute("activeUser");
        return employeeService.getEmpExcludeSelf(emp.getEid());
    }


    @RequestMapping("getPosition")
    @ResponseBody
    public List<Position> getPosition(){
        return positionMapper.selectByExample(null);
    }

    @RequestMapping("getLevel")
    @ResponseBody
    public List<Level> getLevel(){
        return levelMapper.selectByExample(null);
    }


    @RequestMapping("getDept")
    @ResponseBody
    public List<Dept> getDept(){
        return deptMapper.selectByExample(null);
    }

    //添加员工

    @RequestMapping("addEmp")
    public String  addEmp(Employee employee,Integer roleid){
        
        boolean b = employeeService.addEmployee(employee, roleid);
        if(b){
            return "user";
        }else{
            return "error";
        }
    }






}
