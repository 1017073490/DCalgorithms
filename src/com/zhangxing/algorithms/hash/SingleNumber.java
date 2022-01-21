package com.zhangxing.algorithms.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SingleNumber {
    public static void main(String[] args) {

    }

    public int singleNumberAdvancedWithMath(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum ^=num;
        }
        return sum;
    }

    public int singleNumberAdvancedWithSet(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int arraySum = 0;
        int setSum = 0;
        for (int num : nums) {
            set.add(num);
            arraySum += num;
        }
        for (Integer integer : set) {
            setSum += integer;
        }
        return setSum * 2 - arraySum;
    }

    public int singleNumberAdvancedWithMap(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            if (map.get(num) != null) {
                map.remove(num);
            } else {
                map.put(num, 1);
            }
        }
        return map.keySet().iterator().next();
    }

    public int singleNumber(int[] nums) {
        // 暴力法
        ArrayList<Integer> list = new ArrayList();
        // 遍历，int 就不可以，Java 的细节
        for (Integer num : nums) {
            if (list.contains(num)) {
                list.remove(num);
            } else {
                list.add(num);
            }
        }
        return list.get(0);
    }
}
