package com.sjms.clsjms;

public class Test {

    public static void main(String[] args) {
        CeLue ceLue = new CeLue();
        ceLue.setCelueMethod(new Wx());
      //  ceLue.setCelueMethod(new Zfb());
        ceLue.dyMethod();
    }

}

