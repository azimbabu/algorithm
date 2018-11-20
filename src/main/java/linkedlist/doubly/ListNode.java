package linkedlist.doubly;

public class ListNode {
    private int key;
    private ListNode prev;
    private ListNode next;

    public ListNode(int key) {
        this.key = key;
    }

    public ListNode(int key, ListNode prev, ListNode next) {
        this.key = key;
        this.prev = prev;
        this.next = next;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public ListNode getPrev() {
        return prev;
    }

    public void setPrev(ListNode prev) {
        this.prev = prev;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
