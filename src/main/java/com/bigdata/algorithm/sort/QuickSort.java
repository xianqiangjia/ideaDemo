package com.bigdata.algorithm.sort;

/**
 * 快排使用的二分法，选一个基准数据经比较
 */
public class QuickSort {

    public static void sort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }
        int i = low;
        int j = high;
        int key = arr[low];

        while (i < j) {
            while (arr[j] > key && i < j) {
                j--;
            }
            while ((arr[i] <= key) && i < j) {
                i++;
            }
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

            }
        }

        int p = arr[i];
        arr[i]=arr[low];
        arr[low] = p;

        sort(arr, low, i - 1);
        sort(arr, i + 1, high);
    }
}
