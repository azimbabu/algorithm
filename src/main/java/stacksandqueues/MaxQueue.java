package stacksandqueues;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MaxQueue {

    private Queue<Integer> queue;
    private Deque<Integer> deque;

    public MaxQueue() {
        queue = new LinkedList<>();
        deque = new LinkedList<>();
    }

    public void enqueue(int value) {
        queue.offer(value);
        while (!deque.isEmpty() && deque.peekLast() < value) {
            deque.removeLast();
        }
        deque.addLast(value);
    }

    public int dequeue() {
        int value = queue.poll();
        if (deque.peekFirst() == value) {
            deque.removeFirst();
        }
        return value;
    }

    public int peek() {
        return queue.peek();
    }

    public int max() {
        return deque.peekFirst();
    }

    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.enqueue(3);    // max = 3
        System.out.println(maxQueue.max()); // 3
        maxQueue.enqueue(1);    // max = 3
        System.out.println(maxQueue.max()); // 3
        maxQueue.enqueue(3);    // max = 3
        System.out.println(maxQueue.max()); // 3
        maxQueue.enqueue(2);    // max = 3
        System.out.println(maxQueue.max()); // 3
        maxQueue.enqueue(0);    // max = 3
        System.out.println(maxQueue.max()); // 3
        maxQueue.enqueue(1);    // max = 3
        System.out.println(maxQueue.max()); // 3
        System.out.println("Dequeued : " + maxQueue.dequeue());
        System.out.println(maxQueue.max()); // 3
        System.out.println("Dequeued : " + maxQueue.dequeue());
        System.out.println(maxQueue.max()); // 3
        maxQueue.enqueue(2);    // max = 3
        System.out.println(maxQueue.max()); // 3
        maxQueue.enqueue(4);    // max = 4
        System.out.println(maxQueue.max()); // 4
        System.out.println("Dequeued : " + maxQueue.dequeue());
        System.out.println(maxQueue.max()); // 4
    }
}
