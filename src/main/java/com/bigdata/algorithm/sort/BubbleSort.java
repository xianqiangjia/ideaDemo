package com.bigdata.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort {

    public static int[] sort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) { //趟数
            for (int j = 0; j < arr.length - 1 - i; j++) {  //比较交换的次数
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
