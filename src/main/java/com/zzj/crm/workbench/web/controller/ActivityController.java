package com.zzj.crm.workbench.web.controller;

import com.sun.xml.internal.xsom.impl.scd.Step;
import com.zzj.crm.commons.constant.Constant;
import com.zzj.crm.commons.domain.ReturnObject;
import com.zzj.crm.commons.utils.DateUtils;
import com.zzj.crm.commons.utils.HSSFUtils;
import com.zzj.crm.commons.utils.UUIDUtils;
import com.zzj.crm.settings.domain.User;
import com.zzj.crm.settings.service.UserService;
import com.zzj.crm.workbench.domain.Activity;
import com.zzj.crm.workbench.domain.ActivityRemark;
import com.zzj.crm.workbench.service.ActivityRemarkService;
import com.zzj.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.workbench.web.controller
 * @Author: zzj
 * @CreateTime: 2022-07-16  16:00
 * @Description: 负责处理访问/WEB-INF/pages/workbench/activity目录下资源的请求
 * @Version: 1.0
 */
@Controller
public class ActivityController {
    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request) {
        //调用servic层方法查询所有用户
        List<User> userList = userService.queryAllUser();
        //把数据保存到request域中
        request.setAttribute("userList", userList);
        //跳转到市场活动的主页面
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    public @ResponseBody Object saveCreateActivity(Activity activity, HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        //从网页传过来的参数只封装了6个，需要进行二次封装成完整的实体类对象
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formateDateTime(new Date()));
        activity.setCreateBy(user.getId()); //这是市场活动表的外键！

        ReturnObject returnObject = new ReturnObject();
        //增删改数据时可能会出现异常
        try {
            //调用service层,保存创建的市场活动
            int result = activityService.saveCreateActivity(activity);
            if (result > 0) {
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重拾");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setMessage("系统忙，请稍后重拾");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityByConditionForPage.do")
    public @ResponseBody Object queryActivityByConditionForPage(String name, String owner, String startDate, String endDate,
                                                                int pageNo, int pageSize) {
        //封装到map中
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);
        //调用service方法查询数据
        List<Activity> activityList = activityService.queryActivityByConditionForPage(map);
        int totalRows = activityService.queryCountOfActivityByCondition(map);
        //生成json响应信息，封装进对象中
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("activityList", activityList);
        resultMap.put("totalRows", totalRows);
        return resultMap;
    }

    @RequestMapping("/workbench/activity/deleteActivityByIds.do")
    public @ResponseBody Object deleteActivityByIds(String[] ids) {
        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层方法，删除市场活动
            int ret = activityService.deleteActivityByIds(ids);
            if (ret > 0) {
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);

            } else {
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityById.do")
    public @ResponseBody Object queryActivityById(String id) {
        //调用service方法查询市场活动
        Activity activity = activityService.queryActivityById(id);
        return activity;
    }

    @RequestMapping("/workbench/activity/saveEditActivity.do")
    public @ResponseBody Object saveEditActivity(Activity activity, HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        //封装参数
        activity.setEditTime(DateUtils.formateDateTime(new Date()));
        activity.setEditBy(user.getId());
        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层方法，保存修改的市场活动
            int ret = activityService.saveEditActivity(activity);
            if (ret > 0) {
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后再试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后再试");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/exportAllActivities.do")
    public void exportAllActivities(HttpServletResponse response) throws Exception{
        //调用service层方法，查询所有市场活动
        List<Activity> activityList = activityService.queryAllActivities();
        //创建excel文件，把activityList写入excel文件中
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("所有者");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");
        //遍历list填表
        if (activityList != null && activityList.size() > 0) {
            Activity activity = null;
            for (int i = 0; i < activityList.size(); i++) {
                activity = activityList.get(i);
                //每遍历一个市场活动对象，生成一行excel
                row = sheet.createRow(i + 1);
                //每一行有11列
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
//        //根据wb对象生成excel文件
//        FileOutputStream os = new FileOutputStream("D:\\Developer-kits\\Java\\workspace\\spanned-file-repository\\activityList.xls");
//        wb.write(os);
//        //关闭资源
//        os.close();
//        wb.close();
//        //把生成的excel文件下载到客户端
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=activityList.xls");
        ServletOutputStream outputStream = response.getOutputStream();
//        FileInputStream is = new FileInputStream("D:\\Developer-kits\\Java\\workspace\\spanned-file-repository\\activityList.xls");
//        byte[] buffer = new byte[256];
//        int len = 0;
//        while ((len = is.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, len);
//        }
//        is.close();
//        //Tomcat自己会关
//        outputStream.flush();
        //先吧文件从内存写到硬盘，再从硬盘读取到内存中，效率太低
        wb.write(outputStream);
        wb.close();
        outputStream.flush();
    }

    @RequestMapping("/workbench/activity/exportSelectedActivities.do")
    public void exportSelectedActivities(String ids, HttpServletResponse response) throws Exception {
        String[] idArray = ids.split(",");
        //调用service层方法，查询已选的市场活动
        List<Activity> activityList = activityService.queryActivitiesByCondition(idArray);
        //创建excel文件，把activityList写入excel文件中
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("所有者");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");
        //遍历list填表
        if (activityList != null && activityList.size() > 0) {
            Activity activity = null;
            for (int i = 0; i < activityList.size(); i++) {
                activity = activityList.get(i);
                //每遍历一个市场活动对象，生成一行excel
                row = sheet.createRow(i + 1);
                //每一行有11列
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=activityList.xls");
        ServletOutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        wb.close();
        outputStream.flush();
    }

    @RequestMapping("/workbench/activity/importActivity.do")
    public Object importActivity(MultipartFile activityFile, String userName, HttpSession session) {
        System.out.println("userName="+userName);
        User user=(User) session.getAttribute(Constant.SESSION_USER);
        ReturnObject returnObject=new ReturnObject();
        try {
            //把excel文件写到磁盘目录中
            /*String originalFilename = activityFile.getOriginalFilename();
            File file = new File("D:\\course\\18-CRM\\阶段资料\\serverDir\\", originalFilename);//路径必须手动创建好，文件如果不存在，会自动创建
            activityFile.transferTo(file);*/

            //解析excel文件，获取文件中的数据，并且封装成activityList
            //根据excel文件生成HSSFWorkbook对象，封装了excel文件的所有信息
            //InputStream is=new FileInputStream("D:\\course\\18-CRM\\阶段资料\\serverDir\\"+originalFilename);

            InputStream is=activityFile.getInputStream();
            HSSFWorkbook wb=new HSSFWorkbook(is);
            //根据wb获取HSSFSheet对象，封装了一页的所有信息
            HSSFSheet sheet=wb.getSheetAt(0);//页的下标，下标从0开始，依次增加
            //根据sheet获取HSSFRow对象，封装了一行的所有信息
            HSSFRow row=null;
            HSSFCell cell=null;
            Activity activity=null;
            List<Activity> activityList=new ArrayList<>();
            for(int i=1;i<=sheet.getLastRowNum();i++) {//sheet.getLastRowNum()：最后一行的下标
                row=sheet.getRow(i);//行的下标，下标从0开始，依次增加
                activity=new Activity();
                activity.setId(UUIDUtils.getUUID());
                activity.setOwner(user.getId());
                activity.setCreateTime(DateUtils.formateDateTime(new Date()));
                activity.setCreateBy(user.getId());
                for(int j=0;j<row.getLastCellNum();j++) {//row.getLastCellNum():最后一列的下标+1
                    //根据row获取HSSFCell对象，封装了一列的所有信息
                    cell=row.getCell(j);//列的下标，下标从0开始，依次增加
                    //获取列中的数据
                    String cellValue= HSSFUtils.getCellValueForStr(cell);
                    if(j==0){
                        activity.setName(cellValue);
                    }else if(j==1){
                        activity.setStartDate(cellValue);
                    }else if(j==2){
                        activity.setEndDate(cellValue);
                    }else if(j==3){
                        activity.setCost(cellValue);
                    }else if(j==4){
                        activity.setDescription(cellValue);
                    }
                }
                //每一行中所有列都封装完成之后，把activity保存到list中
                activityList.add(activity);
            }
            //调用service层方法，保存市场活动
            int ret=activityService.saveCreateActivityByList(activityList);
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setReturnData(ret);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/detailActivity.do")
    public String detailActivity(String id, HttpServletRequest request) {
        //调用service层方法，查询数据
        Activity activity = activityService.queryActivityForDetailById(id);
        List<ActivityRemark> remarkList = activityRemarkService.queryActivityRemarkForDetailByActivityId(id);
        //把数据保存到request中
        request.setAttribute("activity", activity);//这个操作重要
        request.setAttribute("remarkList", remarkList);
        //请求转发
        return "workbench/activity/detail";
    }
}
