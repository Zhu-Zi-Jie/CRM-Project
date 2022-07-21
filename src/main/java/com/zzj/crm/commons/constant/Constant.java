package com.zzj.crm.commons.constant;

/**
 * @BelongsProject: CRM_Project
 * @BelongsPackage: com.zzj.crm.commons.constant
 * @Author: zzj
 * @CreateTime: 2022-07-13  22:02
 * @Description: 保存ReturnObject类中的Code值
 * @Version: 1.0
 */
public class Constant {
    //保存ReturnObject类汇总的Code值
    public static final String RETURN_OBJECT_CODE_SUCCESS = "1";
    public static final String RETURN_OBJECT_CODE_FAIL = "0";

    //保存当前用户的Key
    public static final String SESSION_USER = "sessionUser";

    //备注的修改标记
    public static final String REMARK_EDIT_FLAG_NO_EDITED="0";//0---没有修改过
    public static final String REMARK_EDIT_FLAG_YES_EDITED="1";//1--已经被修改过
}
