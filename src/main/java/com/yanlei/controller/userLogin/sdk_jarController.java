package com.yanlei.controller.userLogin;


import com.bamboocloud.api.ApphubSdk;
import com.yanlei.util.luxi.BigDecimalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jms.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: x
 * @Date: Created in 8:54 2018/4/25
 * 第三方登入对接 有事@小强
 */

@Controller
public class sdk_jarController {

    private String tokenid ;//token
    private String app_id  = "datadash";//注册在综合信息系统中的app的username
    private String app_key = "password";//注册在综合信息系统中的app的password
    private String targetsystemId = "20180211175457111-DEFD-817AC38EA";//注册在综合信息系统中的app的ID

    //private String login = "/login";//自己的登录页面
    //private String seccuss = "/testSDK";//成功跳转的页面

    private String seccuss = "admin";//成功跳转的页面

    private ApphubSdk sdk = new ApphubSdk();//new sdk jar

    /**
     * 登陆
     * @param req
     * @param res
     * @param BBCloudBAMSession
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse res, String BBCloudBAMSession) throws IOException {
        String accname = req.getParameter("accountName");//获取单点登陆账号
        this.tokenid = req.getParameter("BBCloudBAMSession");		/*获取token*/

        if(this.tokenid==null || "".equals(this.tokenid)){//校验token是否获取到
            try {
                res.sendRedirect(this.sdk.informal_apphublogin_getAPI(this.targetsystemId));//跳转统一的登录页面，重新获取token
            } catch (IOException e) {
                //return this.login; //自己系统的登录界面
                return "error";
            }
            return null;
        }

        /*Cookie cookie =new Cookie("token",tokenid);
        cookie.setMaxAge(60*60*4);   //存活期为一个月 30*24*60*60
        cookie.setPath("/");
        res.addCookie(cookie);*/

        Map<String, List<String>> mapper = this.sdk.informal_userInfo_getAttributes(this.tokenid, this.app_id, this.app_key);//获取用户信息
        if(mapper.containsKey("notToken")){//未获取到用户信息
            if(this.sdk.informal_isAlive()){//检测 统一用户  是否开启
                try {
                    res.sendRedirect(this.sdk.informal_apphublogin_getAPI(this.targetsystemId));//跳转统一的登录页面
                } catch (IOException e) {
                    //return this.login; //自己系统的登录界面
                    return "error";
                }
                return null;
            }else{
                // return this.login; //自己系统的登录界面
                return "error";
            }
        }
        /*token存放在session中*/
        req.getSession().setMaxInactiveInterval(60*60*4);//设置session为240分钟，可自行解决
        req.getSession().setAttribute("tokenid", this.tokenid);//把token 放入session


        Cookie userCookie=new Cookie("tokenid",tokenid); //cookie客户端保存tokenid
        userCookie.setMaxAge(60*60*4);   //存活期为240分组 60*60*4
        userCookie.setPath("/");
        res.addCookie(userCookie);



        if(accname==null || "".equals(accname)){//判断单点登陆有值么
            accname = mapper.get("uid").get(0);//获取用户信息
        }

        /*根据自己的需求    改造的地方    获取用户信息开始*/
        System.out.println(mapper);//打印输出信息，供查阅


        /*统一用户：存储在统一用户中的用户信息*/
        String username = accname;//统一用户的  用户名
        String password = mapper.get("userpassword").get(0);//统一用户的  用户名
        String fullname = mapper.get("sn").get(0);//统一用户的  中文姓名

        /*根据自己的需求    改造的地方  获取用户信息结束*/



        /* 获取角色信息  暂无实例*/

        //res.sendRedirect( req.getServletContext().getRealPath("/")+"admin.html");
        //res.sendRedirect("http://zh.hzxc.gov.cn/apphub/");
        res.sendRedirect("http://172.16.9.61:8080/yanlei/main.html");
//        res.sendRedirect("http://10.18.58.170:8081/main.html");
        return null;
        // return this.seccuss;//成功界面
        //return null;

    }


    /**
     * 注销
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/closeUser")
    public void closeUser(HttpServletRequest req,HttpServletResponse res) throws IOException{
        String gotoUrl = this.sdk.informal_logout(this.targetsystemId);//注销token
        req.getSession().setAttribute("tokenid", null);//失效 存放在session token
        //return getLogin(res);//跳转登录界面重新获取token
        res.sendRedirect(gotoUrl);//跳转统一的登录页面
    }


}
