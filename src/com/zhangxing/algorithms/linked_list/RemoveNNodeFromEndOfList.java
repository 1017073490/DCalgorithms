package com.zhangxing.algorithms.linked_list;

import java.util.Stack;

public class RemoveNNodeFromEndOfList {
    public static void main(String[] args) {

    }

    public ListNode removeNthFromEndByMyself(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1, head);
        ListNode former = sentinel;
        ListNode latter = sentinel;
        for (int i = 0; i < n + 1; i++) {
            former = former.next;
        }
        while (former != null) {
            former = former.next;
            latter = latter.next;
        }
        latter.next = latter.next.next;
        return sentinel.next;
    }

    public ListNode removeNthFromEndAdvancedDoublePointer(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1, head);
        // 左右指针始终保持固定距离
        ListNode first = sentinel;
        ListNode second = sentinel;
        // 倒数 N + 1 个节点
        for (int i = 0; i < n + 1; i++) {
            first = first.next;
        }
        // first、second 同时移动
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return sentinel.next;
    }

    public ListNode removeNthFromEndAdvanced(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1, head);
        ListNode cur = sentinel;
        // 定义一个栈
        Stack<ListNode> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 弹栈
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        stack.peek().next = stack.peek().next.next;
        return sentinel.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 遍历链表得到长度
        int len = getLength(head);
        // 从前至后遍历，找到正数 len - n + 1 个元素
        // 定义哨兵
        ListNode sentinel = new ListNode(-1, head);
        ListNode cur = sentinel;
        for (int i = 0; i < len - n; i++) {
            cur = cur.next;
        }
        // 找到第 len - n 个节点
        cur.next = cur.next.next;

        return sentinel.next;
    }

    public static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
}
