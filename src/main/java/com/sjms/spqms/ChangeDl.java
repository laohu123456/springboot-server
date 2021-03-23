package com.sjms.spqms;

public class ChangeDl implements DC5{
    
    private AC220 ac220 = new AC220();
    
    @Override
    public int changeDC() {
        int ac220A = ac220.resultAC();
        System.out.println("ac220A   " + ac220A);
        //通过配电器

        return 5;
    }
}
