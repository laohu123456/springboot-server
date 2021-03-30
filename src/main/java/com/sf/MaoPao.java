package com.sf;

import java.util.Arrays;

public class MaoPao {


    public static void main(String[] args) {
        int[] arrays = SourceData.getArrays();
        System.out.println(Arrays.toString(arrays));
        int arrayLength = arrays.length;
        maopao(arrayLength, arrays);
        System.out.println(Arrays.toString(arrays));
    }

    /**
     * 冒泡排序
     *  index位和index + 1 比较 根据if()进行位置调整
     *  (arrayLength - i -1) 下面交换和比对的时出现数组越界
     */
    private static void maopao(int arrayLength, int[] arrays){
        for (int i = 0; i < arrayLength - 1; i++) {
            for (int j = 0; j < arrayLength - i - 1; j++) {
                if(arrays[j] > arrays[j+1]){
                    int temp = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = temp;
                }
            }
        }
    }

}
