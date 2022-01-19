package com.zhangxing.algorithms.strings;

public class AddStrings {
    public static void main(String[] args) {
        String s1 = new String("123456");
        String s2 = new String("87654321");
        System.out.println(new AddStrings().addStrings(s1,s2));
    }

    public String addStrings(String num1, String num2) {
        // 思路：每一位分别叠加，结果放入字符串，最后倒叙。需要来考虑进位的问题
        StringBuffer res = new StringBuffer();
        // 定义遍历 2 个字符串的初始位置
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        // 定义进位变量
        int carry = 0;
        // 统一进位情况：只要未遍历完或者还有进位，就补零继续
        while (i >= 0 || j >= 0 || carry != 0) {
            // 取两数当前的对应数位，但是取出来可能不是数字，也就是没有数字了，要补零站位
            // 字符要将 ASCII 码转为数字
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            // 对当前数位求和
            int sum = n1 + n2 + carry;
            res.append(sum % 10);
            carry = sum / 10;
            // 移动指针，遍历下一位
            i--;
            j--;
        }
        return res.reverse().toString();
    }
}
