package com.ujiuye.usual.controller;

import com.ujiuye.usual.bean.Archives;
import com.ujiuye.usual.service.ArchivesService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/archives")
public class ArchivesController {

    @Resource
    private ArchivesService archivesService;

    /*
    *
    * MultipartFile： 将上传的文件自动转换成输入流,流读取的就是上传文件的内容
    * ***.properties ---> Properties  load(InputStream)对象
    *
    * */
    @RequestMapping("addArchives")
    public String addArchives( MultipartFile files){
        CommonsMultipartFile cFile = (CommonsMultipartFile) files;
        DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
        try {
            InputStream inputStream = fileItem.getInputStream();
            List<Archives> list = new ArrayList<>();
            //创建新excel文档，通过我们输入流
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
            //循环遍历文档对象
            for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){
                //获取指定索引的页
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if(hssfSheet == null){
                    continue;
                }
                //循环当前页中的具体行
                for(int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++){
                    //根据索引获取具体的行,第一行为表头所以索引从1开始
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if(hssfRow != null){
                        //获取当前行指定索引的列对象
                        HSSFCell dnum = hssfRow.getCell(0);
                        HSSFCell landline = hssfRow.getCell(1);
                        HSSFCell school = hssfRow.getCell(2);
                        HSSFCell zhuanye = hssfRow.getCell(3);
                        HSSFCell sosperson = hssfRow.getCell(4);
                        HSSFCell biyedate = hssfRow.getCell(5);
                        HSSFCell zzmm = hssfRow.getCell(6);
                        HSSFCell minzu = hssfRow.getCell(7);
                        HSSFCell xueli = hssfRow.getCell(8);
                        HSSFCell email = hssfRow.getCell(9);
                        HSSFCell empFk = hssfRow.getCell(10);
                        HSSFCell remark = hssfRow.getCell(11);
                        HSSFCell hirdate = hssfRow.getCell(12);

                        Archives ac = new Archives();
                        ac.setDnum(dnum.getStringCellValue());
                        ac.setLandline(landline.getStringCellValue());
                        ac.setSchool(school.getStringCellValue());
                        ac.setZhuanye(zhuanye.getStringCellValue());
                        ac.setSosperson(sosperson.getStringCellValue());
                        ac.setBiyedate(biyedate.getDateCellValue());
                        ac.setZzmm(zzmm.getStringCellValue());
                        ac.setMinzu(minzu.getStringCellValue());
                        ac.setXueli(xueli.getStringCellValue());
                        ac.setEmail(email.getStringCellValue());
                        ac.setEmpFk((int)empFk.getNumericCellValue());
                        ac.setRemark(remark.getStringCellValue());
                        ac.setHirdate(hirdate.getDateCellValue());
                        list.add(ac);
                    }
                }
            }
            System.out.println(list.get(0));

            int i = archivesService.BatchAdd(list);
            if(i>0){
                return "redirect:../archives.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:../error.jsp";
    }
}
