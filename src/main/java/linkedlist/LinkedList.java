package linkedlist;

public class LinkedList {

    private ListNode head;

    public LinkedList() {
        this.head = null;
    }

    public ListNode search(int key) {
        ListNode current = head;
        while (current != null && current.getKey() != key) {
            current = current.getNext();
        }
        return current;
    }

    public void insert(ListNode node) {
        node.setNext(head);
        if (head != null) {
            head.setPrev(node);
        }
        head = node;
        node.setPrev(null);
    }

    public void delete(ListNode node) {
        if (node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        } else {
            head = node.getNext();
        }

        if (node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        }
    }

    public boolean isEmpty() {
        return head == null;
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
        LinkedList list = new LinkedList();
        ListNode node1 = new ListNode(1);
        list.insert(node1);

        ListNode node4 = new ListNode(4);
        list.insert(node4);

        ListNode node16 = new ListNode(16);
        list.insert(node16);

        ListNode node9 = new ListNode(9);
        list.insert(node9);

        System.out.println("List : " + list);

        ListNode node25 = new ListNode(25);
        list.insert(node25);

        System.out.println("List : " + list);

        list.delete(node4);
        System.out.println("List : " + list);
    }
}
