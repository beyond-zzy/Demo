package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;

import java.util.List;

public interface  ProjectService {


    public boolean saveInfo(Project project);

    public List<Project> getInfo(ProjectExample projectExample);


}
