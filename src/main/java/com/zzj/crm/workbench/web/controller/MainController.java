package com.zzj.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.workbench.web.controller
 * @Author: zzj
 * @CreateTime: 2022-07-15  23:30
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
public class MainController {
    @RequestMapping("/workbench/main/index.do")
    public String index() {
        //跳转到main/index.jsp
        return "workbench/main/index";
    }
}
