/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * FileUtils.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2016-1-12, TangWeicheng, Create file
 */

package com.tplink.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtils {

    public static String bytes2mb(long bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
        return String.format("%.2f", returnValue);
    }

    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            // 因为如果以MB为单位的话，要保留最后1位小数，
            // 因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            // 最大的单位是GB
            size = (size * 100) / 1024;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
        }
    }

    public static void unZipFile(String path, File file) throws Exception {
        if ((file == null) || !file.exists()) {
            return;
        }

        ZipInputStream Zin = new ZipInputStream(new FileInputStream(file));
        BufferedInputStream Bin = new BufferedInputStream(Zin);
        FileOutputStream out = null;
        BufferedOutputStream Bout = null;
        File Fout = null;
        ZipEntry entry;
        try {
            while (((entry = Zin.getNextEntry()) != null) && !entry.isDirectory()) {
                Fout = new File(path, entry.getName());
                if (!Fout.exists()) {
                    (new File(Fout.getParent())).mkdirs();
                }
                out = new FileOutputStream(Fout);
                Bout = new BufferedOutputStream(out);
                int b;
                while ((b = Bin.read()) != -1) {
                    Bout.write(b);
                }
                Bout.close();
                out.close();
            }
            Bin.close();
            Zin.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Bout.close();
                out.close();
                Bin.close();
                Zin.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static List<File> getAllFilesInPath(String path) {

        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (!files[i].isDirectory()) {
                    list.add(files[i]);
                }
            }
            return list;
        }
        return null;
    }

    public static String readStringFromFile(File f) {

        String s = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));// 读取原始json文件
            s = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
            s = null;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return s;
    }

}
