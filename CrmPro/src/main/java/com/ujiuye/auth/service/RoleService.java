package com.ujiuye.auth.service;

import com.ujiuye.auth.bean.Role;
import com.ujiuye.auth.bean.RoleExample;

import java.util.List;

public interface RoleService {


    public boolean addRole(String sourcesid,Role role);

    public List<Role> getRole(RoleExample roleExample);

}
