package com.zhangxing.algorithms.sliding_windows;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInString {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> res = new FindAllAnagramsInString().findAnagramsAdvanced(s, p);
        for (Integer re : res) {
            System.out.println(re);
        }
        System.out.println();
    }

    public List<Integer> findAnagramsAdvanced(String s, String p) {
        // 滑动窗口
        // 时间：O(2 * N)  空间：O(1)
        // 定义一个结果列表
        ArrayList<Integer> resList = new ArrayList<>();
        // 统计目标 p 字符的频次
        int[] targetArrayCounts = new int[26];
        for (int i = 0; i < p.length(); i++) {
            targetArrayCounts[p.charAt(i) - 'a']++;
        }
        // 定义一个数组，统计子串中所有字符频次
        int[] subStringCounts = new int[26];
        // 定义左右指针，右不包含
        int start = 0, end = 1;
        // 移动指针，总是截取字符出现频次全部小于等于 p 中字符频次的子串
        while (end <= s.length()) {
            char newChar = s.charAt(end - 1);
            subStringCounts[newChar - 'a']++;
            // 判断当前子串是否符合要求
            // 如果新增字符导致子串中频次超过了 p 中的频次，那么移动 start，消除新增字符的影响
            while (subStringCounts[newChar - 'a'] > targetArrayCounts[newChar - 'a'] && start < end) {
                // 在 start 右移同时，伴随着这个字符频次减少的操作
                char removeChar = s.charAt(start);
                subStringCounts[removeChar - 'a']--;
                start++;
            }
            // 如果当前子串长度等于 p 子串的长度，那么就是一个字母异位词
            if (end - start == p.length()) {
                resList.add(start);
            }
            end++;
        }
        return resList;
    }

    public List<Integer> findAnagrams(String s, String p) {
        // 暴力法
        // 时间：O(N)  空间：O(1)
        // 定义一个结果列表
        ArrayList<Integer> resList = new ArrayList<>();
        // 统计目标 p 字符的频次
        int[] targetArrayCounts = new int[26];
        for (int i = 0; i < p.length(); i++) {
            targetArrayCounts[p.charAt(i) - 'a']++;
        }
        // 遍历 s，以每一个字符作为起始，考察长度为 p.length 的子串
        for (int i = 0; i < s.length() - p.length() + 1; i++) {
            // 判断当前子串是否为 p 的字母异位次
            boolean isMatched = true;
            // 定义一个数组，统计子串中所有字符频次
            int[] subStringCounts = new int[26];
            for (int j = i; j < i + p.length(); j++) {
                // subStringCounts 中的频次首先加一（如果有多个重复字符，这里就可以继续判断，因为是大于才推出）
                // 然后直接判断，字符频次超过 p 中的字符频次，直接不符合
                if (++subStringCounts[s.charAt(j) - 'a'] > targetArrayCounts[s.charAt(j) - 'a']) {
                    isMatched = false;
                    break;
                }
            }
            if (isMatched == true) {
                resList.add(i);
            }
        }
        return resList;
    }
}
