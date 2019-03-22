package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.service.AttachmentService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {

    @Resource
    private AttachmentService attachmentService;

    @RequestMapping("saveInfo")
    public String saveInfo(@RequestParam("files") MultipartFile[] files , Attachment attachment){
            String  path = "D:\\attachments";
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
                    attachment.setPath(truename);
                }
            }
        boolean b = attachmentService.saveInfo(attachment);
        if(b){
            return "redirect:/attachment/showAtts";
        }else{
            return "error";
        }
    }

    @RequestMapping("showAtts")
    public String showAtts(HttpSession session ){
        //List<Attachment> list =  attachmentService.showAll(null);
        List<Attachment> list = attachmentService.getAddAndPro();
        session.setAttribute("list",list);
        return "redirect:../project-file.jsp";
    }

    @RequestMapping("output")
    public String output(HttpSession session ){
        List<Attachment> list = (List<Attachment>)session.getAttribute("list");
        // 导出操作的核心代码  pio java代码操作excel表格
       //创建新excel文档，07版本之前均可以这么写
        HSSFWorkbook workBook = new HSSFWorkbook();
        //新建工作表
        HSSFSheet sheet = workBook.createSheet("第一页");
        //设置单元格的高度
        sheet.setColumnWidth(0, 2500);
        sheet.setColumnWidth(1, 5000);




        //新建行
        HSSFRow row = sheet.createRow(0);
        //第一行中有几个字段
        HSSFCell cell[] = new HSSFCell[6];
        for(int i = 0; i < cell.length; i++){
            //取第一行第一列
            cell[i] = row.createCell(i);
        }
        //给第一行所有列赋值
        cell[0].setCellValue("编号");
        cell[1].setCellValue("附件名称");
        cell[2].setCellValue("项目名称");
        cell[3].setCellValue("备注");
        cell[4].setCellValue("文件名称");
        cell[5].setCellValue("描述");

        for(int i=0;i<list.size();i++){
            //新建行
            HSSFRow row1 = sheet.createRow(i+1);
            //第一行中有几个字段
            HSSFCell cell1[] = new HSSFCell[6];
            for(int j = 0; j < cell1.length; j++){
                //取第一行第一列
                cell1[j] = row1.createCell(j);
            }
            Attachment att = list.get(i);
            cell1[0].setCellValue(att.getId());
            cell1[1].setCellValue(att.getAttname());
            cell1[2].setCellValue(att.getProject().getPname());
            cell1[3].setCellValue(att.getRemark());
            cell1[4].setCellValue(att.getAttdis());
            String info = att.getPath();
            int index = info.indexOf("_");
            cell1[5].setCellValue(info.substring(index+1));
        }
        CellRangeAddress cc = new  CellRangeAddress(0,5,0,5);
        //设置输出内容的表格样式
        RegionUtil.setBorderLeft(1, cc, sheet,workBook);
        RegionUtil.setBorderBottom(1, cc, sheet, workBook);
        RegionUtil.setBorderRight(1, cc, sheet, workBook);
        RegionUtil.setBorderTop(1, cc, sheet, workBook);

        try {
            File file = new File("C:\\Users\\Administrator\\Desktop\\项目\\03\\attatchmentinfo.xls");
            FileOutputStream fos = new FileOutputStream(file);
            workBook.write(fos);
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:../project-file.jsp";
    }






    //文件下载
    @RequestMapping("download")
    public ResponseEntity<byte[]> download(String path){
           String filename = "";
           String  downpath = "D:\\attachments\\"+path;
           int index = 0;
        try {
            index = path.indexOf("_");
            filename = path.substring(index+1);
            //文件名乱码问题
            String newName = new String(filename.getBytes("GBK"),"ISO-8859-1");
            System.out.println(filename+"\t"+newName);
            File file = new File(downpath);
            //文件头
            HttpHeaders header = new HttpHeaders();
            // 下载文件的文件名字
            header.setContentDispositionFormData("attachment",newName);
            // MIME:类型  response.setContentType(text/xml); application/json image/jpeg  text/html
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // springmvc 提供的下载文件的工具类
            ResponseEntity<byte[]> entry = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),header, HttpStatus.OK);
            return entry;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  null;
    }


}
