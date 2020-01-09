package com.bigdata.algorithm;

/**
 * 单链表反转
 * <p>
 * 使用递归的方式进行反转，类似于压栈出栈，header压进去
 * 先压栈后出栈，把原来的尾变成新的头，断掉老指针，赋值新指针
 */
public class Reversal {
    //1-2-3-4
    public static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node temp = head.next;
        Node newHead = reverse(head.next);
        head.next = null;
        temp.next = head; //这块不容易理解，  temp是4，temp.next才是4的指针

        return newHead;
    }

    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
