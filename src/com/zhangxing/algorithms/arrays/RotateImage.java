package com.zhangxing.algorithms.arrays;

public class RotateImage {
    public static void main(String[] args) {
        int[][] imageThree = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] imageFour = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        new RotateImage().rotateMy(imageFour);

        for (int[] image : imageFour) {
            for (int num : image) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }
    }

    public void rotateMy(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }

    public void rotate(int[][] matrix) {
        // 数学方法：先转置，再翻转每一行
        int len = matrix.length;
        // 0. 转置：对角线不动，对角线左右两边互换
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 1. 翻转
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][len - j - 1];
                matrix[i][len - j - 1] = temp;
            }
        }
    }

}
