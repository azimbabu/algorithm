package linkedlist.singly;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * https://leetcode.com/problems/linked-list-cycle-ii/description/
 */
public class ListCycle2 {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.getNext() == null) {
            return null;
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
            return null;
        } else {
            slow = head;
            while (slow != fast) {
                slow = slow.getNext();
                fast = fast.getNext();
            }

            return slow;
        }
    }
}
