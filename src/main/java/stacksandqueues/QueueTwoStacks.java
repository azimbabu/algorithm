package stacksandqueues;

import java.util.Stack;

public class QueueTwoStacks {

    private Stack<Integer> inbox;
    private Stack<Integer> outbox;

    public QueueTwoStacks() {
        inbox = new Stack<>();
        outbox = new Stack<>();
    }

    public boolean isEmpty() {
        return inbox.isEmpty() && outbox.isEmpty();
    }

    public void enqueue(int value) {
        inbox.push(value);
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }

        if (outbox.isEmpty()) {
            shiftElements();
        }
        return outbox.pop();
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }

        if (outbox.isEmpty()) {
            shiftElements();
        }
        return outbox.peek();
    }

    private void shiftElements() {
        while (!inbox.isEmpty()) {
            outbox.push(inbox.pop());
        }
    }

    public static void main(String[] args) {
        QueueTwoStacks queue = new QueueTwoStacks();
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
