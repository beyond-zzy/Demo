package com.ujiuye.bm.controller;

import com.ujiuye.bm.bean.Indexvalue;
import com.ujiuye.bm.service.IndexValueServie;
import com.ujiuye.emp.bean.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/bm")
public class IndexValueController {

    @Resource
    private IndexValueServie indexValueServie;

    @RequestMapping("/saveIndexValueInfo")
    public String saveIndexValueInfo(Date inStarttime, HttpSession session , Indexvalue indexvalue, @RequestParam("files") MultipartFile[] files){
        System.out.println(inStarttime);
        System.out.println(indexvalue.getInStarttime());
        String  path = "D:\\indexvalues";
        String truename = "";
        String trupath = "";
        if(files.length>0){
            for(MultipartFile mf : files){
                String uuidnfo = UUID.randomUUID().toString();
                String filename =  mf.getOriginalFilename();
                truename = uuidnfo+"_"+filename;
                trupath = path+"\\"+truename;
                File truefile = new File(trupath);
                try {
                    mf.transferTo(truefile);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            if(trupath.length()>0){
                indexvalue.setInFile(truename);
            }
            Employee emp = (Employee)session.getAttribute("activeUser");
            indexvalue.setEmpFk5(emp.getEid());
        }
        boolean b = indexValueServie.saveInfo(indexvalue);
        if(b){
            return "redirect:../indexvalue-base.jsp";
        }else{
            return "error";
        }
    }






}
