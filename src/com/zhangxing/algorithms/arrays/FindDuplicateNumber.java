package com.zhangxing.algorithms.arrays;

import java.util.Arrays;
import java.util.HashMap;

public class FindDuplicateNumber {
    public static void main(String[] args) {
        int[] array = {1, 3, 4, 2, 2};
        System.out.println(new FindDuplicateNumber().findDuplicate3(array));
    }

    /**
     * 使用 hashMap 保存每个数出现的次数
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            if (countMap.containsKey(num)) {
                return num;
            } else {
                countMap.put(num, 1);
            }
        }
        return -1;
    }

    /**
     * 先排序，然后找相邻的相同元素
     * 但是时间复杂度增加
     *
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    public int findDuplicate3(int[] nums) {
        // 二分查找：
        // 查找的是 1-N 自然序列中谁是 target
        // 定义左右指针
        int left = 1;
        int right = nums.length - 1;
        while (left <= right) {
            // 计算中间值
            int mid = (left + right) / 2;
            // 对遍历到的当前 mid 计算 count 值
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            // 判断 count 和 mid 本身的大小关系
            // count 小于 mid ，按之前的理论，target 值肯定大于 mid，反之小于
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
            // 左右指针重合时，找到 target
            if (left == right) {
                return left;
            }
        }
        return -1;
    }

    public int findDuplicate4(int[] nums) {
        // ***快慢指针***
        int slow = 0, fast = 0;
        // 0. 寻找环内相遇点
        // 将数值与下标看成是链表，因为有重复值存在的原因。一定可以成环
        do {
            // 所以，num[value] = 假设出的 key
            slow = nums[slow];
            // 嵌套就表示 快指针走两步
            fast = nums[nums[fast]];
        } while (slow != fast);
        // 1. slow 和 fast 相等，也就是相遇点
        // 2. 寻找环的入口点
        // 这里涉及数学理论，可能以后的快慢指针客户已考虑这种思想
        // 就是需要找到他们成环的起始点
        // 记住，第二次就是 before 为开头，after 为相遇点
        int before = 0, after = fast;
        while (before != after) {
            before = nums[before];
            after = nums[after];
        }
        // 循环结束，环的入口点就是重复元素
        return before;
    }
}
