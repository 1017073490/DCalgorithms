package com.zhangxing.algorithms.arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(binarySearch(array, 9));
        System.out.println(binarySearch(array, 9, 0, array.length - 1));
    }


    public static int binarySearch(int[] array, int key) {
        // 定义初始查找范围，双指针
        int low = 0;
        int high = array.length - 1;
        // 排除特殊情况
        if (key < array[low] || key > array[high]) {
            return -1;
        }
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key < array[mid]) {
                high = mid - 1;
            } else if (key > array[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] array, int key, int fromIndex, int toIndex) {
        // 基本判断：起始位置大于结束位置时，结束
        if (fromIndex > toIndex || key < array[fromIndex] || key > array[toIndex]) {
            return -1;
        }
        int mid = fromIndex + (toIndex - fromIndex) / 2;
        // 比较，更改范围，递归
        if (key < array[mid]) {
            return binarySearch(array, key, fromIndex, mid - 1);
        } else if (key > array[mid]) {
            return binarySearch(array, key, mid + 1, toIndex);
        } else {
            return mid;
        }
    }
}
