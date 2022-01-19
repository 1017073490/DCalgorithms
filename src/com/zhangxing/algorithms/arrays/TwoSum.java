package com.zhangxing.algorithms.arrays;

import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {
        int[] inputArray = new int[100000];
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = i;
        }
        int[] input = {2, 2};
        long start = System.currentTimeMillis();
        int[] res = new TwoSum().twoSum(inputArray, 99999);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");

        for (int re : res) {
            System.out.println(re + "\t");
        }

    }

    public int[] twoSum(int[] nums, int target) {
        // 穷举法
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("no solution");
    }

    public int[] twoSum2(int[] nums, int target) {
        // 哈希表保存信息
        HashMap<Integer, Integer> map = new HashMap<>();
        // 1.保存数据
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        // 2.再次遍历，根据哈希表信息查找target
        for (int i = 0; i < nums.length; i++) {
            int targetNum = target - nums[i];
            // 如果那个数存在，且不是自己本身
            if (map.containsKey(targetNum) && map.get(targetNum) != i) {
                return new int[]{i, map.get(targetNum)};
            }
        }
        throw new IllegalArgumentException("no solution");
    }

    public int[] twoSum3(int[] nums, int target) {
        // 哈希表保存信息
        HashMap<Integer, Integer> map = new HashMap<>();
        // 一次遍历，根据哈希表信息查找target
        for (int i = 0; i < nums.length; i++) {
            int targetNum = target - nums[i];
            // 如果那个数存在，且不是自己本身
            if (map.containsKey(targetNum)) {
                return new int[]{map.get(targetNum), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("no solution");
    }
}
