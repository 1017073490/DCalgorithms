package com.zhangxing.algorithms.hash;

import java.util.HashSet;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {

    }

    public int longestConsecutiveByMyselfWithTheHelpOfCoding(int[] nums) {
        int max = 0;
        HashSet set = new HashSet();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 0; i < nums.length; i++) {
            int curNum = nums[i];
            int curLen = 1;
            if (!set.contains(curNum - 1)) {
                while (set.contains(curNum + 1)) {
                    curNum++;
                    curLen++;
                }
            }
            max = curLen > max ? curLen : max;
        }
        return max;
    }

    public int longestConsecutiveAdvancedSuper(int[] nums) {
        // 进一步优化：既然存在大量重复辨别，使用 前驱 来帮助跳过重复
        // 遍历到当前数字时，只要 HsehSet 中的 "当前数字 - 1" 存在，就跳过
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
        // 暴力法优化：contains 函数的功能交给 HashSet 来执行，降低时间复杂度
        // 思路几乎一样，不过先使用一次遍历来一劳永逸地将数据存入 HashSet，便于后面使用
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
        // 暴力法：时间复杂度过高
        // 定义一个长度，保持当前最长连续序列的长度
        int maxLen = 0;
        // 遍历数组，以每个元素作为起始点，寻找连续序列
        for (int i = 0; i < nums.length; i++) {
            // 当前元素作为起始点
            int curNum = nums[i];
            // 保存当前连续子序列长度
            int curLen = 1;
            // 寻找后续数字，只有当前 nums 继续包含 [ 当前数 + 1 ]，就继续，同时长度++
            while (contains(nums, curNum + 1)) {
                curLen++;
                curNum++;
            }
            maxLen = curLen > maxLen ? curLen : maxLen;
        }
        return maxLen;
    }

    public boolean contains(int[] nums, int x) {
        // 定义一个方法，用于在数组中寻找某个元素
        for (int num : nums) {
            if (num == x) {
                return true;
            }
        }
        return false;
    }
}
