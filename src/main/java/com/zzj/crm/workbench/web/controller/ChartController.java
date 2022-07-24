package com.zzj.crm.workbench.web.controller;

import com.zzj.crm.workbench.domain.FunnelVO;
import com.zzj.crm.workbench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.workbench.web.controller
 * @Author: zzj
 * @CreateTime: 2022-07-24  16:49
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
public class ChartController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/workbench/chart/transaction/index.do")
    public String index(){
        //跳转页面
        return "workbench/chart/transaction/index";
    }

    @RequestMapping("/workbench/chart/transaction/queryCountOfTranGroupByStage.do")
    public @ResponseBody Object queryCountOfTranGroupByStage(){
        //调用service层方法，查询数据
        List<FunnelVO> funnelVOList=transactionService.queryCountOfTranGroupByStage();
        //根据查询结果，返回响应信息
        return funnelVOList;
    }
}
