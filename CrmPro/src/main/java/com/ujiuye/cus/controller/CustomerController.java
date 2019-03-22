package com.ujiuye.cus.controller;

import com.ujiuye.cus.bean.Customer;
import com.ujiuye.cus.bean.CustomerExample;
import com.ujiuye.cus.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodType;
import java.util.List;

@Controller
@RequestMapping("/cus")
public class CustomerController {
    /*
    * 1.8 jdk + 3.*
    * 1.5 jdk + 4.*
    * */
    @Resource
     private CustomerService customerService;

    @RequestMapping(value = "saveInfo")
    public String saveInfo(Customer customer, HttpServletRequest request ){
        customer.setAddtime(new java.util.Date());
        boolean flag = customerService.saveInfo(customer);
        if(flag){
            /*
            * 1: 跳转到页面，采用异步的方式加载内容
            * 2：使用springmvc 转发或者是重定向,采用域对象的方式完成数据的展示
            * */
            return "redirect:../customer.jsp";
        }else{
            return "error";
        }
    }

    @RequestMapping(value = "showInfo")
    @ResponseBody
    public List<Customer> showInfo(){
       return  customerService.showInfo(null);
    }

    @RequestMapping(value = "search")
    @ResponseBody
    public List<Customer> search(int mt,int mtime,String info){
        System.out.println(mt+mtime+info);
        CustomerExample ex = new CustomerExample();
        CustomerExample.Criteria cc = ex.createCriteria();
        if(mt==0){
          //没有约束条件
          cc.andComnameLike(info);// 公司名称模糊
          cc.andCompanypersonLike(info);//公司的负责人名称模糊  comname like value  and companyperson like value
        }else if(mt==1){
            cc.andComnameLike("%"+info+"%");// 公司名称模糊
        }else{
            cc.andCompanypersonLike("%"+info+"%");//公司的负责人名称模糊
        }
        if(mtime==1){
            ex.setOrderByClause("id");
        }

        return customerService.showInfo(ex);
    }







}
