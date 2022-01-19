package com.zhangxing.algorithms.strings;

public class MultiplyStrings {
    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        System.out.println(new MultiplyStrings().multiplyAdvanced(num1, num2));
    }

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        // 定义输出结果，直接定义成 string，调用字符串相加
        String res = "0";
        // 从个位开始，遍历 num2 的每一位，跟 num1 相乘
        for (int i = num2.length() - 1; i >= 0; i--) {
            // 取出 num2 的当前数位，作为当前乘法的第二个乘数
            int n2 = num2.charAt(i) - '0';
            StringBuilder curResult = new StringBuilder();
            int carry = 0;
            // 因为结果是倒序，所以当前 n2 对应数位要补零，
            // 应该先写入 curResult，补充 n - 1 - i 个零
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                curResult.append("0");
            }
            // 遍历 num1 中的每一位
            for (int j = num1.length() - 1; j >= 0; j--) {
                // 提取 num1 的当前数位，作为当前乘法的第一个乘数
                int n1 = num1.charAt(j) - '0';
                // 计算当前结果
                int product = n1 * n2 + carry;
                // 保存
                curResult.append(product % 10);
                carry = product / 10;
            }
            // 所有位数乘法计算完毕，如果有进位，需要将进位单独作为一位保存下来
            if (carry != 0) {
                curResult.append(carry);
            }
            // 此时得到的就是 num1 和 num2 中当前位 n2 的最终乘积
            // 将当前乘积叠加
            res = new AddStrings().addStrings(res, curResult.reverse().toString());
        }
        return res;
    }

    public String multiplyAdvanced(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int[] resArray = new int[num1.length() + num2.length()];
        // 遍历每一位
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                // 计算乘积
                int product = n1 * n2;
                // 保存至结果数组
                int sum = product + resArray[i + j + 1];
                // 重新更新，叠加结果的个位保存至数组的 i + j + 1 的位置
                resArray[i + j + 1] = sum % 10;
                // 个位保存至数组的 i + j 的位置
                resArray[i + j] += sum / 10;
            }
        }
        // 将结果数组转为 string
        StringBuilder builder = new StringBuilder();
        // 收尾是否为 0，是的话就跳过，不是就遍历
        int start = resArray[0] == 0 ? 1 : 0;
        for (int i = start; i < resArray.length; i++) {
            builder.append(resArray[i]);
        }
        return builder.toString();
    }
}
