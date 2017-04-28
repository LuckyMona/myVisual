/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * HttpRequestUtils.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-3-27, TangWeicheng, Create file
 */

package com.tplink.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtils {

    public static String getIp2(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    private static boolean isNotEmpty(String ip) {
        return (ip != null) && (ip.length() > 0);
    }

}
