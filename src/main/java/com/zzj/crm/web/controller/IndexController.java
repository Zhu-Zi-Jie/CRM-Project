package com.zzj.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    //框架规定省去 协议://ip:port/应用名 ，用/代表应用根目录下的/
    @RequestMapping("/")
    public String index() {
        return "index"; //由视图解析器添加前缀和后缀形成完整的资源访问路径
    }
}
