package com.ujiuye.usual.controller;

import com.ujiuye.emp.bean.Employee;
import com.ujiuye.usual.bean.Msg;
import com.ujiuye.usual.service.MsgJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/msg")
public class MessageController {


    @RequestMapping("saveMsg")
    @ResponseBody
     public String saveMsg(Msg msg, HttpSession session){
        try {

            //1.创建调用Scheduler的工厂
            SchedulerFactory sf = new StdSchedulerFactory();
            //2.从工厂中获取调度器实例
            Scheduler scheduler = sf.getScheduler();
            //3.创建JobDetail
            JobDetail jb = JobBuilder.newJob(MsgJob.class)
                    .withIdentity("ramJob", "ramGroup") //job 的name和group
                    .build();

            //任务运行的时间，SimpleSchedle类型触发器有效
            long time1 = msg.getMsgtime().getTime() - System.currentTimeMillis();
            System.out.println(time1);
            long time = System.currentTimeMillis() + time1;
            Date statTime = new Date(time);


            //4.创建Trigger
            //使用SimpleScheduleBuilder或者CronScheduleBuilder
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("ramTrigger", "ramGroup")
                    //指定时间后执行一次
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                    .startAt(statTime)  //设定任务的启动时间
                    .build();
            // jobdetail 通过一个map集合传递参数
            Employee emp = (Employee)session.getAttribute("activeUser");
            msg.setSendp(emp.getEid());
            jb.getJobDataMap().put("msg",msg);
            //5.注册任务和定时器
            scheduler.scheduleJob(jb,trigger);
            //6.启动 调度器
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:../message-give.jsp";
     }







}
