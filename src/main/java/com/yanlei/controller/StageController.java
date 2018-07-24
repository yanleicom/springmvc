package com.yanlei.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.besjon.po.*;
import com.yanlei.model.*;
import com.yanlei.service.PeopleFileService;
import com.yanlei.service.PeopleService;
import com.yanlei.service.QysjService;
import com.yanlei.util.HssfRowUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import java.util.List;

/**
 * 2 * @Author: xiayuanlei
 * 3 * @Date: 2018/1/23 13:09
 * 4监控大屏_四个平台
 */
@Controller
//@RequestMapping("/yanlei")
public class StageController {
    private static final Logger log = LoggerFactory.getLogger(StageController.class);
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private QysjService qysjService;

    /**
     * 四个平台 - 总事件数,分区域事件数(年,月,日)数据展示
     * @param response
     * @return
     */
    @RequestMapping(value = "/showRegion", method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showRegion(HttpServletResponse response) {
//        response.addHeader( "Access-Control-Allow-Origin", "*" );

        Sgpt sgpt = peopleService.findSgpt();//四个平台页面div头数据展示
        List<Qysj> qysjYear = peopleService.findQysjYear();//年月日数据展示
        List<Qysj> qysjMonth = peopleService.findQysjMonth();
        List<Qysj> qysjDay = peopleService.findQysjDay();

        List<String> lists = new ArrayList<String>();
        lists.add(sgpt.getColumn13());//年 月 日
        lists.add(sgpt.getColumn14());
        lists.add(sgpt.getColumn15());


        List<Fenlist2> fenlists = new ArrayList<Fenlist2>();
        for (int i = 0; i < qysjYear.size(); i++) {
            Fenlist2 fenlist = new Fenlist2(qysjYear.get(i).getId(), qysjYear.get(i).getColumn1(), qysjYear.get(i).getColumn2(), qysjYear.get(i).getColumn3());
            fenlists.add(fenlist);
        }
        List<Fenlist2> fenlists1 = new ArrayList<Fenlist2>();
        for (int i = 0; i < qysjMonth.size(); i++) {
            Fenlist2 fenlist = new Fenlist2(qysjMonth.get(i).getId(), qysjMonth.get(i).getColumn1(), qysjMonth.get(i).getColumn2(), qysjMonth.get(i).getColumn3());
            fenlists1.add(fenlist);
        }
        List<Fenlist2> fenlists2 = new ArrayList<Fenlist2>();
        for (int i = 0; i < qysjDay.size(); i++) {
            Fenlist2 fenlist = new Fenlist2(qysjDay.get(i).getId(), qysjDay.get(i).getColumn1(), qysjDay.get(i).getColumn2(), qysjDay.get(i).getColumn3());
            fenlists2.add(fenlist);
        }

        List<Dlists2> dlistsList = new ArrayList<Dlists2>();
        Dlists2 dlists = new Dlists2();
        dlists.setFenlist(fenlists);
        Dlists2 dlists1 = new Dlists2();
        dlists1.setFenlist(fenlists1);
        Dlists2 dlists2 = new Dlists2();
        dlists2.setFenlist(fenlists2);

        dlistsList.add(dlists);
        dlistsList.add(dlists1);
        dlistsList.add(dlists2);

        JsonRootBean3 jsonRootBean = new JsonRootBean3();
        jsonRootBean.setLists(lists);
        jsonRootBean.setDlists(dlistsList);
        jsonRootBean.setTotal(sgpt.getColumn2());
        jsonRootBean.setName(sgpt.getColumn1());
        jsonRootBean.setColumn3(sgpt.getColumn3());
        return JSON.toJSONString(jsonRootBean);
    }

    @Autowired
    private PeopleFileService peopleFileService;

    /**
     * 四个平台 - 分静态事件数 - 分类型事件数 - 分部门事件数 数据展示
     * @param response
     * @return
     */

    @RequestMapping(value = "/showCation", method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showCation(HttpServletResponse response) {
//        response.addHeader("Access-Control-Allow-Origin", "*");

        Sgpt sgpt = peopleService.findSgpt();
        List<Flxsj> flxsjs = peopleService.findFlxsj();
        //List<Qjw> lists = peopleFileService.findQjwLists();

        List<Fbmsj> fbmsjList = peopleFileService.findFbmsj();

        List<String> arr = new ArrayList<String>();
        List<Long> array = new ArrayList<Long>();

        for (int i = 0; i < fbmsjList.size(); i++) {
            arr.add(fbmsjList.get(i).getName());
        }
        for (int i = 0; i < fbmsjList.size(); i++) {
            array.add(fbmsjList.get(i).getValue());
        }

        List<Fenlist4> fenlist = new ArrayList<Fenlist4>();
        Fenlist4 fenlist4 = new Fenlist4(sgpt.getId(), sgpt.getColumn7(), sgpt.getColumn8().longValue());
        Fenlist4 fenlist5 = new Fenlist4(sgpt.getId(), sgpt.getColumn10(), sgpt.getColumn9().longValue());
        fenlist.add(fenlist4);
        fenlist.add(fenlist5);

        List<Fenlist4> fenlist1 = new ArrayList<Fenlist4>();
        for (int i = 0; i < flxsjs.size(); i++) {
            Fenlist4 fenlist6 = new Fenlist4(flxsjs.get(i).getId(), flxsjs.get(i).getName(), flxsjs.get(i).getValue());
            fenlist1.add(fenlist6);
        }

        List<Dlists4> dlists4s = new ArrayList<Dlists4>();
        Dlists4 dlists4 = new Dlists4();
        dlists4.setFenlist(fenlist);
        Dlists4 dlists5 = new Dlists4();
        dlists5.setFenlist(fenlist1);

        dlists4s.add(dlists4);
        dlists4s.add(dlists5);

        JsonRootBean4 jsonRootBean4 = new JsonRootBean4();
        jsonRootBean4.setLists(arr);
        jsonRootBean4.setLongList(array);
        jsonRootBean4.setDlists(dlists4s);
        jsonRootBean4.setName(sgpt.getColumn6());
        return JSON.toJSONString(jsonRootBean4);
    }

    /**
     * @Author: x
     * @Date: Created in 14:31 2018/2/23
     * 四个平台 - 分区域事件数 excel 修改
     * file3 excel文件上传
     */

    @RequestMapping(value = "/updateRegion", produces = "text/json;charset=UTF-8")
    @ResponseBody
//@CrossOrigin(origins = "*", maxAge = 3600) //解决前端跨域问题
    public String updateRegion(HttpServletResponse response, HttpServletRequest request,
                               @RequestParam(value = "file3") MultipartFile file) throws IOException, InvalidFormatException {

        if (file == null) return null;
       /* String filename = file.getOriginalFilename();
        if (!filename.endsWith("xls") || !filename.endsWith("xlsx")){
            logger.error(filename + "不是excel文件");
            return null;
        }*/
        String filePath = "C:\\MultipartFile\\" + file.getOriginalFilename();
        //String filePath1 = request.getSession().getServletContext().getRealPath("\\upload")+ file.getOriginalFilename();
        //System.out.println(filePath1);

        String filename = file.getOriginalFilename();

        long size = file.getSize();

        if (filename == null || ("").equals(filename) && size == 0) return null;

        File saveFile = new File(filePath);
        file.transferTo(saveFile);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }

        List<Qysj> qysjYear = new ArrayList<Qysj>();
        List<Qysj> qysjMonth = new ArrayList<Qysj>();
        List<Qysj> qysjDay = new ArrayList<Qysj>();

        Sgpt sgpt = peopleService.findSgpt();
        Sgpt sgpt1 = new Sgpt();
        // 1、 加载Excel文件对象2003 2007都可以
        Workbook wb = WorkbookFactory.create(new FileInputStream(saveFile));
        // 2、 读取一个sheet
        //Sheet sheetAt1 = wb.getSheetAt(0);

        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            Sheet sheetAt = wb.getSheetAt(numSheet);
            if (sheetAt == null) {
                continue;
            }

            if (numSheet == 0) {
                Row row = sheetAt.getRow(0);
                if (row != null) {
                    String name = row.getCell(0).getStringCellValue();
                    if ("" != name && !name.equals(sgpt.getColumn3())) { //excel第一行 分区域事件数不同
                        sgpt1.setColumn3(name);
                        sgpt1.setId(1);
                    }
                }
                //String sheetName = sheetAt.getSheetName(); 获得excel的sheet的名称
                for (int rowNum = 2; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        Qysj qysj = HssfRowUtil.convert(hssfRow, Qysj.class);
                        if (qysj != null) {
                            qysjYear.add(qysj);
                        }
                    }
                }
            } else if (numSheet == 1) {
                for (int rowNum = 1; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        Qysj qysj = HssfRowUtil.convert(hssfRow, Qysj.class);
                        if (qysj != null) {
                            qysjMonth.add(qysj);
                        }
                    }
                }
            } else if (numSheet == 2) {
                for (int rowNum = 1; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        Qysj qysj = HssfRowUtil.convert(hssfRow, Qysj.class);
                        if (qysj != null) {
                            qysjDay.add(qysj);
                        }
                    }
                }
            }
        }

        if (!wb.getSheetAt(0).getSheetName().equals(sgpt.getColumn13()) || !wb.getSheetAt(1).getSheetName().equals(sgpt.getColumn14())
                || !wb.getSheetAt(2).getSheetName().equals(sgpt.getColumn15())) {
            sgpt1.setColumn13(wb.getSheetAt(0).getSheetName());
            sgpt1.setColumn14(wb.getSheetAt(1).getSheetName());
            sgpt1.setColumn15(wb.getSheetAt(2).getSheetName());
            int num3 = peopleFileService.updateSgpt(sgpt1);
            if (num3 < 0) return null;
        }
        try {
            log.info("批量处理数据开始---->");
            qysjService.dealBathAdd(qysjDay, qysjMonth, qysjYear);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * @Author: x
     * @Date: Created in 18:34 2018/2/23
     * 四个平台数据增删改
     */

    @RequestMapping(value = "/updateSgpt", produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String updateSgpt(HttpServletResponse response, Sgpt sgpt, Model model) {
//    response.addHeader( "Access-Control-Allow-Origin", "*" );
        // System.out.println(sgpt.getId()+"---"+sgpt.getColumn1()+"---"+sgpt.getColumn2());
        int num = peopleFileService.updateSgpt(sgpt);
        if (num > 0) {
           // model.addAttribute("success", num);
            log.info("修改成功:" + num);
            return "success";
        }
        log.error("修改失败:" + num);
        return "error";
    }

    /**
     * 分类型事件数修改
     * @param response
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateFlxsj", produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String updateFlxsj(HttpServletResponse response, HttpServletRequest request, Model model) {
//        response.addHeader( "Access-Control-Allow-Origin", "*" );
        String flxsjVO = request.getParameter("flxsjVO");
        List<Flxsj> flxsjs = JSONArray.parseArray(flxsjVO, Flxsj.class);
        int i = peopleFileService.deleteFlxsj();
        int num = 0;
        if (i > 0) {
            for (Flxsj flxsj : flxsjs) {
                int r = peopleFileService.updateFlxsj(flxsj);
                num += r;
            }
        }
        if (num > 0) {
            model.addAttribute("success", num);
            log.info("修改成功:" + num);
            return "success";
        }
        log.error("修改失败:" + num);
        return "error";
    }

    /**
     * @Author: x
     * @Date: Created in 9:56 2018/3/1
     * 分部门事件数修改
     */
    @RequestMapping(value = "/updateFbmsj", produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String updateFbmsj(HttpServletResponse response, HttpServletRequest request) {
//        response.addHeader("Access-Control-Allow-Origin", "*");

        String fbmsjVo1 = request.getParameter("fbmsjVo1");
        List<String> fbmsjVo1s = new ArrayList<String>();
        fbmsjVo1s = JSON.parseArray(fbmsjVo1, String.class);

        String fbmsjVo2 = request.getParameter("fbmsjVo2");
        List<Long> fbmsjVo2s = new ArrayList<Long>();
        fbmsjVo2s = JSON.parseArray(fbmsjVo2, Long.class);

        int number = peopleFileService.deleteFbmsj();
        if (number < 0) return "error";
        Fbmsj fbmsj = new Fbmsj();
        int num = 0;
        for (int i = 0; i < fbmsjVo1s.size(); i++) {
            fbmsj.setName(fbmsjVo1s.get(i));
            fbmsj.setValue(fbmsjVo2s.get(i));
            num = peopleFileService.addFbmsj(fbmsj);
            num += num;
        }
        if (num > 0) {
            log.info("修改成功:" + num);
            return "success";
        }
        log.error("修改失败:" + num);
        return "error";
    }
}
