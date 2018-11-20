package linkedlist;

public class SinglyListNode {

    private int key;
    private SinglyListNode next;

    public SinglyListNode(int key) {
        this.key = key;
    }

    public SinglyListNode(int key, SinglyListNode next) {
        this.key = key;
        this.next = next;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public SinglyListNode getNext() {
        return next;
    }

    public void setNext(SinglyListNode next) {
        this.next = next;
    }
}
