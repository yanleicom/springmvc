package com.yanlei.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.besjon.po.Dlists;
import com.besjon.po.Fenlist;
import com.besjon.po.JsonRootBean;
import com.yanlei.model.*;
import com.yanlei.service.PeopleFileService;
import com.yanlei.util.HssfRowUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: xiayuanlei
 * @Date: 2018/1/18 17:34
 */


@Controller
//@RequestMapping("/yanlei")
public class PeopleFileController {

    private static final Logger logger = LoggerFactory.getLogger(PeopleFileController.class);

    @Autowired
    private PeopleFileService peopleFileService;


    /**
     *    监控大屏_下城区政务工作品台
     *使用人数,活跃人数,公文办件量
     *每日平均收件,平均办件时长,公文办结情况 数据展示
     * @return 返回json数据
     */

    @RequestMapping(value = "/showPeopleFile", method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showLeftIndex(HttpServletResponse response, HttpServletRequest request
            /*, @RequestParam("callback")String callback*/) {
//        response.addHeader("Access-Control-Allow-Origin", "*");

        PeopleFile peopleFile = peopleFileService.findPeopleFileList();
        if (peopleFile != null) {
            return JSONObject.toJSONString(peopleFile);
        } else {
            logger.info("数据返回失败");
            return "error";
        }

    }

    /**
     *      监控大屏_下城区政务工作平台
     * 分部门办件数- 区委办,政府办,人大办,区纪委,政协数据展示
     * @param response
     * @return 封装json数据返回前台voe
     */
    @RequestMapping(value = "/showDapartmentNumber", method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showRigthIndex(HttpServletResponse response) {
//        response.addHeader("Access-Control-Allow-Origin", "*");
        List<Qjw> list = peopleFileService.findQjwList();
        List<Qwb> list1 = peopleFileService.findQwbList();
        List<Rlb> list2 = peopleFileService.findRlbList();
        List<Zfb> list3 = peopleFileService.findZfbList();
        List<Zx> list4 = peopleFileService.findZxList();
        Fbm fbm = peopleFileService.findFbm();

        if (list.size() != 0 && list1.size() != 0 && list2.size() != 0 && list3.size() != 0 && list4.size() != 0) {
            List<String> lists = new ArrayList<String>();
            lists.add(fbm.getColumn2());
            lists.add(fbm.getColumn3());
            lists.add(fbm.getColumn4());
            lists.add(fbm.getColumn5());
            lists.add(fbm.getColumn6());

            List<Fenlist> fenlists = new ArrayList<Fenlist>();
            for (int i = 0; i < list.size(); i++) {
                Fenlist fenlist = new Fenlist(list.get(i).getId(), list.get(i).getDepartment(), list.get(i).getNumber());
                fenlists.add(fenlist);
            }
            List<Fenlist> fenlists1 = new ArrayList<Fenlist>();
            for (int i = 0; i < list1.size(); i++) {
                Fenlist fenlist = new Fenlist(list1.get(i).getId(), list1.get(i).getDepartment(), list1.get(i).getNumber());
                fenlists1.add(fenlist);
            }
            List<Fenlist> fenlists2 = new ArrayList<Fenlist>();
            for (int i = 0; i < list2.size(); i++) {
                Fenlist fenlist = new Fenlist(list2.get(i).getId(), list2.get(i).getDepartment(), list2.get(i).getNumber());
                fenlists2.add(fenlist);
            }
            List<Fenlist> fenlists3 = new ArrayList<Fenlist>();
            for (int i = 0; i < list3.size(); i++) {
                Fenlist fenlist = new Fenlist(list3.get(i).getId(), list3.get(i).getDepartment(), list3.get(i).getNumber());
                fenlists3.add(fenlist);
            }
            List<Fenlist> fenlists4 = new ArrayList<Fenlist>();
            for (int i = 0; i < list4.size(); i++) {
                Fenlist fenlist = new Fenlist(list4.get(i).getId(), list4.get(i).getDepartment(), list4.get(i).getNumber());
                fenlists4.add(fenlist);
            }

            List<Dlists> dlistsList = new ArrayList<Dlists>();
            Dlists dlists = new Dlists();
            dlists.setFenlist(fenlists);
            Dlists dlists1 = new Dlists();
            dlists1.setFenlist(fenlists1);
            Dlists dlists2 = new Dlists();
            dlists2.setFenlist(fenlists2);
            Dlists dlists3 = new Dlists();
            dlists3.setFenlist(fenlists3);
            Dlists dlists4 = new Dlists();
            dlists4.setFenlist(fenlists4);
            dlistsList.add(dlists1);
            dlistsList.add(dlists3);
            dlistsList.add(dlists2);
            dlistsList.add(dlists);
            dlistsList.add(dlists4);

            JsonRootBean jsonRootBean = new JsonRootBean();
            jsonRootBean.setLists(lists);
            jsonRootBean.setDlists(dlistsList);
            jsonRootBean.setColumn1(fbm.getColumn1());
            jsonRootBean.setColumn2(fbm.getColumn7());
            jsonRootBean.setColumn3(fbm.getColumn8());
            jsonRootBean.setColumn4(fbm.getColumn9());
            logger.info("数据返回成功");
            return JSON.toJSONString(jsonRootBean);
        } else {
            logger.info("数据返回失败");
            return "error";
        }
    }

    /**
     * @Author: xiayuanlei
     * @Date: 2018/1/29 9:44
     * 监控大屏_下城区政务工作平台
     * 使用人数,活跃人数,公文办件量
     *每日平均收件,平均办件时长,公文办结情况 数据修改
     */

    @RequestMapping(value = "/updatePeopleFile", produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String updatePeopleFile(HttpServletResponse response, PeopleFile peopleFile, Model model) {
//        response.addHeader("Access-Control-Allow-Origin", "*");

        //System.out.println( peopleFile.getColumn1()+ peopleFile.getUsenumber() + peopleFile.getActivenumber());

        int num = peopleFileService.updatePeopleFile(peopleFile);
        if (num > 0) {
            model.addAttribute("success", num);
            // return "zhengwu";
            return "success";
        }
        return "error";
    }

    /**
     *
     * @param response
     * @param request
     * @param file  excel文件上传修改 分部门办件数表格
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */

    @RequestMapping(value = "/updateQjw", produces = "text/json;charset=UTF-8")
    @ResponseBody
//    @CrossOrigin(origins = "*", maxAge = 3600) //解决前端跨域问题
    public String updateQjw(HttpServletResponse response, HttpServletRequest request,
                            @RequestParam(value = "file") MultipartFile file) throws IOException, InvalidFormatException {

        if (file == null) return null;
       /* String filename = file.getOriginalFilename();
        if (!filename.endsWith("xls") || !filename.endsWith("xlsx")){
            logger.error(filename + "不是excel文件");
            return null;
        }*/
        String filePath = "C:\\MultipartFile\\" + file.getOriginalFilename();
        //String filePath = request.getSession().getServletContext().getRealPath("\\upload")+ file.getOriginalFilename();
        String filename = file.getOriginalFilename();
        //System.out.println(filename);
        long size = file.getSize();
        // System.out.println(size);
        if (filename == null || ("").equals(filename) && size == 0) return null;

        File saveFile = new File(filePath);
        file.transferTo(saveFile);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }

        List<Qwb> qwbs = new ArrayList<Qwb>();
        List<Zfb> zfbs = new ArrayList<Zfb>();
        List<Rlb> rlbs = new ArrayList<Rlb>();
        List<Qjw> qjws = new ArrayList<Qjw>();
        List<Zx> zxs = new ArrayList<Zx>();

        Qwb qwb = null;
        Zfb zfb = null;
        Rlb rlb = null;
        Qjw qjw = null;
        Zx zx = null;

        Fbm fbm = peopleFileService.findFbm();
        logger.info("查询得到的数据"+JSON.toJSONString(fbm));

        Fbm fbm1 = new Fbm();
        // 1、 加载Excel文件对象2003 2007都可以
        Workbook wb = WorkbookFactory.create(new FileInputStream(saveFile));
        // 2、 读取一个sheet
        //Sheet sheetAt1 = wb.getSheetAt(0);
        //logger.info("excelsheet名称"+wb.getSheetAt(0).getSheetName(),wb.getSheetAt(1).getSheetName());
        //处理删除所有原先的数据

        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            Sheet sheetAt = wb.getSheetAt(numSheet);
            if (sheetAt == null) {
                continue;
            }
            if (numSheet == 0) {
                Row row = sheetAt.getRow(0);
                if (row != null) {
                    String name = row.getCell(0).getStringCellValue();
                    if ("" != name && !name.equals(fbm.getColumn1())) { //excel第一行 分区域事件数不同
                        fbm1.setColumn1(name);
                        fbm1.setId(1);
                    }

                }
                for (int rowNum = 2; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        Cell cell1 = hssfRow.getCell(0);
                        Cell cell2 = hssfRow.getCell(1);
                        if (cell1!= null && cell2 != null &&cell1.getCellType() != Cell.CELL_TYPE_BLANK &&
                                cell2.getCellType() != Cell.CELL_TYPE_BLANK) {
                            qwb = new Qwb();
                            qwb.setDepartment(cell1.getStringCellValue());
                            long number = Math.round(cell2.getNumericCellValue());
                            qwb.setNumber(number);
                            qwb.setUpdatatime(new Date());
                            qwbs.add(qwb);
                        }
                    }
                }
            } else if (numSheet == 1) {
                //按照sheet表格式添加数据
                String sheetName = sheetAt.getSheetName();
                //第一个sheet修改数据
                for (int rowNum = 1; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        Cell cell1 = hssfRow.getCell(0);
                        Cell cell2 = hssfRow.getCell(1);
                        if (cell1!= null && cell2 != null && cell1.getCellType() != Cell.CELL_TYPE_BLANK &&
                                cell2.getCellType() != Cell.CELL_TYPE_BLANK) {
                            zfb = new Zfb();
                            zfb.setDepartment(cell1.getStringCellValue());
                            long number = Math.round(cell2.getNumericCellValue());
                            zfb.setNumber(number);
                            zfb.setUpdatatime(new Date());
//                       zfb.setId(rowNum);
                            zfbs.add(zfb);
                        }
                    }
                }
            } else if (numSheet == 2) {
                // 循环行Row
                for (int rowNum = 1; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        rlb = HssfRowUtil.convert(hssfRow, Rlb.class);
                        if (rlb != null){
                            rlbs.add(rlb);
                        }
                        /*Cell cell1 = hssfRow.getCell(0);
                        Cell cell2 = hssfRow.getCell(1);
                        if (cell1!= null && cell2 != null && cell1.getCellType() != Cell.CELL_TYPE_BLANK &&
                                cell2.getCellType() != Cell.CELL_TYPE_BLANK) {
                            rlb = new Rlb();
                            rlb.setDepartment(cell1.getStringCellValue());
                            long number = Math.round(cell2.getNumericCellValue());
                            rlb.setNumber(number);
                            rlb.setUpdatatime(new Date());
                            rlbs.add(rlb);
                        }*/
                    }
                }
            } else if (numSheet == 3) {
                // 循环行Row
                for (int rowNum = 1; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        Cell cell1 = hssfRow.getCell(0);
                        Cell cell2 = hssfRow.getCell(1);
                        if (cell1!= null && cell2 != null &&cell1.getCellType() != Cell.CELL_TYPE_BLANK &&
                                cell2.getCellType() != Cell.CELL_TYPE_BLANK) {
                            qjw = new Qjw();
                            qjw.setDepartment(cell1.getStringCellValue());
                            long number = Math.round(cell2.getNumericCellValue());
                            qjw.setNumber(number);
                            qjw.setUpdatatime(new Date());
                            qjws.add(qjw);
                        }
                    }
                }
            } else if (numSheet == 4) {
                // 循环行Row
                for (int rowNum = 1; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        Cell cell1 = hssfRow.getCell(0);
                        Cell cell2 = hssfRow.getCell(1);
                        if (cell1!= null && cell2 != null &&(cell1.getCellType() != Cell.CELL_TYPE_BLANK ||
                                cell2.getCellType() != Cell.CELL_TYPE_BLANK)) {
                            zx = new Zx();
                            zx.setDepartment(cell1.getStringCellValue());
                            long number = Math.round(cell2.getNumericCellValue());
                            zx.setNumber(number);
                            zx.setUpdatatime(new Date());
                            zxs.add(zx);
                        }
                    }
                }
            } else {

            }
        }

        if (!wb.getSheetAt(0).getSheetName().equals(fbm.getColumn2()) || !wb.getSheetAt(1).getSheetName().equals(fbm.getColumn3())
                || !wb.getSheetAt(2).getSheetName().equals(fbm.getColumn4()) || !wb.getSheetAt(3).getSheetName().equals(fbm.getColumn5())
                || !wb.getSheetAt(4).getSheetName().equals(fbm.getColumn6())) {
            fbm1.setColumn2(wb.getSheetAt(0).getSheetName());
            fbm1.setColumn3(wb.getSheetAt(1).getSheetName());
            fbm1.setColumn4(wb.getSheetAt(2).getSheetName());
            fbm1.setColumn5(wb.getSheetAt(3).getSheetName());
            fbm1.setColumn6(wb.getSheetAt(4).getSheetName());
            //fbm1.setId(1);
            int num5 = peopleFileService.updateFbm(fbm1);
            if (num5 < 0) return null;
        }
        try {
            logger.info("-------->开始批量处理数据");
            peopleFileService.dealBathAdd(qjws, zfbs, rlbs, zxs, qwbs);
            return "success";
        } catch (Exception e) {
            logger.error("错误信息"+e);
            return "error";
        }
    }

