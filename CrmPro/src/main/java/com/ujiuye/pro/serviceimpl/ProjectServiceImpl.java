package com.ujiuye.pro.serviceimpl;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.mapper.ProjectMapper;
import com.ujiuye.pro.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    public boolean saveInfo(Project project){
        int insert = projectMapper.insert(project);
        if(insert>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Project> getInfo(ProjectExample projectExample) {
        if(projectExample == null){
            return projectMapper.getProByanalysisId();
        }else{
            return    projectMapper.selectByExample(projectExample);
        }
    }
}
