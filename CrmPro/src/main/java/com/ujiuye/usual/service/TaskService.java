package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Task;

import java.util.List;

public interface TaskService {

    public  boolean saveInfo(Task task);


    public List<Task> getTaskAndEmp(Integer eid);
    // 查询员工的
    public List<Task> getTaskAndEmp1(Integer eid);

    //更新任务状态
    public boolean   updateStatus(Task task);

}
