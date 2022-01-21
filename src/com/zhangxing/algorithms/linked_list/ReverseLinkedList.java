package com.zhangxing.algorithms.linked_list;

public class ReverseLinkedList {
    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        // 定义两个指针，指向当前访问的节点、上一个节点
        ListNode curNode = head;
        ListNode prevNode = null;
        // 依次迭代链表中的节点，将 next 指向 prev
        while (curNode != null) {
            ListNode tempNode = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = tempNode;
        }
        // prev 指向的就是末尾的节点
        return prevNode;
    }

    public ListNode reverseListDG(ListNode head) {
        // 基准情况
        if (head == null || head.next == null) {
            return head;
        }
        // 一般情形
        // 递归调用，翻转剩余所有节点
        ListNode restHead = head.next;
        // 相当于这句话完成了翻转当前 head 的剩余所有
        ListNode reversedRest = reverseList(restHead);
        // 当前节点接在链表末尾
        restHead.next = head;
        // 防止循环指向，要断开
        head.next = null;
        return reversedRest;
    }
}
