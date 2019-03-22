package com.ujiuye.usual.mapper;

import com.ujiuye.usual.bean.Task;
import com.ujiuye.usual.bean.TaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskMapper {
    int countByExample(TaskExample example);

    int deleteByExample(TaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Task record);

    int insertSelective(Task record);

    List<Task> selectByExample(TaskExample example);

    Task selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    // 查询经理分配
    List<Task> getTaskAndEmp( @Param("eid") Integer eid);

   // 查询员工的任务
    List<Task> getTaskAndEmp1( @Param("eid1") Integer eid1);
}