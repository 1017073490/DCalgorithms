package com.zhangxing.algorithms.sliding_windows;

import java.util.HashMap;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        String s = "aa";
        String t = "aa";
        System.out.println(new MinimumWindowSubstring().minWindowSlidingWindowsAdvancedSuper(s, t));
    }

    public String minWindowSlidingWindowsAdvancedSuper(String s, String t) {
        // 时间：O(N)
        // 滑动窗口：枚举 s 中 可能覆盖 t 的子串
        String resString = "";
        // 定义一个 HashMap 保存频次
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        // 统计 t 中的字符
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = frequencyMap.getOrDefault(c, 0);
            frequencyMap.put(c, count + 1);
        }
        // 定义左右指针指向滑动窗口的起始、结束位置（左闭右开）
        int start = 0, end = 1;
        // 统计子串每个字符出现的频次
        HashMap<Character, Integer> subStringFrequencyMap = new HashMap<>();
        // 定义一个"贡献值"变量，统计 t 中的字符在子串中出现了多少
        int targetCharCount = 0;

        while (end <= s.length()) {
            // end 增加之后，新增的字符
            char newChar = s.charAt(end - 1);
            // 新增字符频次加一
            if (frequencyMap.containsKey(newChar)) {
                // t 串中有该字符才判断
                subStringFrequencyMap.put(newChar, subStringFrequencyMap.getOrDefault(newChar, 0) + 1);
                // 频次加一，还要看是否有贡献，如果子串中频次小于 t 中频次，当前字符就有贡献
                if (subStringFrequencyMap.get(newChar) <= frequencyMap.get(newChar)) {
                    targetCharCount++;
                }
            }
            // 如果当前子串符合覆盖子串要求，即贡献值达到了 t 子串的数值。并且比之前的最小子串要短，就替换
            while (targetCharCount == t.length() && start < end) {
                if ((resString.equals("") || (end - start) < resString.length())) {
                    resString = s.substring(start, end);
                }
                // 对要删除的字符频次减一
                char removeChar = s.charAt(start);
                if (frequencyMap.containsKey(removeChar)) {
                    // t 串中有该字符才判断
                    subStringFrequencyMap.put(removeChar, subStringFrequencyMap.getOrDefault(removeChar, 0) - 1);
                    // 如果子串中的频次不够 t 重的频次，此时相当于减少了一个真正有贡献的 char，贡献值减一
                    if (subStringFrequencyMap.get(removeChar) < frequencyMap.get(removeChar)) {
                        targetCharCount--;
                    }
                }
                // 不管是不是最小子串，左指针都需要移动，为了缩小窗口，寻找局部最优解
                start++;
            }
            // 不是覆盖子串，就要扩大窗口，寻找新的子串
            end++;
        }
        return resString;
    }

    public String minWindowSlidingWindowsAdvanced(String s, String t) {
        // 时间：O(N)
        // 滑动窗口：枚举 s 中 可能覆盖 t 的子串
        String resString = "";
        // 定义一个 HashMap 保存频次
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        // 统计 t 中的字符
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = frequencyMap.getOrDefault(c, 0);
            frequencyMap.put(c, count + 1);
        }
        // 定义左右指针指向滑动窗口的起始、结束位置（左闭右开）
        int start = 0, end = 1;
        // 统计子串每个字符出现的频次
        HashMap<Character, Integer> subStringFrequencyMap = new HashMap<>();

        while (end <= s.length()) {
            // end 增加之后，新增的字符
            char newChar = s.charAt(end - 1);
            // 新增字符频次加一
            if (frequencyMap.containsKey(newChar)) {
                // t 串中有该字符才判断
                subStringFrequencyMap.put(newChar, subStringFrequencyMap.getOrDefault(newChar, 0) + 1);
            }
            // 如果当前子串符合覆盖子串要求，并且比之前的最小子串要短，就替换
            while (check(frequencyMap, subStringFrequencyMap) && start < end) {
                if ((resString.equals("") || (end - start) < resString.length())) {
                    resString = s.substring(start, end);
                }
                // 对要删除的字符频次减一
                char removeChar = s.charAt(start);
                if (frequencyMap.containsKey(removeChar)) {
                    // t 串中有该字符才判断
                    subStringFrequencyMap.put(removeChar, subStringFrequencyMap.getOrDefault(removeChar, 0) - 1);
                }
                // 不管是不是最小子串，左指针都需要移动，为了缩小窗口，寻找局部最优解
                start++;
            }
            // 不是覆盖子串，就要扩大窗口，寻找新的子串
            end++;
        }
        return resString;
    }


    public String minWindowSlidingWindows(String s, String t) {
        // 时间：N * N
        // 滑动窗口：枚举 s 中 可能覆盖 t 的子串
        String resString = "";
        // 定义一个 HashMap 保存频次
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        // 统计 t 中的字符
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = frequencyMap.getOrDefault(c, 0);
            frequencyMap.put(c, count + 1);
        }
        // 定义左右指针指向滑动窗口的起始、结束位置（左闭右开）
        int start = 0, end = t.length();
        while (end <= s.length()) {
            // 统计子串每个字符出现的频次
            HashMap<Character, Integer> subStringFrequencyMap = new HashMap<>();
            // 统计子串中的字符频次
            for (int k = start; k < end; k++) {
                char c = s.charAt(k);
                int count = subStringFrequencyMap.getOrDefault(c, 0);
                subStringFrequencyMap.put(c, count + 1);
            }
            if (check(frequencyMap, subStringFrequencyMap)) {
                if ((resString.equals("") || (end - start) < resString.length())) {
                    resString = s.substring(start, end);
                }
                // 不管是不是最小子串，左指针都需要移动，为了缩小窗口，寻找局部最优解
                start++;
            } else {
                // 不是覆盖子串，就要扩大窗口，寻找新的子串
                end++;
            }
        }
        return resString;
    }

    public String minWindow(String s, String t) {
        // 时间：O(N * N * N)
        // 暴力法：枚举 s 中 可能覆盖 t 的子串
        String resString = "";
        // 定义一个 HashMap 保存频次
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        // 统计 t 中的字符
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = frequencyMap.getOrDefault(c, 0);
            frequencyMap.put(c, count + 1);
        }
        // 在 s 中搜索覆盖子串
        // 遍历所有字符，作为当前子串的起始位置
        for (int i = 0; i < s.length(); i++) {
            // 遍历结束位置，i 之后不小于 t 长度的位置，作为子串的结束位置
            for (int j = i + t.length(); j <= s.length(); j++) {
                // 统计子串每个字符出现的频次
                HashMap<Character, Integer> subStringFrequencyMap = new HashMap<>();
                // 统计子串中的字符频次
                for (int k = i; k < j; k++) {
                    char c = s.charAt(k);
                    int count = frequencyMap.getOrDefault(c, 0);
                    subStringFrequencyMap.put(c, count + 1);
                }
                // 如果当前的子串符合覆盖要求，且比之前的最小子串要短，就替换
                if (check(frequencyMap, subStringFrequencyMap) && (resString.equals("") || (j - i) < resString.length())) {
                    resString = s.substring(i, j);
                }
            }
        }
        return resString;
    }

    // 用于检查当前子串是否是一个覆盖 t 的子串
    private boolean check(HashMap<Character, Integer> tFreq, HashMap<Character, Integer> subFreq) {
        // 遍历 t 中每个字符的频次，与 subSting 进行比较
        // 查看 t 中出现的每个字符在 subFreq 中出现的次数，一旦有不相等的情况出现（也不可能大于），就返回 false
        for (Character character : tFreq.keySet()) {
            if (subFreq.getOrDefault(character, 0) < tFreq.get(character)) {
                return false;
            }
        }
        return true;
    }
}
