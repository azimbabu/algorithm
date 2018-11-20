package linkedlist;

public class QueueLinkedList {

    private SinglyLinkedList list;

    public QueueLinkedList() {
        list = new SinglyLinkedList();
    }

    public void enqueue(int value) {
        list.addLast(new SinglyListNode(value));
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        } else {
            return list.removeFirst();
        }
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        } else {
            return list.peekFirst();
        }
    }

    public static void main(String[] args) {
        QueueLinkedList queue = new QueueLinkedList();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);

        queue.enqueue(15);
        queue.enqueue(6);
        queue.enqueue(9);
        queue.enqueue(8);
        queue.enqueue(4);

        System.out.println("Dequeue first six elements");
        for (int i = 1; i <= 6; i++) {
            System.out.println("Dequeued : " + queue.dequeue());
        }

        queue.enqueue(17);
        queue.enqueue(3);
        queue.enqueue(5);

        System.out.println("Dequeued : " + queue.dequeue()); // 15

        System.out.println("New Head : " + queue.peek());   // 6
    }
}
