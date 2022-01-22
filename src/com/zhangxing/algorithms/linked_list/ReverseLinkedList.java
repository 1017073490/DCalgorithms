package com.zhangxing.algorithms.linked_list;

public class ReverseLinkedList {
    public static void main(String[] args) {

    }

    public ListNode reverseListByMy(ListNode head) {
        ListNode curNode = head;
        ListNode prevNode = null;
        while (curNode != null) {
            ListNode next = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = next;
        }
        return prevNode;
    }

    public ListNode reverseList(ListNode head) {
        // 定义两个指针，指向当前访问的节点、上一个节点
        ListNode curNode = head;
        ListNode prevNode = null;
        // 依次迭代链表中的节点，将 next 指向 prev
        while (curNode != null) {
            // 先将 curNode 的 next 保存下来，这样就可以保存迭代下去
            ListNode tempNode = curNode.next;
            // 当前的节点的 next 指向前一个节点，那么就完成了当前节点的反转
            curNode.next = prevNode;
            // 然后 prev 和 cur 分别前移一个
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
