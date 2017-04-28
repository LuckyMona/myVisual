/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * LoginChecker.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-26, TangWeicheng, Create file
 */

package com.tplink.aspect;

import com.tplink.controller.AccountController;
import com.tplink.controller.BasicController;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Order(2)
public class LoginChecker {

    @Pointcut("@annotation(com.tplink.annotation.LoginCheck)")
    public void loginCheckPointcut() {

    }

    private List<String> excludeUrls;

    @Around("loginCheckPointcut()")
    public Object checkIfUserIsLogin(ProceedingJoinPoint pj) {

        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
                .getRequestAttributes()).getRequest();

        String url = request.getRequestURI();
        boolean shouldLogin = true;
        for (String str : excludeUrls) {
            if (url.contains(str)) {
                shouldLogin = false;
            }
        }

        HttpSession session = request.getSession();
        // 读取session中的用户,当用户不为空时才执行
        if (shouldLogin && (session.getAttribute(AccountController.ACCOUNT_LOGIN_KEY) == null)) {

            Map<String, String> result = new HashMap<>();
            result.put(BasicController.TYPE, BasicController.LOGIN);
            result.put(BasicController.ERROR_MESSAGE, "操作前请登录");

            return result;
        }

        try {
            return pj.proceed(pj.getArgs());
        } catch (Throwable e) {
            e.printStackTrace();
            Map<String, String> result = new HashMap<>();
            result.put(BasicController.TYPE, BasicController.ERROR);
            result.put(BasicController.ERROR_MESSAGE, "503 服务器内部错误：" + e.getMessage());

            return result;
        }

    }

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }
}
