/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * JsonUtils.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-24, TangWeicheng, Create file
 */

package com.tplink.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public class JsonUtils {

    /**
     * 将json转化为实体POJO
     *
     * @param jsonStr
     * @param obj
     * @return
     */
    public static <T> Object JSONToObj(String jsonStr, Class<T> clazz) {
        T t = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            t = objectMapper.readValue(jsonStr, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将实体POJO转化为JSON
     *
     * @param obj
     * @return
     * @throws JSONException
     * @throws IOException
     */
    public static <T> JSONObject objectToJson(T obj) throws JSONException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Convert object to JSON string
        String jsonStr = "";
        try {
            jsonStr = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw e;
        }
        return new JSONObject(jsonStr);
    }

    public static JSONObject combineTwoJson(JSONObject o1, JSONObject o2) {

        for (Iterator<String> it = o1.keys(); it.hasNext();) {
            String key = it.next();
            try {
                o2.put(key, o1.get(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return o2;
    }
}
