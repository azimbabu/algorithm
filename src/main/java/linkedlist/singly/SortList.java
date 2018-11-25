package linkedlist.singly;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * https://leetcode.com/problems/sort-list/description/
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.getNext() == null) {
            return head;
        } else {
            ListNode middle = findMiddleAndSplit(head);
            head = sortList(head);
            middle = sortList(middle);
            return merge(head, middle);
        }
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode result = dummyHead, current1 = head1, current2 = head2;

        while (current1 != null || current2 != null) {
            if (current2 == null || (current1 != null && current1.getKey() <= current2.getKey())) {
                ListNode next = current1.getNext();
                current1.setNext(null);
                result.setNext(current1);
                result = current1;
                current1 = next;
            } else if (current1 == null || (current2 != null && current1.getKey() > current2.getKey())){
                ListNode next = current2.getNext();
                current2.setNext(null);
                result.setNext(current2);
                result = current2;
                current2 = next;
            }
        }

        return dummyHead.getNext();
    }

    private ListNode findMiddleAndSplit(ListNode head) {
        ListNode preSlow = null, slow = head, fast = head;

        while (fast != null) {
            preSlow = slow;
            slow = slow.getNext();
            fast = fast.getNext();
            if (fast != null) {
                fast = fast.getNext();
            }
        }

        preSlow.setNext(null);
        return slow;
    }

    public static void main(String[] args) {
        ListNode list = build(new int[] {4, 3, 2, 1});
        System.out.println("Unsorted : " + listToString(list));
        list = new SortList().sortList(list);
        System.out.println("Sorted : " + listToString(list));
    }

    private static ListNode build(int[] array) {
        ListNode dummyHead = new ListNode(0);
        ListNode result = dummyHead;
        for (int value : array) {
            result.setNext(new ListNode(value));
            result = result.getNext();
        }
        return dummyHead.getNext();
    }

    private static String listToString(ListNode head) {
        StringBuffer result = new StringBuffer();
        ListNode current = head;
        while (current != null) {
            result.append(current.getKey());
            if (current.getNext() != null) {
                result.append("->");
            }
            current = current.getNext();
        }
        return result.toString();
    }
}
