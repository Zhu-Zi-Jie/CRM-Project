package com.zzj.crm.settings.web.interceptor;

import com.zzj.crm.commons.constant.Constant;
import com.zzj.crm.settings.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.settings.web.interceptor
 * @Author: zzj
 * @CreateTime: 2022-07-15  21:15
 * @Description: TODO
 * @Version: 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //如果用户没有登录成功，则跳转到登录页面
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath());//没有使用springmvc框架，在原生的servelet中url需要补充工程路径,
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
