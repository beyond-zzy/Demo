package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Module;
import com.ujiuye.pro.bean.ModuleExample;

import java.util.List;

public interface ModuleService {

      public boolean saveModInfo(Module module);

      public List<Module> getModule(ModuleExample example);


}
