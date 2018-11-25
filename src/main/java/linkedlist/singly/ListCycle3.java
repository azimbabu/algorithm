package linkedlist.singly;

import util.list.singly.ListUtils;

import java.util.Arrays;

/**
 * Write a function cycleLength() that checks whether a given Linked List contains loop and
 * if loop is present then returns count of nodes in loop.
 * https://www.geeksforgeeks.org/find-length-of-loop-in-linked-list/
 */
public class ListCycle3 {

    public int cycleLength(ListNode head) {
        if (head == null || head.getNext() == null) {
            return 0;
        }

        ListNode slow = head, fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) {
                break;
            }
        }

        if (slow != fast) {
            return 0;
        } else {
            int length = 0;
            do {
                length++;
                slow = slow.getNext();
            } while (slow != fast);
            return length;
        }
    }

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);

        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(two);

        ListCycle3 listCycle = new ListCycle3();
        System.out.println(listCycle.cycleLength(one)); // 4

        five.setNext(one);
        System.out.println(listCycle.cycleLength(one)); // 5

        five.setNext(three);
        System.out.println(listCycle.cycleLength(one)); // 3

        five.setNext(four);
        System.out.println(listCycle.cycleLength(one)); // 2

        five.setNext(five);
        System.out.println(listCycle.cycleLength(one)); // 1

        five.setNext(null);
        System.out.println(listCycle.cycleLength(one)); // 0
    }
}
