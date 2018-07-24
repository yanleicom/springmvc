package com.yanlei.controller;

import com.bamboocloud.api.ApphubSdk;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
//@RequestMapping("/yanlei")
public class SanshiController {

    private static final Logger log = LoggerFactory.getLogger(SanshiController.class);

    @RequestMapping("/sanshi") //直接返回试图
     public ModelAndView show(HttpServletResponse res,HttpServletRequest request)  {
        ModelAndView modelAndView = new ModelAndView("sanshi");
        return modelAndView;
     }
      @RequestMapping("/once")
     public ModelAndView once(HttpServletResponse res,HttpServletRequest request){
          ModelAndView modelAndView = new ModelAndView("sanshi");
          return modelAndView;
     }
     @RequestMapping("/terrace")
     public ModelAndView terrace(HttpServletResponse res,HttpServletRequest request){
         ModelAndView modelAndView = new ModelAndView("sanshi");
         return modelAndView;
     }
     @RequestMapping("/zhengwu")
     public ModelAndView zhengwu(HttpServletResponse res,HttpServletRequest request)  {
         ModelAndView modelAndView = new ModelAndView("sanshi");
         return modelAndView;
     }





    @RequestMapping("/page")
    public ModelAndView page1(HttpServletResponse res,HttpServletRequest request)  {
        return getModelAndView(res, request,"page1");
    }
    @RequestMapping("/jingji")
    public ModelAndView jingji(HttpServletResponse res,HttpServletRequest request)  {
        return getModelAndView(res, request,"jingji");
    }
    @RequestMapping("/dangqun")
    public ModelAndView dangqun(HttpServletResponse res,HttpServletRequest request) {
        return getModelAndView(res, request,"dangqun");
    }
    @RequestMapping("/shehui")
    public ModelAndView shehui(HttpServletResponse res,HttpServletRequest request)  {
        return getModelAndView(res, request,"shehui");
    }
    @RequestMapping("/shengtai")
    public ModelAndView shengtai(HttpServletResponse res,HttpServletRequest request)  {
        return getModelAndView(res, request,"shengtai");
    }
    @RequestMapping("/wenhua")
    public ModelAndView wenhua(HttpServletResponse res,HttpServletRequest request)  {
        return getModelAndView(res, request,"wenhua");
    }
    @RequestMapping("/main")
    public ModelAndView main(HttpServletResponse res,HttpServletRequest request)  {
        return getModelAndView(res, request,"main");
    }


    /*private ModelAndView getModelAndView(HttpServletResponse res,HttpServletRequest request,String name) {
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("tokenid")){
                    String tokenid = cookie.getValue();
                    ApphubSdk sdk = new ApphubSdk();//new sdk jar
                    boolean m = sdk.informal_isTokenValid(tokenid);
                    if (m ==true) {
                        ModelAndView modelAndView = new ModelAndView(name);
                        return modelAndView;
                    }else {
                        res.sendRedirect("http://172.16.9.61:8080/yanlei/login");
                        return null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            res.sendRedirect("http://172.16.9.61:8080/yanlei/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/
    private ModelAndView getModelAndView(HttpServletResponse res,HttpServletRequest request,String name) {
        String tokenid = request.getParameter("tokenid");
        String userName = request.getParameter("userName");
        ApphubSdk sdk = new ApphubSdk();//new sdk jar
        if (StringUtils.isNotBlank(tokenid) && StringUtils.isNotBlank(userName)){
            boolean m = sdk.informal_isTokenValid(tokenid);
            if (m ==true) {
                ModelAndView modelAndView = new ModelAndView(name);
                return modelAndView;
            }else {
                try {
                    res.sendRedirect("http://172.16.9.61:8080/yanlei/login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }else {
            try {
                res.sendRedirect("http://172.16.9.61:8080/yanlei/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    @RequestMapping("/xiacheng")
    public ModelAndView xiacheng(){
        ModelAndView modelAndView = new ModelAndView("xiacheng");
        return modelAndView;
    }
   /* @RequestMapping("/admin")
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView("/WEB-INF/admin.html");
        return modelAndView;
    }*/


    /**
     * 下载excel 模板
     * @param request
     * @param response
     */
    @RequestMapping("/uploadExcel")
    public void uploadExcel(HttpServletRequest request, HttpServletResponse response){
        try {
            String path = "C:\\MultipartFile\\" + "template2.xlsx";
            //String filename = path.substring(path.lastIndexOf("/")+1);
            //String filename = new Date().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String filename = sdf.format(new Date());
            /* String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();*/


            filename = new String(filename.getBytes("iso8859-1"),"UTF-8");
            File file = new File(path);
            //如果文件不存在
            if(!file.exists()){
                //return false;
            }
            //设置响应头，控制浏览器下载该文件
            //response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));

            response.setHeader("content-disposition", "attachment;filename="+ new String((filename+".xlsx").getBytes(), "utf-8"));
            //读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(path);
            OutputStream out = response.getOutputStream();
            byte buffer[] = new byte[1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区中
            while((len = in.read(buffer)) > 0){
                out.write(buffer, 0, len);
        }
            //关闭
            in.close();
            out.close();
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @RequestMapping("/uploadExcel1")
    public void uploadExcel1(HttpServletRequest request, HttpServletResponse response){
        try {
            String path = "C:\\MultipartFile\\" + "template.xlsx";
           // String filename = path.substring(path.lastIndexOf("/")+1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String filename = sdf.format(new Date());

            filename = new String(filename.getBytes("iso8859-1"),"UTF-8");
            File file = new File(path);
            //如果文件不存在
            if(!file.exists()){
                //return false;
            }
            //设置响应头，控制浏览器下载该文件
            //response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.setHeader("content-disposition", "attachment;filename="+ new String((filename+".xlsx").getBytes(), "utf-8"));
            //读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(path);
            OutputStream out = response.getOutputStream();
            byte buffer[] = new byte[1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区中
            while((len = in.read(buffer)) > 0){
                out.write(buffer, 0, len);
            }
            //关闭
            in.close();
            out.close();
        } catch (Exception e){
            log.error(e.getMessage());
//            log.error(e);
        }
    }

    @RequestMapping("/uploadExcel2")
    public void uploadExcel2(HttpServletRequest request, HttpServletResponse response){
        try {
            String path = "C:\\MultipartFile\\" + "sanshi.xlsx";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String filename = sdf.format(new Date());
            filename = new String(filename.getBytes("iso8859-1"),"UTF-8");
            File file = new File(path);
            //如果文件不存在
            if(!file.exists()){
                //return false;
            }
            //设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename="+ new String((filename+".xlsx").getBytes(), "utf-8"));
            //读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(path);
            OutputStream out = response.getOutputStream();
            byte buffer[] = new byte[1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区中
            while((len = in.read(buffer)) > 0){
                out.write(buffer, 0, len);
            }
            //关闭
            in.close();
            out.close();
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }


    @RequestMapping("/uploadExcel3")
    public void uploadExcel3(HttpServletRequest request, HttpServletResponse response){
        try {
            String path = "C:\\MultipartFile\\" + "sgpt.xlsx";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String filename = sdf.format(new Date());
            filename = new String(filename.getBytes("iso8859-1"),"UTF-8");
            File file = new File(path);
            //如果文件不存在
            if(!file.exists()){
                //return false;
            }
            //设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename="+ new String((filename+".xlsx").getBytes(), "utf-8"));
            //读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(path);
            OutputStream out = response.getOutputStream();
            byte buffer[] = new byte[1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区中
            while((len = in.read(buffer)) > 0){
                out.write(buffer, 0, len);
            }
            //关闭
            in.close();
            out.close();
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
