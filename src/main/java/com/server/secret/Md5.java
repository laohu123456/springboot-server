package com.server.secret;

public class Md5 {

    public static String encrypt(String value){
        System.out.println("加密 :" + value);
        return value + "-";
    }

    public static String  dencrypt(String value){
        System.out.println("解密 :" + value);
        return value.replaceAll("-","");
    }

}
