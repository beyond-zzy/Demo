package com.ujiuye.auth.controller;

import com.ujiuye.auth.bean.Role;
import com.ujiuye.auth.bean.RoleExample;
import com.ujiuye.auth.bean.RoleSources;
import com.ujiuye.auth.mapper.RoleMapper;
import com.ujiuye.auth.mapper.RoleSourcesMapper;
import com.ujiuye.auth.service.RoleService;
import com.ujiuye.emp.bean.Dept;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @RequestMapping("addRole")
    public String addRole(String sourcesid,Role role){
        System.out.println(sourcesid+"=======");
        /*
        * 先添加角色 在去添加关系
        * */
        boolean b = roleService.addRole(sourcesid, role);
        if(b){
            return "role";
        }else{
            return  "error";
        }
    }


    @RequestMapping("getRole")
    @ResponseBody
    public List<Role> getRole(){
        RoleExample re = new RoleExample();
        RoleExample.Criteria cc = re.createCriteria();
        cc.andStatusEqualTo(1);
        return roleService.getRole(re);
    }

}
