package com.zhangxing.algorithms.linked_list;

public class MergeTwoSortedLists {
    public ListNode mergeTwoListsMy(ListNode list1, ListNode list2) {
        ListNode sentinel = new ListNode(-1);
        ListNode curNode = sentinel;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curNode.next = list1;
                curNode = list1;
                list1 = list1.next;
            } else {
                curNode.next = list2;
                curNode = list2;
                list2 = list2.next;
            }
        }
        curNode.next = (list1==null)?list2:list1;
        return sentinel.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 迭代法
        // 首先，定义一个哨兵节点
        ListNode sentinel = new ListNode(-1);
        // 定义一个 prevNode
        // 保存当前结果链表里的最后一个节点，作为判断比较的 "前一个节点"
        ListNode prevNode = sentinel;
        // 迭代遍历 2 个链表，直到至少有一个为 null
        while (list1 != null && list2 != null) {
            // 比较当前 2 个链表的头结点，较小的那个在结果链表末尾
            if (list1.val <= list2.val) {
                prevNode.next = list1;
                prevNode = list1;
                list1 = list1.next;
            } else {
                prevNode.next = list2;
                prevNode = list2;
                list2 = list2.next;
            }
        }
        // 可能还有一个链表没有遍历完成
        prevNode.next = (list1 == null) ? list2 : list1;
        return sentinel.next;
    }

    public ListNode mergeTwoListsDG(ListNode list1, ListNode list2) {
        // 基准
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        //
        if (list1.val <= list2.val) {
            // 直接提取出来，剩下的递归合并
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
