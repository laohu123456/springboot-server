package com.sjms.clsjms;

public  class CeLue {


    private CelueInterface celueInterface;

    public void setCelueMethod(CelueInterface celueInterface){
        this.celueInterface = celueInterface;
    }

    public void dyMethod(){
        celueInterface.celueInterface();
    }

    public void first(){
        System.out.println("通用的方法,外部调用");
        dyMethod();
    }

}
