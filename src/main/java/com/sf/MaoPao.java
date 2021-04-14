package com.sf;

import java.util.Arrays;

public class MaoPao {


    public static void main(String[] args) {
        int[] arrays = SourceData.getArrays();
        System.out.println(Arrays.toString(arrays));
        int arrayLength = arrays.length;
        maopao(arrayLength, arrays);
        System.out.println(Arrays.toString(arrays));
        int index = zbcz(arrayLength, arrays, 5);
        System.out.println(index);
    }

    /**
     * 冒泡排序
     *  index位和index + 1 比较 根据if()进行位置调整
     *  (arrayLength - i -1) 下面交换和比对的时出现数组越界
     */
    private static void maopao(int arrayLength, int[] arrays){
        for (int i = 0; i < arrayLength ; i++) {
            for (int j = 0; j < arrayLength - i - 1; j++) {
                if(arrays[j] > arrays[j+1]){
                    int temp = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = temp;
                }
            }
        }
    }

    /**
     * 二分查找(这般查找)
     * 前提条件数组有序(从小到大)
     * 查找数字位置
     * 查不到 返回 -1 查到了返回数字的第一位置index
     */

    private static int zbcz(int arrayLength, int[] arrays, int targetNumber){
        if(arrayLength == 0){
            return -1;
        }
        int left = 0;
        int right = arrayLength;
        while(left < right){
            int mid = (left + right) / 2;
            if(arrays[mid] == targetNumber){
                return mid;
            }else if(arrays[mid] < targetNumber){
                left = mid + 1;
            }else if(arrays[mid] > targetNumber){
                right = mid - 1;
            }
        }
        return -1;
    }

}
