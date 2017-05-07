/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * BaseRepository.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-27, TangWeicheng, Create file
 */

package com.tplink.repository;

import com.alibaba.druid.pool.DruidDataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public abstract class BaseRepository<T> {

    @Autowired
    DruidDataSource dataSource;

    Random ran = new Random();

    public JSONArray executeSql(String sql) {

        System.out.println("execute SQL:" + sql);
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            Statement st = conn.createStatement();
            ResultSet set = st.executeQuery(sql);
            JSONArray srr = resultsetToJsonArray(set);
            // printResultSet(set);
            System.out.println("execute result=:" + srr);
            return srr;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private JSONArray resultsetToJsonArray(ResultSet rs) {
        JSONArray arr = new JSONArray();
        try {

            JSONObject ob = null;

            ResultSetMetaData m = rs.getMetaData();
            int columns = m.getColumnCount();
            // // 显示列,表格的表头
            // for (int i = 1; i <= columns; i++) {
            // System.out.print(m.getColumnName(i));
            // System.out.print("\t\t");
            // }
            //
            // System.out.println();
            // 显示表格内容
            while (rs.next()) {
                ob = new JSONObject();
                for (int i = 1; i <= columns; i++) {
                    ob.put(m.getColumnName(i), rs.getString(i));
                    // System.out.print(m.getColumnName(i) + "\t\t" +
                    // rs.getString(i));
                }
                // System.out.println();
                arr.put(ob);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    private void printResultSet(ResultSet rs) {
        try {
            ResultSetMetaData m = rs.getMetaData();
            int columns = m.getColumnCount();
            // 显示列,表格的表头
            for (int i = 1; i <= columns; i++) {
                System.out.print(m.getColumnName(i));
                System.out.print("\t\t");
            }

            System.out.println();
            // 显示表格内容
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.print(rs.getString(i));
                    System.out.print("\t\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
