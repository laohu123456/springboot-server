package com.sjms.spqms;


public class Test {

    /**
     * 适配器模式
     */

    public static void main(String[] args) {
        ChangeDl changeDl = new ChangeDl();
        int dc = changeDl.changeDC();
        System.out.println("通过changeDC变压器 220 ---> " + dc + "A");
    }

}
