package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Msg;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/*
*  默认的quartz框架不能调用spring 容器中的对象内容
*   1： 我们自己在job定义需要的对象
*   2： 创建工具工厂，提供注入的功能。
* */
public class MsgJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 工作内容
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        Msg msg = (Msg)jobDataMap.get("msg");
        /*
        * jdbc连接
        * */
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crmpro", "root", "root");
            String sql = "insert into msg(sendp,recvp,msgcontent,msgtime) values(?,?,?,?)";
            PreparedStatement ps =   con.prepareStatement(sql);
            System.out.println(msg);
            ps.setInt(1,msg.getSendp());
            ps.setInt(2,msg.getRecvp());
            ps.setString(3,msg.getMsgcontent());
            ps.setDate(4,new Date(msg.getMsgtime().getTime()));
            ps.executeUpdate();
            con.close();
            ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
