package com.zzj.crm.workbench.web.controller;

import com.zzj.crm.commons.constant.Constant;
import com.zzj.crm.commons.domain.ReturnObject;
import com.zzj.crm.commons.utils.DateUtils;
import com.zzj.crm.commons.utils.UUIDUtils;
import com.zzj.crm.settings.domain.User;
import com.zzj.crm.workbench.domain.ActivityRemark;
import com.zzj.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.workbench.web.controller
 * @Author: zzj
 * @CreateTime: 2022-07-21  17:16
 * @Description: TODO
 * @Version: 1.0
 */

@Controller
public class ActivityRemarkController {
    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/saveCreateActivityRemark.do")
    public @ResponseBody Object saveCreateActivityRemark(ActivityRemark remark, HttpSession session) {
        User user=(User) session.getAttribute(Constant.SESSION_USER);
        //封装参数
        remark.setId(UUIDUtils.getUUID());
        remark.setCreateTime(DateUtils.formateDateTime(new Date()));
        remark.setCreateBy(user.getId());
        remark.setEditFlag(Constant.REMARK_EDIT_FLAG_NO_EDITED);

        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service层方法，保存创建的市场活动备注
            int ret = activityRemarkService.saveCreateActivityRemark(remark);
            if(ret>0){
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setReturnData(remark);
            }else{
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/deleteActivityRemarkById.do")
    public @ResponseBody Object deleteActivityRemarkById(String id){
        ReturnObject returnObject= new ReturnObject();
        try {
            //调用service层方法，删除备注
            int ret = activityRemarkService.deleteActivityRemarkById(id);
            if(ret>0){
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/saveEditActivityRemark.do")
    public @ResponseBody Object saveEditActivityRemark(ActivityRemark remark,HttpSession session){
        User user=(User) session.getAttribute(Constant.SESSION_USER);
        //封装参数
        remark.setEditTime(DateUtils.formateDateTime(new Date()));
        remark.setEditBy(user.getId());
        remark.setEditFlag(Constant.REMARK_EDIT_FLAG_YES_EDITED);

        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service层方法，保存修改的市场活动备注
            int ret = activityRemarkService.saveEditActivityRemark(remark);
            if(ret>0){
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setReturnData(remark);
            }else{
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }
        return returnObject;
    }
}
