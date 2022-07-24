package com.zzj.crm.workbench.web.controller;

import com.zzj.crm.commons.constant.Constant;
import com.zzj.crm.commons.domain.ReturnObject;
import com.zzj.crm.settings.domain.DicValue;
import com.zzj.crm.settings.domain.User;
import com.zzj.crm.settings.service.DicValueService;
import com.zzj.crm.settings.service.UserService;
import com.zzj.crm.workbench.domain.Transaction;
import com.zzj.crm.workbench.domain.TransactionHistory;
import com.zzj.crm.workbench.domain.TransactionRemark;
import com.zzj.crm.workbench.service.CustomerService;
import com.zzj.crm.workbench.service.TransactionHistoryService;
import com.zzj.crm.workbench.service.TransactionRemarkService;
import com.zzj.crm.workbench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.workbench.web.controller
 * @Author: zzj
 * @CreateTime: 2022-07-23  23:39
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
public class TransactionController {
    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRemarkService transactionRemarkService;

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @RequestMapping("/workbench/transaction/index.do")
    public String index(HttpServletRequest request){
        //调用service层方法，查询动态数据
        List<DicValue> stageList=dicValueService.queryDicValueByTypeCode("stage");
        List<DicValue> transactionTypeList=dicValueService.queryDicValueByTypeCode("transactionType");
        List<DicValue> sourceList=dicValueService.queryDicValueByTypeCode("source");
        //把数据保存到request
        request.setAttribute("stageList",stageList);
        request.setAttribute("transactionTypeList",transactionTypeList);
        request.setAttribute("sourceList",sourceList);
        //请求转发
        return "workbench/transaction/index";
    }

    @RequestMapping("/workbench/transaction/toSave.do")
    public String toSave(HttpServletRequest request){
        //调用service层方法，查询动态数据
        List<User> userList = userService.queryAllUser();
        List<DicValue> stageList=dicValueService.queryDicValueByTypeCode("stage");
        List<DicValue> transactionTypeList=dicValueService.queryDicValueByTypeCode("transactionType");
        List<DicValue> sourceList=dicValueService.queryDicValueByTypeCode("source");
        //把数据保存到request中
        request.setAttribute("userList",userList);
        request.setAttribute("stageList",stageList);
        request.setAttribute("transactionTypeList",transactionTypeList);
        request.setAttribute("sourceList",sourceList);
        //请求转发
        return "workbench/transaction/save";
    }

    @RequestMapping("/workbench/transaction/getPossibilityByStage.do")
    public @ResponseBody
    Object getPossibilityByStage(String stageValue){
        //解析properties配置文件，根据阶段获取可能性
        ResourceBundle bundle=ResourceBundle.getBundle("possibility");
        String possibility=bundle.getString(stageValue);
        //返回响应信息
        return possibility;
    }

    @RequestMapping("/workbench/transaction/queryCustomerNameByName.do")
    public @ResponseBody Object queryCustomerNameByName(String customerName){
        //调用service层方法，查询所有客户名称
        List<String> customerNameList=customerService.queryCustomerNameByName(customerName);
        //根据查询结果，返回响应信息
        return customerNameList;//['xxxx','xxxxx',......]
    }

    @RequestMapping("/workbench/transaction/saveCreateTran.do")
    public @ResponseBody Object saveCreateTran(@RequestParam Map<String,Object> map, HttpSession session){
        //@RequestParam的使用！
        //封装参数
        map.put(Constant.SESSION_USER,session.getAttribute(Constant.SESSION_USER));

        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service层方法，保存创建的交易
            transactionService.saveCreateTran(map);
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/transaction/detailTran.do")
    public String detailTran(String id,HttpServletRequest request){
        //调用service层方法，查询数据
        Transaction tran=transactionService.queryTranForDetailById(id);
        List<TransactionRemark> remarkList=transactionRemarkService.queryTranRemarkForDetailByTranId(id);
        List<TransactionHistory> historyList=transactionHistoryService.queryTranHistoryForDetailByTranId(id);

        //根据tran所处阶段名称查询可能性
        ResourceBundle bundle=ResourceBundle.getBundle("possibility");
        String possibility=bundle.getString(tran.getStage());
        tran.setPossibility(possibility);

        //把数据保存到request中
        request.setAttribute("tran",tran);
        request.setAttribute("remarkList",remarkList);
        request.setAttribute("historyList",historyList);
        //request.setAttribute("possibility",possibility);

        //调用service方法，查询交易所有的阶段
        List<DicValue> stageList=dicValueService.queryDicValueByTypeCode("stage");
        request.setAttribute("stageList",stageList);

        //请求转发
        return "workbench/transaction/detail";
    }
}
