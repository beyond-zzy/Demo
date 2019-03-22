package com.ujiuye.auth.service;

import com.ujiuye.auth.bean.Role;
import com.ujiuye.auth.bean.RoleExample;
import com.ujiuye.auth.bean.RoleSources;
import com.ujiuye.auth.mapper.RoleMapper;
import com.ujiuye.auth.mapper.RoleSourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements  RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleSourcesMapper roleSourcesMapper;

    @Override
    public boolean addRole(String sourcesid, Role role) {
        /*
        *  添加角色表
        * */
        int i2 = roleMapper.insert(role);
        System.out.println(role.getRoleid());
        // 添加关系
        List<RoleSources> list = new ArrayList<>();

        String [] info = sourcesid.split(",");

        for(int i=0;i<info.length;i++){
            RoleSources rs = new RoleSources();
            rs.setRoleid(role.getRoleid());
            rs.setSid(Integer.parseInt(info[i]));
            rs.setRsdis("资源和我们的角色描述");
            list.add(rs);
        }
        int i3 = roleSourcesMapper.batchInsert(list);
        return i3>0;
    }

    @Override
    public List<Role> getRole(RoleExample roleExample) {
        return roleMapper.selectByExample(roleExample);
    }
}
