package linkedlist;

public class StackLinkedList {

    private SinglyLinkedList list;

    public StackLinkedList() {
        list = new SinglyLinkedList();
    }

    public void push(int value) {
        list.addFirst(new SinglyListNode(value));
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        } else {
            int value = list.removeFirst();
            return value;
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        } else {
            return list.peekFirst();
        }
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {
        StackLinkedList stack = new StackLinkedList();
        stack.push(15);
        stack.push(6);
        stack.push(2);
        stack.push(9);
        System.out.println("Current top : " + stack.peek());    // 9
        stack.push(17);
        stack.push(3);
        System.out.println("Current top : " + stack.peek());    // 3
        System.out.println("Pop : " + stack.pop()); // 3
        System.out.println("Current top : " + stack.peek());    // 17
    }
}
