package com.zhangxing.algorithms.arrays;

public class NextPermutation {

    public static void main(String[] args) {
        int[] input = {3, 2, 1};
        NextPermutation nextPermutation = new NextPermutation();
        nextPermutation.nextPermutationMy(input);
        for (int i : input) {
            System.out.println(i);
        }
    }
    private static void swapMy(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private static void fanzhuan(int[] nums, int start, int end) {
        while (start < end) {
            swapMy(nums, start, end);
            start++;
            end--;
        }
    }
    public void nextPermutationMy(int[] nums) {
        int tar = nums.length - 2;
        while (tar >= 0 && nums[tar] >= nums[tar + 1]) {
            tar--;
        }
        if (tar == -1) {
            fanzhuan(nums, 0, nums.length - 1);
            return;
        }
        int res = tar + 2;
        while (res < nums.length && nums[tar] < nums[res]) {
            res++;
        }
        swap(nums, tar, res - 1);
        // 剩下直接反转
        fanzhuan(nums, tar + 1, nums.length - 1);
    }
    public void nextPermutation(int[] nums) {
        // 思路：从后向前，找到升序子序列，然后调整子序列的最高位，剩余部分升序排列
        int length = nums.length;
        // 0. 从后向前，找到升序子序列，找到第一次下降的数，位置记为 k
        int k = length - 2;
        while (k >= 0 && nums[k] >= nums[k + 1]) {
            k--;
        }
        // 1. 找到目标 k，需要调整部分的最高位
        // 2. 特殊情况，k = -1
        if (k == -1) {
            reverse(nums, 0, length - 1);
            return;
        }
        // 3. 一般情况，k >= 0
        // 3.1 依次遍历剩余降序部分，找到替换最高位的那个数
        // 其是小于 k 的那个数的前一位
        int i = k + 2;
        while (i < length && nums[i] > nums[k]) {
            i++;
        }
        // 3.2 i - 1 就是需要替换的数字，交换 i - 1 和 k 的数值
        swap(nums, k, i - 1);
        // 3.3 k 下标之后的所有数升序排列，因为前面操作之后，还是一个降序序列，
        // 所以可以使用 双指针交替替换
        reverse(nums, k + 1, length - 1);
    }

    private void swap(int[] nums, int k, int i) {
        // 交换操作封装函数
        int temp = nums[k];
        nums[k] = nums[i];
        nums[i] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        // 翻转数组封装函数
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
