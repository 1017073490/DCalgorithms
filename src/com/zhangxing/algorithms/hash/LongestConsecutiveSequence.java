package com.zhangxing.algorithms.hash;

import java.util.HashSet;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {

    }

    public int longestConsecutiveAdvancedSuper(int[] nums) {
        int maxLen = 0;
        // 定义一个 HashSet，保存所有出现的数值
        HashSet<Object> hashSet = new HashSet<>();
        // 遍历一遍所有元素
        for (int num : nums) {
            hashSet.add(num);
        }
        // 遍历数组，以每个元素作为起始点，寻找连续序列
        for (int i = 0; i < nums.length; i++) {
            // 当前元素作为起始点
            int curNum = nums[i];
            // 保存当前连续子序列长度
            int curLen = 1;
            // 只有在当前元素的前驱不存在的情况下，才去进行寻找连续序列的操作
            if (!hashSet.contains(curNum - 1)) {
                // 寻找后续数字
                while (hashSet.contains(curNum + 1)) {
                    curLen++;
                    curNum++;
                }
                maxLen = curLen > maxLen ? curLen : maxLen;
            }
        }
        return maxLen;
    }

    public int longestConsecutiveAdvanced(int[] nums) {
        int maxLen = 0;
        // 定义一个 HashSet，保存所有出现的数值
        HashSet<Object> hashSet = new HashSet<>();
        // 遍历一遍所有元素
        for (int num : nums) {
            hashSet.add(num);
        }
        // 遍历数组，以每个元素作为起始点，寻找连续序列
        for (int i = 0; i < nums.length; i++) {
            // 当前元素作为起始点
            int curNum = nums[i];
            // 保存当前连续子序列长度
            int curLen = 1;
            // 寻找后续数字
            while (hashSet.contains(curNum + 1)) {
                curLen++;
                curNum++;
            }
            maxLen = curLen > maxLen ? curLen : maxLen;
        }
        return maxLen;
    }


    public int longestConsecutive(int[] nums) {
        // 暴力法
        // 定义一个长度，保持当前最长连续序列的长度
        int maxLen = 0;
        // 遍历数组，以每个元素作为起始点，寻找连续序列
        for (int i = 0; i < nums.length; i++) {
            // 当前元素作为起始点
            int curNum = nums[i];
            // 保存当前连续子序列长度
            int curLen = 1;
            // 寻找后续数字
            while (contains(nums, curNum + 1)) {
                curLen++;
                curNum++;
            }
            maxLen = curLen > maxLen ? curLen : maxLen;
        }
        return maxLen;
    }

    // 定义一个方法，用于在数组中寻找某个元素
    public boolean contains(int[] nums, int x) {
        for (int num : nums) {
            if (num == x) {
                return true;
            }
        }
        return false;
    }
}
