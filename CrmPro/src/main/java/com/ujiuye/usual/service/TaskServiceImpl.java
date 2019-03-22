package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Task;
import com.ujiuye.usual.mapper.TaskMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskServiceImpl implements  TaskService {
    @Resource
    private TaskMapper taskMapper;
    @Override
    public boolean saveInfo(Task task) {
        int insert = taskMapper.insert(task);
        if(insert>0){
            return true;
        }
        return false;
    }
    @Override
    public List<Task> getTaskAndEmp(Integer eid) {
        return taskMapper.getTaskAndEmp(eid);
    }

    // 查询员工的
    @Override
    public List<Task> getTaskAndEmp1(Integer eid) {

        return taskMapper.getTaskAndEmp1(eid);
    }

    @Override
    public boolean updateStatus(Task task) {
        int i = taskMapper.updateByPrimaryKeySelective(task);
        if(i>0){
            return true;
        }
        return false;
    }


}
