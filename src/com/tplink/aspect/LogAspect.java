/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * LogAspect.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-27, TangWeicheng, Create file
 */

package com.tplink.aspect;

import com.tplink.utils.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Order(1)
public class LogAspect {

    @Pointcut("@annotation(com.tplink.annotation.LogRequired)")
    public void logPointcut() {

    }

    private static final String LOG_STR = "%s:%s:requestHeader=%s;requestParams=%s;responces=%s;sessionParams=%s;executeTime=%d";

    @Around("logPointcut()")
    public Object Log(ProceedingJoinPoint pj) {

        long start = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
                .getRequestAttributes()).getRequest();

        // 获取所有session参数
        Map<String, Object> se = new HashMap<>();
        Enumeration<String> em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            String str = em.nextElement();
            se.put(str, request.getSession().getAttribute(str));
        }

        // 获取http头部信息
        Map<String, Object> header = new HashMap<>();
        em = request.getHeaderNames();
        while (em.hasMoreElements()) {
            String str = em.nextElement();
            header.put(str, request.getHeader(str));
        }

        // 获取所有参数
        Map<String, String> param = new HashMap<>();
        for (Entry<String, String[]> en : request.getParameterMap().entrySet()) {
            param.put(en.getKey(), Arrays.toString(en.getValue()));
        }

        // 获取函数返回值
        Object rvt = null;
        try {
            rvt = pj.proceed(pj.getArgs());
        } catch (Throwable e) {
            e.printStackTrace();
            rvt = null;
        }

        String s = String.format(LOG_STR, DateFormat.getDateTimeInstance().format(new Date()),
                pj.toString(), header, param, rvt, se, (System.currentTimeMillis() - start));
        Log.thread(s);

        return rvt;
    }
}
