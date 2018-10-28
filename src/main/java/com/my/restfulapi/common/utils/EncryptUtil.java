package com.my.restfulapi.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

    public static String md5Encrypt(String buf){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(buf.getBytes());
            StringBuffer buffer = new StringBuffer();
            //把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                //加盐 与运算
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            return buffer.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args){
        System.out.println(md5Encrypt("123456"));
    }
}