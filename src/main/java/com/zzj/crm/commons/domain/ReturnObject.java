package com.zzj.crm.commons.domain;

/**
 * @BelongsProject: CRM_Project
 * @BelongsPackage: com.zzj.crm.commons.domain
 * @Author: zzj
 * @CreateTime: 2022-07-13  17:05
 * @Description: TODO
 * @Version: 1.0
 */
public class ReturnObject {
    private String code; //处理成功或失败的标记，1-成功，0-失败
    private String message;
    private Object returnData; //其他数据

    public ReturnObject(String code, String message, Object returnData) {
        this.code = code;
        this.message = message;
        this.returnData = returnData;
    }

    public ReturnObject() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }
}
