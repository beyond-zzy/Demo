package com.ujiuye.emp.mapper;

import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.bean.EmployeeExample;
import java.util.List;

import com.ujiuye.emp.bean.QueryVo;
import com.ujiuye.usual.bean.Evaluate;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    int countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer eid);

    int insert(Employee record);

    int insertSelective(Evaluate record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer eid);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getEmpbyPname(QueryVo queryVo);

    List<Employee> getEmpBypid();

    Employee  getEmpAndFun(Integer eid);


    Employee  getEmpAndArchives(Integer eid);



}