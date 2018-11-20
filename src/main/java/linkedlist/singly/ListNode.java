package linkedlist.singly;

public class ListNode {

    private int key;
    private ListNode next;

    public ListNode(int key) {
        this.key = key;
    }

    public ListNode(int key, ListNode next) {
        this.key = key;
        this.next = next;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
