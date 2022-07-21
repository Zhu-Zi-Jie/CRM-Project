package com.zzj.crm.commons.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.commons.utils
 * @Author: zzj
 * @CreateTime: 2022-07-20  23:14
 * @Description: 从指定的HSSFCell对象中获取列的值
 * @Version: 1.0
 */
public class HSSFUtils {
    public static String getCellValueForStr(HSSFCell cell){
        String ret="";
        if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
            ret=cell.getStringCellValue();
        }else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
            ret=cell.getNumericCellValue()+"";
        }else if(cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
            ret=cell.getBooleanCellValue()+"";
        }else if(cell.getCellType()==HSSFCell.CELL_TYPE_FORMULA){
            ret=cell.getCellFormula();
        }else{
            ret="";
        }

        return ret;
    }
}
