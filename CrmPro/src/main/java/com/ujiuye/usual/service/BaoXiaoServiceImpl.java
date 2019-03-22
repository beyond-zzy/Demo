package com.ujiuye.usual.service;

import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.mapper.EmployeeMapper;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.bean.BaoxiaoExample;
import com.ujiuye.usual.mapper.BaoxiaoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BaoXiaoServiceImpl implements  BaoXiaoService {

    @Resource
    private BaoxiaoMapper baoxiaoMapper;

    @Resource
    private EmployeeMapper employeeMapper;


    @Override
    public boolean saveInfo(Baoxiao baoxiao) {
        int insert = baoxiaoMapper.insertSelective(baoxiao);
        if(insert>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Baoxiao> show(BaoxiaoExample baoxiaoExample) {
        return baoxiaoMapper.selectByExample(baoxiaoExample);
    }


    // 按照报销单的状态码查询信息，包括员工的信息
    @Override
    public List<Baoxiao> showBaoxiaoAndEmp(int status) {
        BaoxiaoExample be = new BaoxiaoExample();
        BaoxiaoExample.Criteria cc = be.createCriteria();
        cc.andBxstatusEqualTo(status);
        List<Baoxiao> baoxiaos = baoxiaoMapper.selectByExample(be);
        for(Baoxiao bx:baoxiaos){
            Integer empFk = bx.getEmpFk();
            Employee employee = employeeMapper.selectByPrimaryKey(empFk);
            bx.setEmployee(employee);
        }
        return baoxiaos;
    }

    public boolean updateBaoxiao(Baoxiao bx){
        int i = baoxiaoMapper.updateByPrimaryKeySelective(bx);
        if(i>0){
            return true;
        }
        return false;
    }
}
