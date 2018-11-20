package linkedlist;

public class LinkedListWithSentinel {
    private ListNode sentinelNode;

    public LinkedListWithSentinel() {
        sentinelNode = new ListNode(Integer.MIN_VALUE);
        sentinelNode.setNext(sentinelNode);
        sentinelNode.setPrev(sentinelNode);
    }

    public void delete(ListNode node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
    }

    public void insert(ListNode node) {
        node.setNext(sentinelNode.getNext());
        sentinelNode.getNext().setPrev(node);
        sentinelNode.setNext(node);
        node.setPrev(sentinelNode);
    }

    public ListNode search(int key) {
        ListNode current = sentinelNode.getNext();
        while (current != sentinelNode && current.getKey() != key) {
            current = current.getNext();
        }
        return current;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        ListNode current = sentinelNode.getNext();
        while (current != sentinelNode) {
            sb.append(current.getKey());
            if (current.getNext() != null) {
                sb.append("->");
            }
            current = current.getNext();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedListWithSentinel list = new LinkedListWithSentinel();
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

        list.delete(node1);
        System.out.println("List : " + list);
    }
}
