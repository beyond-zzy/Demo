package com.ujiuye.emp.service;

import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.bean.EmployeeExample;
import com.ujiuye.emp.bean.QueryVo;
import com.ujiuye.pro.bean.Project;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getEmp(QueryVo queryVo);

    public List<Employee> getEmpByPid();

    public Employee getOne(String username ,String password);

    public Employee getEmpAllInfo(Integer eid);

    //查询不是自身的人员信息
    public List<Employee> getEmpExcludeSelf(Integer eid);


    // 添加员工信息
    public boolean  addEmployee(Employee employee,Integer roleid);


}
