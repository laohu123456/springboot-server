package com.sf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SourceData {

    private static List<Integer> list  = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int randomNum = random.nextInt(30);
            list.add(randomNum);
        }
    }

    public static int[] getArrays(){
        return SourceData.list.stream().mapToInt(Integer::intValue).toArray();
    }

}
