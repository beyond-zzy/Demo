package com.ujiuye.auth.service;

import com.ujiuye.auth.bean.Sources;
import com.ujiuye.auth.bean.SourcesExample;

import java.util.List;

public interface SourcesService {

      public List<Sources> getOne(SourcesExample se);

      public boolean insertInfo(Sources sources);

      public  boolean  updateInfo(Sources sources);

      public  boolean deleteInfoByPk(Integer id);



}
