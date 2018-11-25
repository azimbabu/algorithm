package linkedlist.singly;

/**
 * Given a linked list, determine if it has a cycle in it.
 * https://leetcode.com/problems/linked-list-cycle/description/
 */
public class ListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.getNext() == null) {
            return false;
        }

        ListNode slow = head, fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
