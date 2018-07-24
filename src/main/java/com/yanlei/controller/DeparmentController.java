package com.yanlei.controller;

import com.alibaba.fastjson.JSON;
import com.yanlei.model.*;
import com.yanlei.service.DepartmentService;
import com.yanlei.service.NumberItemsService;
import com.yanlei.util.HssfRowUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping(value = "/yanlei")
public class DeparmentController {

    private Logger logger = LoggerFactory.getLogger(DeparmentController.class);

    @Autowired
    private NumberItemsService numberItemsService;
    @Autowired
    private DepartmentService departmentService;


    @RequestMapping(value = "/downloadDepartment")
    public void downloadZdpyc(HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = "C:\\MultipartFile\\" + "department.xlsx";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String filename = sdf.format(new Date());
            filename = new String(filename.getBytes("iso8859-1"), "UTF-8");
            File file = new File(path);
            //如果文件不存在
            if (!file.exists()) {
                //return false;
            }
            //设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename=" + new String((filename + ".xlsx").getBytes(), "utf-8"));
            //读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(path);
            OutputStream out = response.getOutputStream();
            byte buffer[] = new byte[1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区中
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            //关闭
            in.close();
            out.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 最多跑一次_分部门办件数的excel形式修改
     * @param response
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value = "/updateDepartment", produces = "text/json;charset=UTF-8")
    @ResponseBody
    //@CrossOrigin(origins = "*", maxAge = 3600) //解决前端跨域问题
    public String updateDepartment(HttpServletResponse response, HttpServletRequest request,
                                   @RequestParam(value = "file8") MultipartFile file) {
        //response.addHeader("Access-Control-Allow-Origin", "*");

        if (file == null) return null;
        String filePath = "C:\\MultipartFile\\" + file.getOriginalFilename();

        String filename = file.getOriginalFilename();

        long size = file.getSize();

        if (filename == null || ("").equals(filename) && size == 0) return null;

        File saveFile = new File(filePath);
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            logger.error("文件保存错误",e);
            e.printStackTrace();
        }
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }

        List<DepartmentQjw> departmentQjws = new ArrayList<DepartmentQjw>();
        List<DepartmentQwb> departmentQwbs = new ArrayList<DepartmentQwb>();
        List<DepartmentRdb> departmentRdbs = new ArrayList<DepartmentRdb>();
        List<DepartmentZfb> departmentZfbs = new ArrayList<DepartmentZfb>();
        List<DepartmentZx> departmentZxs = new ArrayList<DepartmentZx>();

        Zdpyc zdpyc = numberItemsService.findZdpyc();

        Zdpyc zdpyc1 = new Zdpyc();
        logger.info("查询得到的数据"+ JSON.toJSONString(zdpyc));
        Workbook wb;
        try {
            wb = WorkbookFactory.create(new FileInputStream(saveFile));
        } catch (Exception e) {
            logger.error("文件不是excel格式文件",e);
            return "error";
        }

        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            Sheet sheetAt = wb.getSheetAt(numSheet);
            if (sheetAt == null) {
                continue;
            }

            if (numSheet == 0) {
                Row row = sheetAt.getRow(0);
                if (row != null) {
                    String name = row.getCell(0).getStringCellValue();
                    if ("" != name && !name.equals(zdpyc1.getColumn8())) { //excel第一行 分区域事件数不同
                        zdpyc1.setColumn8(name);
                        zdpyc1.setId(1);
                    }
                }
                for (int rowNum = 2; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        DepartmentQwb departmentQwb = HssfRowUtil.convert(hssfRow, DepartmentQwb.class);
                        if (departmentQwb != null) {
                            departmentQwbs.add(departmentQwb);
                        }
                    }
                }
            } else if (numSheet == 1) {
                for (int rowNum = 1; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        DepartmentZfb departmentZfb = HssfRowUtil.convert(hssfRow, DepartmentZfb.class);
                        if (departmentZfb != null) {
                            departmentZfbs.add(departmentZfb);
                        }
                    }
                }
            } else if (numSheet == 2) {
                for (int rowNum = 1; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        DepartmentRdb departmentRdb = HssfRowUtil.convert(hssfRow, DepartmentRdb.class);
                        if (departmentRdb != null) {
                            departmentRdbs.add(departmentRdb);
                        }
                    }
                }
            }else if (numSheet == 3) {
                for (int rowNum = 1; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        DepartmentQjw departmentQjw = HssfRowUtil.convert(hssfRow, DepartmentQjw.class);
                        if (departmentQjw != null) {
                            departmentQjws.add(departmentQjw);
                        }
                    }
                }
            }else if (numSheet == 4) {
                for (int rowNum = 1; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        DepartmentZx departmentZx = HssfRowUtil.convert(hssfRow, DepartmentZx.class);
                        if (departmentZx != null) {
                            departmentZxs.add(departmentZx);
                        }
                    }
                }
            }
        }

        if (!wb.getSheetAt(0).getSheetName().equals(zdpyc.getColumn9()) || !wb.getSheetAt(1).getSheetName().equals(zdpyc.getColumn10())
                || !wb.getSheetAt(2).getSheetName().equals(zdpyc.getColumn11()) ||!wb.getSheetAt(3).getSheetName().equals(zdpyc.getColumn12())
                || !wb.getSheetAt(4).getSheetName().equals(zdpyc.getColumn13())) {
            zdpyc1.setColumn9(wb.getSheetAt(0).getSheetName());
            zdpyc1.setColumn10(wb.getSheetAt(1).getSheetName());
            zdpyc1.setColumn11(wb.getSheetAt(2).getSheetName());
            zdpyc1.setColumn12(wb.getSheetAt(3).getSheetName());
            zdpyc1.setColumn13(wb.getSheetAt(4).getSheetName());
            int num5 = numberItemsService.updateZdpyc(zdpyc1);
            if (num5 < 0) return null;
        }
        try {
            logger.info("-------->开始批量处理数据");
            departmentService.dealBathAdd(departmentQjws,departmentQwbs,departmentRdbs,departmentZfbs,departmentZxs);
            return "success";
        } catch (Exception e) {
            logger.error("错误信息提示:"+e);
            return "error";
        }
    }
}
