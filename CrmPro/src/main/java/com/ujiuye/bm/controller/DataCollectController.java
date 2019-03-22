package com.ujiuye.bm.controller;

import com.ujiuye.bm.bean.Datacollect;
import com.ujiuye.bm.bean.DatacollectQueryVo;
import com.ujiuye.bm.service.DataCollectService;
import com.ujiuye.usual.bean.Archives;
import com.ujiuye.util.DateUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bm")
public class DataCollectController {

     @Resource
      private DataCollectService dataCollectService;

    @RequestMapping("addData")
    public String addData( MultipartFile files){
        CommonsMultipartFile cFile = (CommonsMultipartFile) files;
        DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
        try {
            InputStream inputStream = fileItem.getInputStream();
            List<Datacollect> list = new ArrayList<Datacollect>();
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
                        HSSFCell dacname = hssfRow.getCell(0);
                        HSSFCell daturnover = hssfRow.getCell(1);
                        HSSFCell datime = hssfRow.getCell(2);
                        HSSFCell dabusiness = hssfRow.getCell(3);
                        HSSFCell dasuperiority = hssfRow.getCell(4);
                        HSSFCell dainforiority = hssfRow.getCell(5);
                        HSSFCell dasort = hssfRow.getCell(6);
                        HSSFCell empcount = hssfRow.getCell(7);
                        HSSFCell buildtime = hssfRow.getCell(8);
                        HSSFCell remark = hssfRow.getCell(9);
                        HSSFCell daother = hssfRow.getCell(10);

                        Datacollect d = new Datacollect();

                        d.setDacname(dacname.getStringCellValue());
                        d.setDaturnover((double)(daturnover.getNumericCellValue()));
                        d.setBuildtime(buildtime.getDateCellValue());
                        d.setDabusiness(dabusiness.getStringCellValue());
                        d.setDainforiority(dainforiority.getStringCellValue());
                        d.setDaother(daother.getStringCellValue());
                        d.setDasort((int)dasort.getNumericCellValue());
                        d.setDasuperiority(dasuperiority.getStringCellValue());
                        d.setDatime(datime.getDateCellValue());
                        d.setEmpcount((int)empcount.getNumericCellValue());
                        d.setRemark(remark.getStringCellValue());
                        list.add(d);
                    }
                }
            }
            System.out.println(list.get(0));

            boolean flag = dataCollectService.BatchInsert(list);
            if(flag){
                return "redirect:../duibiao-base.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:../error.jsp";
    }

    @RequestMapping("getInfoGroup")
    @ResponseBody
    public List<Datacollect>  getInfoGroup(){
        return dataCollectService.getInfoGroup();
    }

    // 查询企业的最新数据
    @RequestMapping("getOneByCname")
    @ResponseBody
    public Datacollect getOneByCname(String cname){
        return dataCollectService.getOneByCname(cname);
    }


    // 提供浪潮集团14年到18年的营业额数据
    @RequestMapping("showPng")
    @ResponseBody
    public List<Datacollect> showPng(){
        // SELECT * FROM datacollect WHERE dacname='浪潮集团' AND datime BETWEEN '2014-01-01' AND '2018-12-31' ORDER BY datime;
        DatacollectQueryVo vo = new DatacollectQueryVo();
        vo.setDacname("浪潮集团");
        vo.setStartTime(DateUtil.getDate("2014-01-01"));
        vo.setEndTime(DateUtil.getDate("2018-12-31"));
        List<Datacollect> png = dataCollectService.getPng(vo);
       return png;
    }


}
