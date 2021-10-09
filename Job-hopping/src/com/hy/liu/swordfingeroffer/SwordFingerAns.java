package com.hy.liu.swordfingeroffer;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SwordFingerAns {

    public int[] reversePrint(ListNode head) {

        LinkedList<Integer> list = new LinkedList<>();

        while (head != null) {

            list.push(head.val);

            head = head.next;
        }

        int[] ans = new int[list.size()];

        for (int i = 0; i < list.size(); i ++) {

            ans[i] = list.get(i);
        }

        return ans;
    }

    public ListNode reverseList(ListNode head) {

        ListNode pre = null, cur = head;

        while (cur != null) {

            ListNode next = cur.next;

            cur.next = pre;

            pre = cur;

            cur = next;
        }

        return pre;
    }

    Map<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {

        if (head == null) {

            return null;
        }

        if (!map.containsKey(head)) {

            Node newHead = new Node(head.val);

            map.put(head, newHead);

            newHead.next = copyRandomList(head.next);

            newHead.random = copyRandomList(head.random);
        }

        return map.get(head);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
