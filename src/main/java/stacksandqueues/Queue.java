package stacksandqueues;

public class Queue {

    private int[] array;
    private int head, tail;

    public Queue(int capacity) {
        array = new int[capacity+1];
        head = tail = 0;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public void enqueue(int value) {
        if (isFull()) {
            throw new RuntimeException("Queue overflow");
        } else {
            array[tail] = value;
            tail = (tail + 1) % array.length;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        } else {
            int value = array[head];
            head = (head + 1) % array.length;
            return value;
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        } else {
           return array[head];
        }
    }

    private boolean isFull() {
        return (tail+1) % array.length == head;
    }

    public static void main(String[] args) {
        Queue queue = new Queue(12);
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
