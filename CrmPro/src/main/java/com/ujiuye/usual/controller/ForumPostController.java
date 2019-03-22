package com.ujiuye.usual.controller;


import com.ujiuye.emp.bean.Employee;
import com.ujiuye.usual.bean.Forumpost;
import com.ujiuye.usual.bean.ForumpostExample;
import com.ujiuye.usual.service.ForumPostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/forum")
public class ForumPostController {

    @Resource
     private ForumPostService forumPostService;


     @RequestMapping("add")
      public String  add(Forumpost forumpost, HttpSession session){
          Employee emp =(Employee) session.getAttribute("activeUser");
          forumpost.setEmpFk3(emp.getEid());
         boolean b = forumPostService.insertInfo(forumpost);
         if(b){
             return "redirect:../forum-showmyself.jsp";
         }
         return "redirect:../forum.jsp";
      }

    // 显示当前登录用户发布的所有帖子
    @RequestMapping("getOneByEid")
    @ResponseBody
    public List<Forumpost> getOneByEid(HttpSession session){
        Employee emp =(Employee) session.getAttribute("activeUser");
        ForumpostExample fe = new ForumpostExample();
        ForumpostExample.Criteria cc = fe.createCriteria();
        cc.andEmpFk3EqualTo(emp.getEid());
        return forumPostService.getForumpostByEid(fe);
    }

    // 根据一个帖子的id查询当前的帖子和所有的评论
    @RequestMapping("getForumpostByid")
    public String getForumpostByid(Integer id,HttpSession session){
        System.out.println(id+"=============");
        /*
        * 接收帖子的id值，根据id值 查询帖子，查询当前帖子所有评论
        * */
        Forumpost forumAndAllEval = forumPostService.getForumAndAllEval(id);
        session.setAttribute("fe",forumAndAllEval);
        return "redirect:../forum-reply.jsp";
        //return forumAndAllEval;
    }




}
