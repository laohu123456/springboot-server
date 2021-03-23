package com.sjms.mbsjms;

public class Tea extends MoBan{
    @Override
    public void second() {
        System.out.println("倒茶");
    }

    //茶水不需要放糖
    @Override
    public boolean gzhs() {
        return false;
    }


}
