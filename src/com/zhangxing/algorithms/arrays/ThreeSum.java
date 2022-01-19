package com.zhangxing.algorithms.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {
        int[] input = {-1, 0, 1, 2, -1, -4};
        System.out.println(new ThreeSum().threeSum(input));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        // 双指针解决，使用三元组=核心+（左右指针）
        List<List<Integer>> resList = new ArrayList<>();
        if (nums == null || nums.length == 1 || nums.length == 2) {
            return resList;
        }
        int n = nums.length;
        // 0.数组排序
        Arrays.sort(nums);
        // 1.遍历每一个元素，作为三元组的最小核心
        for (int i = 0; i < n; i++) {
            // 1.1 当前数大于0，直接结束
            if (nums[i] > 0) break;
            // 1.2 当前数字出现过，跳过
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 1.3 以当前数做最小数
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                // 判断sum，与0对比
                if (sum == 0) {
                    // 一组解
                    resList.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return resList;
    }
}
