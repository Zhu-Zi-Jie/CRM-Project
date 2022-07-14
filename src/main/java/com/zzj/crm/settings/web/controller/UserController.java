package com.zzj.crm.settings.web.controller;

import com.zzj.crm.commons.constant.Constant;
import com.zzj.crm.commons.domain.ReturnObject;
import com.zzj.crm.commons.utils.DateUtils;
import com.zzj.crm.settings.domain.User;
import com.zzj.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: CRM_Project
 * @BelongsPackage: com.zzj.crm.settings.web.Controller
 * @Author: zzj
 * @CreateTime: 2022-07-12  21:54
 * @Description: TODO
 * @Version: 1.0
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin() {
        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    public @ResponseBody Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        //封装参数为map集合，sql语句不需要isRemPed所以不需要封装进map
        Map<String, Object> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);
        //调用service层方法，查询用户
        User user = userService.queryUserByLoginActAndPwd(map);
        //根据查询结果，生成响应信息
        ReturnObject returnObject = new ReturnObject();
        if (user == null) {
            //登录失败，用户名或密码错误
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户或密码错误");
        }else {//进一步判断账号是否合法
            String nowDateStr = DateUtils.formateDateTime(new Date());
            if (nowDateStr.compareTo(user.getExpireTime()) > 0) {
                //登录失败，账号已过期
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号已过期");
            } else if ("0".equals(user.getLockState())) {
                //登录失败，状态被锁定
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("状态以锁定");
            } else if (!user.getAllowIps().contains(request.getRemoteAddr())) {
                //登录失败，不在常用的ip地址内
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip受限");
            } else {
                //登录成功
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);
                //把user保存到session中
                session.setAttribute(Constant.SESSION_USER, user);
                //如果需要记住密码，则往外写cookie
                if ("true".equals(isRemPwd)) {
                    Cookie cookie1 = new Cookie("loginAct", user.getLoginAct());
                    cookie1.setMaxAge(10 * 24 * 60 * 60);//最多保存十天
                    response.addCookie(cookie1);
                    Cookie cookie2 = new Cookie("loginPwd", user.getLoginPwd());
                    cookie1.setMaxAge(10 * 24 * 60 * 60);//最多保存十天
                    response.addCookie(cookie2);
                } else {
                    //把没有过期的cookie删除
                    Cookie cookie1 = new Cookie("loginAct", "null");
                    cookie1.setMaxAge(0);//间接手段删除浏览器中的cookie
                    response.addCookie(cookie1);
                    Cookie cookie2 = new Cookie("loginPwd", "null");
                    cookie1.setMaxAge(0);
                    response.addCookie(cookie2);
                }
            }
        }
        return returnObject;
    }
}
