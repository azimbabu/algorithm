package linkedlist;

public class SinglyLinkedList {

    private SinglyListNode head, tail;

    public SinglyLinkedList() {
        head = tail = null;
    }

    public SinglyListNode search(int key) {
        SinglyListNode current = head;
        while (current != null && current.getKey() != key) {
            current = current.getNext();
        }
        return current;
    }

    public void addFirst(SinglyListNode node) {
        node.setNext(head);
        head = node;
        if (tail == null) {
            tail = node;
        }
    }

    public void addLast(SinglyListNode node) {
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
        SinglyListNode prev = null, current = head;
        while (current != null) {
            SinglyListNode next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        head = prev;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        SinglyListNode current = head;
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
        SinglyLinkedList list = new SinglyLinkedList();
        for (int value : array) {
            list.addLast(new SinglyListNode(value));
        }
        System.out.println("List : " + list);
        list.reverse();
        System.out.println("List reversed : " + list);
    }
}
