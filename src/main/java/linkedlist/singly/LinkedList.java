package linkedlist.singly;

public class LinkedList {

    private ListNode head, tail;

    public LinkedList() {
        head = tail = null;
    }

    public ListNode search(int key) {
        ListNode current = head;
        while (current != null && current.getKey() != key) {
            current = current.getNext();
        }
        return current;
    }

    public void addFirst(ListNode node) {
        node.setNext(head);
        head = node;
        if (tail == null) {
            tail = node;
        }
    }

    public void addLast(ListNode node) {
        if (tail != null) {
            tail.setNext(node);
        }

        tail = node;

        if (head == null) {
            head = node;
        }
    }

    public int removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        }

        int value = head.getKey();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        return value;
    }

    public int peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        } else {
            return head.getKey();
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void reverse() {
        ListNode prev = null, current = head;
        while (current != null) {
            ListNode next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        head = prev;
    }

    @Override
    public String toString() {
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

    public static void main(String[] args) {
        testReverse(new int[]{});
        testReverse(new int[]{1});
        testReverse(new int[]{1, 2});
        testReverse(new int[]{1, 2, 3});
        testReverse(new int[]{1, 2, 3, 4});
    }

    private static void testReverse(int[] array) {
        LinkedList list = new LinkedList();
        for (int value : array) {
            list.addLast(new ListNode(value));
        }
        System.out.println("List : " + list);
        list.reverse();
        System.out.println("List reversed : " + list);
    }
}
