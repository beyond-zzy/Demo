package com.ujiuye.emp.service;

import com.ujiuye.auth.bean.EmpRole;
import com.ujiuye.auth.mapper.EmpRoleMapper;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.bean.EmployeeExample;
import com.ujiuye.emp.bean.QueryVo;
import com.ujiuye.emp.mapper.EmployeeMapper;
import com.ujiuye.pro.bean.Project;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements  EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private EmpRoleMapper empRoleMapper;


    @Override
    public List<Employee> getEmp(QueryVo queryVo) {
        return employeeMapper.getEmpbyPname(queryVo);
    }

    @Override
    public List<Employee> getEmpByPid() {
        return employeeMapper.getEmpBypid();
    }

    @Override
    public Employee getOne(String username, String password) {
        EmployeeExample ex = new EmployeeExample();
        EmployeeExample.Criteria cc = ex.createCriteria();
        cc.andUsernameEqualTo(username);
        cc.andPasswordEqualTo(password);
        List<Employee> employees = employeeMapper.selectByExample(ex);
        if(employees.size()>0){
            return employees.get(0);
        }
        return null;
    }

    public Employee getEmpAllInfo(Integer eid){
        return employeeMapper.getEmpAndArchives(eid);
    }

    // 非自身的人员信息
    @Override
    public List<Employee> getEmpExcludeSelf(Integer eid) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria cc = example.createCriteria();
        cc.andEidNotEqualTo(eid);
        return employeeMapper.selectByExample(example);
    }

    @Override
    public boolean addEmployee(Employee employee, Integer roleid) {
        int insert = employeeMapper.insert(employee);
        EmpRole er = new EmpRole();
        er.setEmpFk(employee.getEid());
        er.setRoleFk(roleid);
        er.setErdis(employee.getEname()+"的角色");
        int insert1 = empRoleMapper.insert(er);
        return insert1>0;
    }
}
