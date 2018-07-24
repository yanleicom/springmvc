package com.yanlei.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.besjon.po.*;
import com.yanlei.model.*;
import com.yanlei.service.NumberItemsService;
import com.yanlei.service.ZdpycYearService;
import com.yanlei.util.HssfRowUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

/**
 * @Author: x
 * @Date: Created in 15:24 2018/3/6
 * 监控大屏_最多跑一次
 */

@Controller
//@RequestMapping(value = "/yanlei")
public class NumberItemsController {
    private static final Logger logger = LoggerFactory.getLogger(NumberItemsController.class);

    @Autowired
    private NumberItemsService numberItemsService;
    @Autowired
    private ZdpycYearService zdpycYearService;

    /**
     * 分受理区域办件数 年,月,日数据展示
     * @param response
     * @param request
     * @return
     */

    @RequestMapping(value = "/showZdpyc", method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showZdpyc(HttpServletResponse response, HttpServletRequest request) {
       // response.addHeader("Access-Control-Allow-Origin", "*");
        Zdpyc zdpyc = numberItemsService.findZdpyc();

        List<ZdpycYear> zdpycYear = numberItemsService.findZdpycYear();//年月日数据展示
        List<ZdpycYear> zdpycMonth = numberItemsService.findZdpycMonth();
        List<ZdpycYear> zdpycDay = numberItemsService.findZdpycDay();

        List<String> lists = new ArrayList<String>();
        lists.add(zdpyc.getColumn2());//年
        lists.add(zdpyc.getColumn3());//月
        lists.add(zdpyc.getColumn4());//日

        List<Fenlist2> fenlists = new ArrayList<Fenlist2>();
        for (int i = 0; i < zdpycYear.size(); i++) {
            Fenlist2 fenlist = new Fenlist2(zdpycYear.get(i).getId(), zdpycYear.get(i).getColumn1(), zdpycYear.get(i).getColumn2(), zdpycYear.get(i).getColumn3());
            fenlists.add(fenlist);
        }
        List<Fenlist2> fenlists1 = new ArrayList<Fenlist2>();
        for (int i = 0; i < zdpycMonth.size(); i++) {
            Fenlist2 fenlist = new Fenlist2(zdpycMonth.get(i).getId(), zdpycMonth.get(i).getColumn1(), zdpycMonth.get(i).getColumn2(), zdpycMonth.get(i).getColumn3());
            fenlists1.add(fenlist);
        }
        List<Fenlist2> fenlists2 = new ArrayList<Fenlist2>();
        for (int i = 0; i < zdpycDay.size(); i++) {
            Fenlist2 fenlist = new Fenlist2(zdpycDay.get(i).getId(), zdpycDay.get(i).getColumn1(), zdpycDay.get(i).getColumn2(), zdpycDay.get(i).getColumn3());
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
        jsonRootBean.setName(zdpyc.getColumn1());
        return JSON.toJSONString(jsonRootBean);
    }

    /**
     *开通网上办件数统计 数据展示
     * @param response
     * @param request
     * @return
     */

    @RequestMapping(value = "/showComputer", method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showComputer(HttpServletResponse response, HttpServletRequest request) {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        Zdpyc zdpyc = numberItemsService.findZdpyc();
        List<Computer> computers = numberItemsService.findComputer();

        List<Fenlist5> fenlist5s = new ArrayList<Fenlist5>();
        for (int i = 0; i < computers.size(); i++) {
            Fenlist5 fenlist5 = new Fenlist5(computers.get(i).getId(), computers.get(i).getName(), computers.get(i).getValue());
            fenlist5s.add(fenlist5);
        }
        JsonRootBean5 jsonRootBean = new JsonRootBean5();
        jsonRootBean.setFenlist5s(fenlist5s);
        jsonRootBean.setColumn5(zdpyc.getColumn5());
        jsonRootBean.setColumn6(zdpyc.getColumn6());
        jsonRootBean.setColumn7(zdpyc.getColumn7());

        return JSON.toJSONString(jsonRootBean);
    }


    @RequestMapping(value = "/showDepartmentOfNumber", method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showDepartmentOfNumber(HttpServletResponse response, HttpServletRequest request) {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        Zdpyc zdpyc = numberItemsService.findZdpyc();

        List<Qwb> qwbs = numberItemsService.findDepartmentQwb();
        List<Zfb> zfbs = numberItemsService.findDepartmentZfb();
        List<Rlb> rlbs = numberItemsService.findDepartmentRdb();
        List<Qjw> qjws = numberItemsService.findDepartmentQjw();
        List<Zx> zxs = numberItemsService.findDepartmentZx();

        List<String> lists = new ArrayList<String>();
        lists.add(zdpyc.getColumn9());
        lists.add(zdpyc.getColumn10());
        lists.add(zdpyc.getColumn11());
        lists.add(zdpyc.getColumn12());
        lists.add(zdpyc.getColumn13());

        List<Fenlist> fenlists = new ArrayList<Fenlist>();
        for (int i = 0; i < qwbs.size(); i++) {
            Fenlist fenlist = new Fenlist(qwbs.get(i).getId(), qwbs.get(i).getDepartment(), qwbs.get(i).getNumber());
            fenlists.add(fenlist);
        }
        List<Fenlist> fenlists1 = new ArrayList<Fenlist>();
        for (int i = 0; i < zfbs.size(); i++) {
            Fenlist fenlist = new Fenlist(zfbs.get(i).getId(), zfbs.get(i).getDepartment(), zfbs.get(i).getNumber());
            fenlists1.add(fenlist);
        }
        List<Fenlist> fenlists2 = new ArrayList<Fenlist>();
        for (int i = 0; i < rlbs.size(); i++) {
            Fenlist fenlist = new Fenlist(rlbs.get(i).getId(), rlbs.get(i).getDepartment(), rlbs.get(i).getNumber());
            fenlists2.add(fenlist);
        }
        List<Fenlist> fenlists3 = new ArrayList<Fenlist>();
        for (int i = 0; i < qjws.size(); i++) {
            Fenlist fenlist = new Fenlist(qjws.get(i).getId(), qjws.get(i).getDepartment(), qjws.get(i).getNumber());
            fenlists3.add(fenlist);
        }
        List<Fenlist> fenlists4 = new ArrayList<Fenlist>();
        for (int i = 0; i < zxs.size(); i++) {
            Fenlist fenlist = new Fenlist(zxs.get(i).getId(), zxs.get(i).getDepartment(), zxs.get(i).getNumber());
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
        dlistsList.add(dlists);
        dlistsList.add(dlists1);
        dlistsList.add(dlists2);
        dlistsList.add(dlists3);
        dlistsList.add(dlists4);

        JsonRootBean jsonRootBean = new JsonRootBean();
        jsonRootBean.setLists(lists);
        jsonRootBean.setDlists(dlistsList);
        jsonRootBean.setColumn1(zdpyc.getColumn8());
        jsonRootBean.setColumn2(zdpyc.getColumn14());
        jsonRootBean.setColumn3(zdpyc.getColumn15());
        jsonRootBean.setColumn4(zdpyc.getColumn16());

        return JSON.toJSONString(jsonRootBean);
    }

    @RequestMapping(value = "/updateZdpyc", method = RequestMethod.POST,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    //@CrossOrigin(origins = "*", maxAge = 3600) //解决前端跨域问题
    public String updateZdpyc(HttpServletResponse response,
                              @RequestParam(value = "file4") MultipartFile file) {
        if (file == null) return null;
        //       response.addHeader("Access-Control-Allow-Origin", "*");

        String filePath = "C:\\MultipartFile\\" + file.getOriginalFilename();
        //String filePath = request.getSession().getServletContext().getRealPath("\\upload")+ file.getOriginalFilename();
        String filename = file.getOriginalFilename();
        //System.out.println(filename);
        long size = file.getSize();
        // System.out.println(size);
        if (filename == null || ("").equals(filename) && size == 0) return null;

        File saveFile = new File(filePath);
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            logger.error("文件保存错误", e);
            e.printStackTrace();
        }
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }

        Zdpyc zdpyc = numberItemsService.findZdpyc();

        Zdpyc zdpyc1 = new Zdpyc();
        logger.info("查询得到的数据" + JSON.toJSONString(zdpyc));
        Workbook wb;
        try {
            wb = WorkbookFactory.create(new FileInputStream(saveFile));
        } catch (Exception e) {
            logger.error("文件不是excel格式文件", e);
            return "error";
        }

        List<ZdpycYear> zdpycYears = new ArrayList<ZdpycYear>();
        List<ZdpycYear> zdpycMonths = new ArrayList<ZdpycYear>();
        List<ZdpycYear> zdpycDays = new ArrayList<ZdpycYear>();

        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            Sheet sheetAt = wb.getSheetAt(numSheet);
            if (sheetAt == null) {
                continue;
            }

            if (numSheet == 0) {
                Row row = sheetAt.getRow(0);
                if (row != null) {
                    String name = row.getCell(0).getStringCellValue();
                    if ("" != name && !name.equals(zdpyc1.getColumn1())) { //excel第一行 分区域事件数不同
                        zdpyc1.setColumn1(name);
                        zdpyc1.setId(1);
                    }
                }
                for (int rowNum = 2; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        ZdpycYear zdpycYear = HssfRowUtil.convert(hssfRow, ZdpycYear.class);
                        if (zdpycYear != null) {
                            zdpycYears.add(zdpycYear);
                        }
                    }
                }
            } else if (numSheet == 1) {
                for (int rowNum = 1; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        ZdpycYear zdpycMonth = HssfRowUtil.convert(hssfRow, ZdpycYear.class);
                        if (zdpycMonth != null) {
                            zdpycMonths.add(zdpycMonth);
                        }
                    }
                }
            } else if (numSheet == 2) {
                for (int rowNum = 1; rowNum <= sheetAt.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheetAt.getRow(rowNum);
                    if (hssfRow != null) {
                        ZdpycYear zdpycDay = HssfRowUtil.convert(hssfRow, ZdpycYear.class);
                        if (zdpycDay != null) {
                            zdpycDays.add(zdpycDay);
                        }
                    }
                }
            }
        }
        if (!wb.getSheetAt(0).getSheetName().equals(zdpyc.getColumn2()) || !wb.getSheetAt(1).getSheetName().equals(zdpyc.getColumn3())
                || !wb.getSheetAt(2).getSheetName().equals(zdpyc.getColumn4())) {
            zdpyc1.setColumn2(wb.getSheetAt(0).getSheetName());
            zdpyc1.setColumn3(wb.getSheetAt(1).getSheetName());
            zdpyc1.setColumn4(wb.getSheetAt(2).getSheetName());

            //fbm1.setId(1);
            int num5 = numberItemsService.updateZdpyc(zdpyc1);
            if (num5 < 0) return null;
        }

        try {
            logger.info("-------->开始批量处理数据");
            zdpycYearService.dealBathAdd(zdpycYears, zdpycMonths, zdpycDays);
            return "success";
        } catch (Exception e) {
            logger.error("错误信息" + e);
            return "error";
        }

    }


    @RequestMapping("/downloadZdpyc")
    public void downloadZdpyc(HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = "C:\\MultipartFile\\" + "zdpyc.xlsx";
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


    @RequestMapping(value = "/updateComputer", method = RequestMethod.POST,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String updateComputer(HttpServletResponse response, HttpServletRequest request) {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        String computer = null;
        try {
            computer = request.getParameter("Computer");
            List<Computer> computers = JSONArray.parseArray(computer, Computer.class);
            numberItemsService.delAllComputers(computers);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("错误信息" + e);
            return "error";
        }


    }

    @RequestMapping(value = "/updateComputerColumn", method = RequestMethod.POST,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String updateComputerColumn(HttpServletResponse response, HttpServletRequest request, String column5, Long column6) {
        Zdpyc zdpycs = numberItemsService.findZdpyc();
            int num = 0;
            if (column5 != null && !column5.equals(zdpycs.getColumn5()) ||
                    column6 != null && !column6.equals(zdpycs.getColumn6())) {
                Zdpyc zdpyc1 = new Zdpyc();
                zdpyc1.setId(zdpycs.getId());
                zdpyc1.setColumn5(column5);
                zdpyc1.setColumn6(column6);
                num = numberItemsService.updateZdpyc(zdpyc1);
            }
            if (num>0){
                return "success";
            }else {
                return "error";
            }
    }
}

