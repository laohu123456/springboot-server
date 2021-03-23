package com.sjms.mbsjms;

public  abstract class MoBan {

    public void moban(){
        first();
        second();
        if(gzhs()){
            third();
        }
        fourth();
    }


    //主要练习钩子函数

    public void first(){
        System.out.println("烧水");
    }

    public abstract void second();

    public void third(){
        System.out.println("放糖");
    }

    public void fourth(){
        System.out.println("品尝");
    }

    public boolean gzhs(){
        return true;
    }

}
