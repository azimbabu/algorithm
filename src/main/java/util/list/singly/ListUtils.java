package util.list.singly;

import linkedlist.singly.ListNode;

import java.util.List;

public final class ListUtils {

    private ListUtils() {
    }

    public static ListNode buildList(List<Integer> list) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        for (int value : list) {
            ListNode node = new ListNode(value);
            current.setNext(node);
            current = current.getNext();
        }
        return dummyHead.getNext();
    }

    public static String toString(ListNode head) {
        StringBuffer sb = new StringBuffer();
        ListNode current = head;
        while (current != null) {
            sb.append(current.getKey());
            if (current.getNext() != null) {
                sb.append("->");
            }
            current = current.getNext();
        }
        return sb.toString();
    }
}
