package com.zzj.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @BelongsProject: CRM_Project
 * @BelongsPackage: com.zzj.crm.commons.utils
 * @Author: zzj
 * @CreateTime: 2022-07-13  21:47
 * @Description: 对Date类型数据进行指定格式的格式化
 * @Version: 1.0
 */
public class DateUtils {
    private DateUtils(){}

    /*
     * @description:对指定的date对象进行格式化
     * @author: zzj
     * @date: 2022/7/13 21:51
     * @param: [date]
     * @return: java.lang.String
     **/
    public static String formateDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /*
     * @description:DateUtils里进行不同格式化的方法
     * @author: zzj 
     * @date: 2022/7/13 21:55
     * @param: [date]
     * @return: java.lang.String
     **/
    public static String formateDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
