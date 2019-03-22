package com.ujiuye.usual.service;

import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.mapper.EmployeeMapper;
import com.ujiuye.usual.bean.Evaluate;
import com.ujiuye.usual.bean.EvaluateExample;
import com.ujiuye.usual.bean.Forumpost;
import com.ujiuye.usual.bean.ForumpostExample;
import com.ujiuye.usual.mapper.EvaluateMapper;
import com.ujiuye.usual.mapper.ForumpostMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ForumPostServiceImpl implements ForumPostService {

    @Resource
    private ForumpostMapper forumpostMapper;

    @Resource
     private EmployeeMapper employeeMapper;

    @Resource
    private EvaluateMapper evaluateMapper;

    @Override
    public boolean insertInfo(Forumpost forumpost) {

        int i = forumpostMapper.insertSelective(forumpost);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Forumpost> getForumpostByEid(ForumpostExample forumpostExample) {
        return forumpostMapper.selectByExample(forumpostExample);
    }
    // 获取当前对应id帖子,帖子对应所有评论，评论人,发帖人
    @Override
    public Forumpost getForumAndAllEval(Integer id) {
        //获取了这个帖子
        Forumpost forumpost = forumpostMapper.selectByPrimaryKey(id);
        // 获取发帖人
        Employee employee = employeeMapper.selectByPrimaryKey(forumpost.getEmpFk3());
        forumpost.setEmployee(employee);
        List<Evaluate> evaluates = itear1(forumpost.getForumid());
        forumpost.setEvaluates(evaluates);
        return forumpost;
    }

    //获取当前帖子的第一级评论 null,帖子id
    public  List<Evaluate> itear1(Integer id){
        EvaluateExample ee = new EvaluateExample();
        EvaluateExample.Criteria cc = ee.createCriteria();
        cc.andForumFkEqualTo(id);
        cc.andEvaidFkIsNull();
        // 第一级评论
        List<Evaluate> evaluates = evaluateMapper.selectByExample(ee);
        itear2(evaluates);
        return evaluates;
    }
    //获取下一级节点
    public  void itear2(List<Evaluate> evaluates){
        // 第一遍的时候第一级评论
         for(Evaluate ev :evaluates){
             Employee employee = employeeMapper.selectByPrimaryKey(ev.getEmpFk4());
             ev.setEmployee(employee);
             EvaluateExample newee = new EvaluateExample();
             EvaluateExample.Criteria newcc = newee.createCriteria();
             newcc.andEvaidFkEqualTo(ev.getEvaid());
             List<Evaluate> evaluates1 = evaluateMapper.selectByExample(newee);
             if(evaluates1.size()>0){
                 ev.setEvaluateList(evaluates1);
                 itear2(evaluates1);
             }
         }
    }





}
