package stacksandqueues;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MinQueue {

    private Queue<Integer> queue;
    private Deque<Integer> deque;

    public MinQueue() {
        queue = new LinkedList<>();
        deque = new LinkedList<>();
    }

    public void enqueue(int value) {
        queue.offer(value);
        while (!deque.isEmpty() && deque.peekLast() > value) {
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

    public int min() {
        return deque.peekFirst();
    }

    public static void main(String[] args) {
        MinQueue minQueue = new MinQueue();
        minQueue.enqueue(3);    // min = 3
        System.out.println(minQueue.min()); // 3
        minQueue.enqueue(1);    // min = 1
        System.out.println(minQueue.min()); // 1
        minQueue.enqueue(3);    // min = 1
        System.out.println(minQueue.min()); // 1
        minQueue.enqueue(2);    // min = 1
        System.out.println(minQueue.min()); // 1
        minQueue.enqueue(0);    // min = 0
        System.out.println(minQueue.min()); // 0
        minQueue.enqueue(1);    // min = 0
        System.out.println(minQueue.min()); // 0
        System.out.println("Dequeued : " + minQueue.dequeue()); // 3
        System.out.println(minQueue.min()); // min = 0
        System.out.println("Dequeued : " + minQueue.dequeue()); // 1
        System.out.println(minQueue.min()); // min = 0
        minQueue.enqueue(2);    // min = 0
        System.out.println(minQueue.min()); // 0
        minQueue.enqueue(-4);    // min = -4
        System.out.println(minQueue.min()); // -4
        System.out.println("Dequeued : " + minQueue.dequeue()); // 3
        System.out.println(minQueue.min()); // -4
    }
}
