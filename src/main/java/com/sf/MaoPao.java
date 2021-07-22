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

    /**
     * 插入排序
     * 默认从第二数据开始,
     * 如果第二个数据比第一个数据小，则进行交换，在和第三个数进行比较，如果比前的数字小则插入，否则，退出
     * @param array
     * @return
     */
    public static int[] charupaixu(int[] array){
        for (int i = 1; i < array.length; i++) {
            for (int j = i ; j > 0 ; j--) {
                if(array[j] < array[j-1]){
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }else{
                    break;
                }

            }
        }
        return array;
    }


    /**
     * 选择排序
     * 假定第一个数字最小，然后标记一下最小数字的索引
     * 那第一个数字和后面的数字逐个进行比较，找出最小的数字的索引,比较两次索引是否相等
     * 不相等则对两个索引的数字进行交换，反之，则不管
     * @param array
     * @return
     */
    public static int[] xuanzepaixu(int[] array){
        for (int i = 0; i < array.length; i++) {
            int min = i;

            for (int j = i + 1; j < array.length ; j++) {
                if(array[min] > array[j]){
                    min = j;
                }
            }
            if(min != i){
                int temp = array[min];
                array[min] = array[i];
                array[i] = temp;
            }
        }
        return array;
    }

}
