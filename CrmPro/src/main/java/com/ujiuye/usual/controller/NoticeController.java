package com.ujiuye.usual.controller;

import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @RequestMapping("saveinfo")
    public String saveInfo(Notice notice){
        notice.setNdate(new java.util.Date());
        boolean b = noticeService.saveInfo(notice);
        if(b){
            return "redirect:../notice.jsp";
        }
         return "error.jsp";
    }

    // 查询所有的内容
    @RequestMapping("showAll")
    @ResponseBody
    public List<Notice> showAll(){
      return noticeService.getAll();
    }


    // 查询main显示的通知内容
    @RequestMapping("showMainPage")
    @ResponseBody
    public List<Notice> showMainPage(){
        return noticeService.getMainPage();
    }

    // 查询一个通知内容
    @RequestMapping("showOneInfo")
    @ResponseBody
    public Notice showOneInfo(Integer  nid){
        System.out.println(nid+"====");
        return noticeService.getOneByPrimary(nid);
    }


}
