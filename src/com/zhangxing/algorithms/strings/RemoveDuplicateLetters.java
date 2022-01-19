package com.zhangxing.algorithms.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        String s1 = "bcabc";
        String s2 = "cbacdcbc";
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLettersAdvanced(s1));
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters(s2));
    }

    public String removeDuplicateLetters(String s) {
        // 时间复杂度：O(N)
        // 空间复杂度：O(N)
        // 递归的基准情形
        if (s.length() == 0) return "";
        // 希望找到最左侧位置，位置记为 position
        int position = 0;
        int[] countArray = new int[26];
        for (int i = 0; i < s.length(); i++) {
            // 计算出字母的次数
            countArray[s.charAt(i) - 'a']++;
        }
        // 遍历字符串，找到可以放在最左边的字母
        for (int i = 0; i < s.length(); i++) {
            // 把当前字符和 position 位置比较，如果更小就替换
            // 这里每次遍历都是尝试当前字符能否删，能删则他的 count 值 -1，
            // 且如果当前字符小于 position，则需要替换：因为第一轮比较的是自己，如果后面退出成立，那 OK 没问题。
            // 如果不成立，则说明这个字符可以删，所以遇到更小的要立即替换
            // 不能删，就推出了。position 停留的位置就是最后剩的初始字符
            if (s.charAt(i) < s.charAt(position)) {
                position = i;
            }
            // 每遇到一个字母，其 count--
            // 如果此时 count 值为 0，以当前字母作为最左端字符
            if (--countArray[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }
        // 递归调用
        return s.charAt(position) +
                removeDuplicateLetters(s.substring(position + 1).
                        replaceAll(s.charAt(position) + "", ""));
    }

    public String removeDuplicateLettersAdvanced(String s) {
        // 时间复杂度：O(N)
        // 空间复杂度：O(1)
        Stack<Character> resStack = new Stack<>();
        // 快速判断一个字符是否在结果中出现过，使用 HashSet 保存元素是否出现
        HashSet<Character> seenSet = new HashSet<>();
        // 为了快速判断一个字符是否在某个位置之后出现，用一个 HashMap 保存字母出现在字符串中出现的位置
        HashMap<Character, Integer> lastOccur = new HashMap<>();
        // 遍历字符串，将最后一个出现的位置保存进 lastOccur
        for (int i = 0; i < s.length(); i++) {
            // map 会覆盖重复出现的 key
            lastOccur.put(s.charAt(i), i);
        }
        // 遍历字符串，判断每个字符是否需要入栈
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            // 只有在 curChar 没有出现过的情况下，才判断是否入栈
            if (!seenSet.contains(curChar)) {
                // curChar 入栈操作之前，判断之前的栈顶元素是否在后面重复出现，如果是，需要弹出
                while (!resStack.isEmpty() && curChar < resStack.peek() && lastOccur.get(resStack.peek()) > i) {
                    // 此外，弹出之后，seenSet 就等于没有出现，需要删除
                    seenSet.remove(resStack.pop());
                }
                // 真正入栈操作
                resStack.push(curChar);
                seenSet.add(curChar);
            }
        }
        // 将 resStack 元素保存成字符串输出
        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : resStack) {
            stringBuilder.append(character.charValue());
        }
        return stringBuilder.toString();
    }
}
