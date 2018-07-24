package com.yanlei.util;

import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @Author: x
 * @Date: Created in 11:02 2018/3/5
 */
public class HssfRowUtil {
    private static Logger logger = LoggerFactory.getLogger(HssfRowUtil.class);

    /**
     * @Author: 将row里面的数据一一对应赋值给po的属性
     * 单元格只要有一个为空 都会跳过属性赋值失败
     * @Date: Created in 14:48 2018/3/5
     */
    public static <T> T convert(Row row, Class<T> c) {
        T t = null;
        if (row == null) {
            return t;
        }
        Field[] fields = c.getDeclaredFields();
        try {
            t = c.newInstance();
            if (row.getPhysicalNumberOfCells() < fields.length - 2) {
                return null;
            }
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                Cell cell = row.getCell(i);
                Field field = fields[i + 1];
                field.setAccessible(true);
                if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    if (field.getGenericType().equals(String.class)) {
                        field.set(t, cell.getStringCellValue());
                    }
                    continue;
                }
                if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    double value = cell.getNumericCellValue();
                    if (field.getGenericType().equals(Long.class)) {
                        field.set(t, Math.round(value));
                        continue;
                    }
                    if (field.getGenericType().equals(Integer.class)) {
                        long l = Math.round(cell.getNumericCellValue());
                        field.set(t, (int) l);
                        continue;
                    }
                    if (field.getGenericType().equals(Double.class)) {
                        field.set(t, cell.getNumericCellValue());
                        continue;
                    }
                }
            }
            //设定更新日期
            if (fields[fields.length - 1].getGenericType().equals(Date.class)) {
                fields[fields.length - 1].setAccessible(true);
                fields[fields.length - 1].set(t, new Date());
            }
        } catch (Exception e) {
            logger.error("excel数据赋值属性失败");
            e.printStackTrace();
        }
        return t;
    }
}
