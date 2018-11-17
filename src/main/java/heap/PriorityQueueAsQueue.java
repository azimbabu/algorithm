package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class PriorityQueueAsQueue {

    private static class Element {
        private int key;
        private int value;

        public Element(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }
    }

    private PriorityQueue<Element> priorityQueue;
    private AtomicInteger counter;

    public PriorityQueueAsQueue() {
        priorityQueue = new PriorityQueue<>(Comparator.comparing(Element::getKey));
        counter = new AtomicInteger(Integer.MIN_VALUE);
    }

    public void enqueue(int value) {
        priorityQueue.offer(new Element(counter.getAndIncrement(), value));
    }

    public int dequeue() {
        if (priorityQueue.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        } else {
            return priorityQueue.poll().value;
        }
    }

    public int peek() {
        if (priorityQueue.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        } else {
            return priorityQueue.peek().value;
        }
    }

    public static void main(String[] args) {
        PriorityQueueAsQueue queue = new PriorityQueueAsQueue();
        queue.enqueue(4);
        queue.enqueue(1);
        queue.enqueue(3);
        System.out.println("Current peek : " + queue.peek());
        System.out.println("Dequeue : " + queue.dequeue());
        queue.enqueue(8);
        System.out.println("Current peek : " + queue.peek());
        System.out.println("Dequeue : " + queue.dequeue());
    }
}
