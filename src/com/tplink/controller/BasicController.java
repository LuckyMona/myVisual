/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * BasicController.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-3-13, TangWeicheng, Create file
 */

package com.tplink.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class BasicController.
 *
 * @author admin
 */
public abstract class BasicController {

    public static final String TYPE = "type";

    public static final String ERROR_MESSAGE = "errorMsg";

    public static final String SUCCESS = "success";

    public static final String ERROR = "error";

    public static final String LOGIN = "login";

    Map<String, Object> resultMap = new HashMap<>();

    public void setResult(String key, Object obj) {
        resultMap.put(key, obj);
    }

    public void setType(Object value) {
        resultMap.put(TYPE, value);
    }

    public void setErrorMessage(Object value) {
        resultMap.put(ERROR_MESSAGE, value);
    }

    public Map<String, Object> getResult() {
        return resultMap;
    }

}