//小林政务公开季度excel文件上传写入数据库 !!! 看第二季度格式
    @RequestMapping(value = "/addexcel", produces = "text/json;charset=UTF-8")
    @ResponseBody
//    @CrossOrigin(origins = "*", maxAge = 3600) //解决前端跨域问题
    public String addexcel(@RequestParam(value = "file77") MultipartFile file)
            throws IOException, InvalidFormatException {
        if (file == null) return null;
        String filePath = "C:\\MultipartFile\\" + file.getOriginalFilename();
        String filename = file.getOriginalFilename();
        //System.out.println(filename);
        long size = file.getSize();
        //System.out.println(size);
        List<GovernmentDepartment> page = new ArrayList<GovernmentDepartment>();
        GovernmentDepartment governmentDepartmen = null;
        GovernmentDepartment governmentDepartmen2 = null;
        GovernmentDepartment governmentDepartmen3 = null;
        if (filename == null || ("").equals(filename) && size == 0) return null;
        File saveFile = new File(filePath);
        file.transferTo(saveFile);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        // 1、 加载Excel文件对象2003 2007都可以
        Workbook wb = WorkbookFactory.create(new FileInputStream(saveFile));
        // 2、 读取一个sheet
        Sheet sheetAt1 = wb.getSheetAt(0);
            for (int rowNum = 2; rowNum <= sheetAt1.getLastRowNum(); rowNum++) {
                Row hssfRow = sheetAt1.getRow(rowNum);
                if (hssfRow != null) {
                    Cell cell1 = hssfRow.getCell(0);
                    Cell cell2 = hssfRow.getCell(1);
                    Cell cell3 = hssfRow.getCell(2);
                    Cell cell4 = hssfRow.getCell(3);
                    if (cell1!= null && cell2 != null &&cell1.getCellType() != Cell.CELL_TYPE_BLANK &&
                            cell2.getCellType() != Cell.CELL_TYPE_BLANK && cell3!= null && cell4 != null
                            &&cell3.getCellType() != Cell.CELL_TYPE_BLANK &&
                            cell4.getCellType() != Cell.CELL_TYPE_BLANK) {
                        governmentDepartmen = new GovernmentDepartment();
                        governmentDepartmen2 = new GovernmentDepartment();
                        governmentDepartmen3 = new GovernmentDepartment();

                        governmentDepartmen.setName("网站栏目公开数");
                        governmentDepartmen.setIntValue(Math.round(cell2.getNumericCellValue()));
                        governmentDepartmen.setAcquisitionTime(new Date());
                        governmentDepartmen.setCycle("第二季度");
                        governmentDepartmen.setUnit("件");
                        governmentDepartmen.setLevel("2018年第二季度政务公开统计");
                        governmentDepartmen.setLevel2(cell1.getStringCellValue());
                        governmentDepartmen.setSource("小林");

                        governmentDepartmen2.setName("信息公开公开数");
                        governmentDepartmen2.setIntValue(Math.round(cell3.getNumericCellValue()));
                        governmentDepartmen2.setAcquisitionTime(new Date());
                        governmentDepartmen2.setCycle("第二季度");
                        governmentDepartmen2.setUnit("件");
                        governmentDepartmen2.setLevel("2018年第二季度政务公开统计");
                        governmentDepartmen2.setLevel2(cell1.getStringCellValue());
                        governmentDepartmen2.setSource("小林");


                        governmentDepartmen3.setName("总量");
                        governmentDepartmen3.setIntValue(Math.round(cell4.getNumericCellValue()));
                        governmentDepartmen3.setAcquisitionTime(new Date());
                        governmentDepartmen3.setCycle("第二季度");
                        governmentDepartmen3.setUnit("件");
                        governmentDepartmen3.setLevel("2018年第二季度政务公开统计");
                        governmentDepartmen3.setLevel2(cell1.getStringCellValue());
                        governmentDepartmen3.setSource("小林");
                        page.add(governmentDepartmen);
                        page.add(governmentDepartmen2);
                        page.add(governmentDepartmen3);
                    }
                }
            }

            if (page.size()>0 &&page!=null){
                Integer num = peopleFileService.insertPageList(page);
                if (num>0){
                    return "ok";
                }else {
                    return "notOk";
                }
            }
        return null;
    }
}
