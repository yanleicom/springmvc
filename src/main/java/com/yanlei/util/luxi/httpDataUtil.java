package com.yanlei.util.luxi;

/**
 *
 */

import com.alibaba.fastjson.JSONObject;
import com.yanlei.dto.ResponseData;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.util.*;
import java.util.Map.Entry;



/**
 * @author x
 * @ClassName httpData.java
 * @Description:TODO
 * @date 2017-12-19下午4:16:51
 */
public class httpDataUtil {
    private Logger logger = LoggerFactory.getLogger(httpDataUtil.class);

    /**
     * 调用HTTP接口 POST方式
     *
     * @param @param  httpURL URL地址
     * @param @param  mapper  map集合 key是接口接收的key value是传的�??
     * @param @throws Exception
     * @return String 接口返回的�??
     * @throws
     * @Description: TODO
     * @author x
     * @date 2017-12-19
     */
    public static ResponseData httpPOST(String httpURL, Map<String, String> mapper) throws Exception {
        //请求的webservice的url
        URL url = new URL(httpURL);
        //创建http链接
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        String response = "";
        try {
            //设置请求的方法类�?
            httpURLConnection.setRequestMethod("POST");

            //设置请求的内容类�?
            //httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            // 设置通用的请求属�?
            httpURLConnection.setRequestProperty("accept", "*/*");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //设置发�?�数�?
            httpURLConnection.setDoOutput(true);
            //设置接受数据
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);

            //发�?�数�?,使用输出�?
            OutputStream outputStream = httpURLConnection.getOutputStream();
            //发�?�的soap协议的数�?
            //String requestXmlString = requestXml("北京");
            StringBuffer sbf = new StringBuffer();
            for (Entry<String, String> e : mapper.entrySet()) {
                sbf.append("&" + e.getKey() + "=" + URLEncoder.encode(e.getValue(), "utf-8"));

            }
            //发�?�数�?
            outputStream.write(sbf.toString().substring(1, sbf.length()).getBytes());


            //接收数据

            InputStream inputStream = httpURLConnection.getInputStream();


            //定义字节数组
            byte[] b = new byte[1024];

            //定义�?个输出流存储接收到的数据
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            //�?始接收数�?
            int len = 0;
            while (true) {
                len = inputStream.read(b);
                if (len == -1) {
                    //数据读完
                    break;
                }
                byteArrayOutputStream.write(b, 0, len);
            }

            //从输出流中获取读取到数据(服务端返回的)
            response = byteArrayOutputStream.toString();
        } catch (Exception e) {
            return new ResponseData("999999", e.getMessage());
        }
        return new ResponseData(response);
    }

    /**
     * 调用HTTP接口 GET方式
     *
     * @param @param  httpURL URL地址
     * @param @param  mapper  map集合 key是接口接收的key value是传的�??
     * @param @throws Exception
     * @return String 接口返回的�??
     * @throws
     * @Description: TODO
     * @author x
     * @date 2017-12-19
     */
    public static ResponseData httpGet(String httpURL, Map<String, String> mapper) {
        String result = "";
        BufferedReader in = null;
        String response = "";
        try {
            String urlNameString = httpURL;
            StringBuffer sbf = new StringBuffer();
            if (mapper != null) {
                for (Entry<String, String> e : mapper.entrySet()) {
                    sbf.append("&" + e.getKey() + "=" + URLEncoder.encode(e.getValue(), "utf-8"));

                }
                if (sbf.length() != 0) {
                    urlNameString = httpURL + "?" + sbf.toString().substring(1, sbf.length());
                }
            }
            System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连�?
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属�?
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连�?
            connection.connect();
            // 获取�?有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历�?有的响应头字�?
            for (String key : map.keySet()) {
                //System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响�?
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发�?�GET请求出现异常�?" + e);
            e.printStackTrace();
            return new ResponseData("999999", e.getMessage());
        }
        // 使用finally块来关闭输入�?
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return new ResponseData("999999", e2.getMessage());
            }
        }
        return new ResponseData(result);
    }


    //http服务发送发送后期需要移动到login-plugins中
    public static String login(String name){

        String loginName=name;
        long timestamp =new Date().getTime();
        int nonce=100000+(int)(Math.random()*900000);

        String appSecret="OA";
        List<String> list = new ArrayList<String>();
        list.add(loginName);
        list.add(timestamp+"");
        list.add(nonce+"");
        Collections.sort(list);
        StringBuilder content = new StringBuilder();
        for(String s:list){
            content.append(s);
        }
        String signature=getSha1(content.toString()+appSecret);
        String echostr="abcdef";
        JSONObject map=new JSONObject();
        map.put("appId", "ZmOaK1ylZehx2qX1");
//        map.put("appId", "3OjQ5nZLGmId1AGV");
        map.put("loginName", loginName);
        map.put("nonce", nonce);
        map.put("echostr", echostr);
        map.put("timestamp", timestamp);
        map.put("signature",signature);
        return map.toString();
    }
    //获取督察列表，后期需要修改到plugins内
    public static String getSchedule(int[] arr, String token, String companyCode, String companyShowID, String loginName, String userName){

        Map<String, String> headerMap = new HashMap<String, String>();
        Map<String, Object> BodyMap = new HashMap<String, Object>();
        Map<String, int[]> paramMap = new HashMap<String, int[]>();

        headerMap.put("async","false");
        headerMap.put("authToken",token);
        headerMap.put("companyCode",companyCode);
        headerMap.put("companyShowID",companyShowID);
        headerMap.put("language","zh-cn");
        headerMap.put("loginName",loginName);
//        headerMap.put("resourceUri","/Document-Module/Document/GetListAll");//公文
        headerMap.put("resourceUri","/Supervision-Module/Supervision/List"); //督查

        headerMap.put("type","GET");
        headerMap.put("userName",userName);

       /* paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);*/
        paramMap.put("types",arr);

        BodyMap.put("param",paramMap);

        JSONObject map=new JSONObject();
        map.put("header", headerMap);
        map.put("body", BodyMap);

        return map.toString();
    }
    public static String getSha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }



     public static  ResponseData getPostResult(String outStr,String url){
        HttpClient httpClient = HttpClients.custom().build();
        HttpPost httpPost=new HttpPost(url);
        String result="";
        try {
            StringEntity entity=new StringEntity(outStr,"utf8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            HttpResponse response=httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode()==200){
                //HttpResponseProxy{HTTP/1.1 200 OK [Content-Length: 172, Content-Type: application/json; charset=utf-8, Server: Microsoft-IIS/8.0, X-Powered-By: ASP.NET, Date: Fri, 19 Jan 2018 03:41:44 GMT] ResponseEntityProxy{[Content-Type: application/json; charset=utf-8,Content-Length: 172,Chunked: false]}}
                result=EntityUtils.toString(response.getEntity(),"UTF-8");

            }
            else{
                result = String.valueOf(response.getStatusLine().getStatusCode());
                return new ResponseData("999999","url:"+url+" 返回状态码："+result);
            }
        }catch (Exception e){
            result = e.getMessage();
            return new ResponseData("999999","url:"+url+" 错误状态码："+result);
            //System.out.println(url+"--interface Exception");
        }finally{

        }
        return new ResponseData(result);

    }

    //获取公文接口 后期移动到plugins内
    public static String getOfficialDocument(String token, String companyCode, String companyShowID, String loginName, String userName, long startTime, long endTime){
        Map<String, String> headerMap = new HashMap<String, String>();
        Map<String, Object> BodyMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        headerMap.put("async","false");
        headerMap.put("authToken",token);
        headerMap.put("companyCode",companyCode);
        headerMap.put("companyShowID",companyShowID);
        headerMap.put("language","zh-cn");
        headerMap.put("loginName",loginName);
        headerMap.put("resourceUri","/Document-Module/Document/GetListAll");
        headerMap.put("type","GET");
        headerMap.put("userName",userName);
        paramMap.put("pageIndex", "1"); //页码
        paramMap.put("pageSize","100"); //条数
        paramMap.put("startTime",startTime); //大于这个时间
        paramMap.put("endTime",endTime); //小于这个时间

        BodyMap.put("param",paramMap);

        JSONObject map=new JSONObject();
        map.put("header", headerMap);
        map.put("body", BodyMap);
        return map.toString();
    }

    public static String doPost(String url, Map params){

        BufferedReader in = null;
        try {
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));

            //设置参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));

                //System.out.println(name +"-"+value);
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if(code == 200){    //请求成功
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(),"utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();

                return sb.toString();
            }
            else{   //
                System.out.println("状态码：" + code);
                return null;
            }
        }
        catch(Exception e){
            e.printStackTrace();

            return null;
        }
    }


    public static String doPost(String url, String params) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";

        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity,"UTF-8");
                return jsonString;
            }
            else{
                //logger.error("请求返回:"+state+"("+url+")");
            }
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    //获取公文接口 后期移动到plugins内
    public static String getOfficialDocument2(String token, String companyCode, String companyShowID, String loginName, String userName){
        Map<String, String> headerMap = new HashMap<String, String>();
        Map<String, Object> BodyMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        headerMap.put("async","false");
        headerMap.put("authToken",token);
        headerMap.put("companyCode",companyCode);
        headerMap.put("companyShowID",companyShowID);
        headerMap.put("language","zh-cn");
        headerMap.put("loginName",loginName);
        headerMap.put("resourceUri","/Document-Module/Document/GetListAll");
        headerMap.put("type","GET");
        headerMap.put("userName",userName);
        paramMap.put("pageIndex", "1"); //页码
        paramMap.put("pageSize","100"); //条数
        BodyMap.put("param",paramMap);

        JSONObject map=new JSONObject();
        map.put("header", headerMap);
        map.put("body", BodyMap);
        return map.toString();
    }

}