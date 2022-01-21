package com.zhangxing.algorithms.hash;

import java.util.HashMap;

public class LRUByHand {
    // 首先定义一个双向链表的节点类
    class Node {
        int key;
        int val;
        Node next;
        Node prev;

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // 定义 HashMap
    private HashMap<Integer, Node> hashMap = new HashMap<Integer, Node>();
    // 定义基本属性
    private int capacity;
    // size 是已经有的、占用的大小，<= capacity
    private int size;
    // 定义头尾指针
    private Node head, tail;

    public LRUByHand(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        // 定义哨兵，方便统一处理
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        // 从 Hash 表中查找 key，不存在返回 -1
        Node node = hashMap.get(key);
        if (node == null) return -1;
        // 存在需要进行操作：将当前节点移到链表末尾
        moveToTail(node);
        return node.val;
    }

    private void moveToTail(Node node) {
        // 移动节点到末尾
        removeNode(node);
        addToTail(node);
    }

    private void addToTail(Node node) {
        // 在链表末尾增加一个节点
        node.next = tail;
        // 以原先的末尾节点作为前一个节点
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }

    private void removeNode(Node node) {
        // 删除某一个节点：跳过该节点
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    public void put(int key, int val) {
        // 从 Hash 表中查找 key，不存在返回 -1
        Node node = hashMap.get(key);
        if (node == null) {
            // 创建新的节点，插入末尾
            Node newNode = new Node(key, val);
            // 1. 保存进 Hash 表
            hashMap.put(key, newNode);
            // 2. 放到双向链表末尾
            addToTail(newNode);
            size++;
            // 3. 如果超出容量限制，删除链表头结点
            if (size > capacity) {
                Node head = removeHead();
                hashMap.remove(head.key);
                size--;
            }
        } else {
            // 存在，修改 val，并移到末尾
            node.val = val;
            moveToTail(node);
        }
    }

    private Node removeHead() {
        Node realHead = head.next;
        removeNode(realHead);
        return realHead;
    }
}
