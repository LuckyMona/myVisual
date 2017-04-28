/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * AccountEncoder.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-3-10, TangWeicheng, Create file
 */

package com.tplink.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AccountEncoder {

    private static final String ALGORITHM = "SHA-256";

    public static String getEncodePassword(String account, String password) {

        if (isPrintableAscii(account) && isPrintableAscii(password)) {
            return encode(password + account);
        }
        return null;
    }

    private static String encode(String src) {
        String dst = null;

        if ((src != null) && (src.length() > 0)) {
            try {
                MessageDigest md = MessageDigest.getInstance(ALGORITHM);
                byte[] result = md.digest(src.getBytes());
                dst = getBase64(result);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                dst = getBase64(src.getBytes());
            }
        }

        return dst;
    }

    private static String getBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static boolean isPrintableAscii(String string) {
        boolean ascii = false;

        if (string != null) {
            final int asciiStart = 0x20;
            final int asciiEnd = 0x7E;
            char c;

            ascii = true;

            for (int i = 0; i < string.length(); i++) {
                c = string.charAt(i);

                if ((c < asciiStart) || (c > asciiEnd)) {
                    ascii = false;
                    break;
                }
            }
        }

        return ascii;
    }

}
