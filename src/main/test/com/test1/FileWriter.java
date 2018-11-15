package com.test1;

import com.yanlei.util.PropertyUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Author: x
 * @Date: Created in 14:47 2018/4/11
 */
public class FileWriter {

    public static void main(String[] args) {
        // 构建指定文件
        File file = new File("C:\\MultipartFile\\config.properties");
        OutputStream out = null;
        try {
            // 根据文件创建文件的输出流
            out = new FileOutputStream(file);
            String message = "我是好人。";
            // 把内容转换成字节数组
            byte[] data = message.getBytes();
            // 向文件写入内容
            out.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭输出流
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
