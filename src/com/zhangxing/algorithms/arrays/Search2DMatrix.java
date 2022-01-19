package com.zhangxing.algorithms.arrays;

public class Search2DMatrix {
    public static void main(String[] args) {
        int[][] array = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 62;
        System.out.println(new Search2DMatrix().searchMatrix(array,target));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        // 既然二维数组已经排好序，将其变化为一维，就是排好序的一维数组
        // 再用二分查找，下标对应关系：idx = row * n + col
        if (matrix == null) {
            return false;
        }
        // 所以先定义出 m 和 n
        int m = matrix.length;
        int n = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[m-1][n-1]) {
            return false;
        }
        // 二分，定义左右指针
        int left = 0;
        int right = m * n - 1;
        while (left <= right) {
            // 计算中间位置
            int mid = left + (right - left) / 2;
            // 一维展开无法直接取到，要计算其下标
            int midElement = matrix[mid / n][mid % n];
            if (target < midElement) {
                right = mid - 1;
            } else if (target > midElement) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
