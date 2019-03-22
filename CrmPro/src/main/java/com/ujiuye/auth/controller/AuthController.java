package com.ujiuye.auth.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.ujiuye.auth.service.SourcesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ujiuye.auth.bean.MyMessage;
import com.ujiuye.auth.bean.Sources;
import com.ujiuye.auth.bean.SourcesExample;
import com.ujiuye.auth.bean.SourcesExample.Criteria;
import com.ujiuye.auth.mapper.SourcesMapper;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Resource
	private SourcesService sourcesService;

	@RequestMapping("showAuth")
	@ResponseBody
	public List<Sources> showAuth() {
		SourcesExample se = new SourcesExample();
        Criteria cc = se.createCriteria();
        cc.andPidEqualTo(0);
        List<Sources> parents = sourcesService.getOne(se);
        queryChildren(parents.get(0));
        return parents;

	}
	// 递归查询子菜单
	private void queryChildren(Sources parents) {
        SourcesExample se1 = new SourcesExample();
        Criteria cc1 = se1.createCriteria();
        cc1.andPidEqualTo(parents.getId());
        List<Sources> childrens= sourcesService.getOne(se1);
        for(Sources se: childrens){
            queryChildren(se);
        }
        parents.setChildren(childrens);

    }

	// 添加节点
	@RequestMapping("add")
	public String add(Sources sources ) {
        sourcesService.insertInfo(sources);
		return "redirect:../pm.jsp";
	}


    // 更新节点
    @RequestMapping("updateInfo")
    public String updateInfo(Sources sources ) {
        sourcesService.updateInfo(sources);
        return "redirect:../pm.jsp";
    }


	// 编辑节点
	@RequestMapping("getOneById")
	public String getOneById(Integer id,Model model) {
	    /*
	    * 根据当前节点的id值，查询到该节点
	    * 放入到model域中
	    * */
        SourcesExample se1 = new SourcesExample();
        Criteria cc1 = se1.createCriteria();
        cc1.andIdEqualTo(id);
        model.addAttribute("onesource",sourcesService.getOne(se1).get(0)) ;
		return "pm-edit";
	}


	@RequestMapping("delete")
	@ResponseBody
	public MyMessage  delete(Integer id) {
        sourcesService.deleteInfoByPk(id);
        MyMessage msg = new MyMessage();
		msg.setSuccess(true);
		return msg;
	}

}
