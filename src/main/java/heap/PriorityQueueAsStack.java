package heap;

import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.PriorityQueue;

public class PriorityQueueAsStack {

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

    public PriorityQueueAsStack() {
        priorityQueue = new PriorityQueue<>(Comparator.comparing(Element::getKey, Comparator.reverseOrder()));
    }

    public void push(int value) {
        priorityQueue.offer(new Element(priorityQueue.isEmpty() ? Integer.MIN_VALUE : priorityQueue.peek().key + 1, value));
    }

    public int pop() {
        if (priorityQueue.isEmpty()) {
            throw new EmptyStackException();
        } else {
            return priorityQueue.poll().value;
        }
    }

    public int peek() {
        if (priorityQueue.isEmpty()) {
            throw new EmptyStackException();
        } else {
            return priorityQueue.peek().value;
        }
    }

    public static void main(String[] args) {
        PriorityQueueAsStack stack = new PriorityQueueAsStack();
        stack.push(4);
        stack.push(1);
        stack.push(3);
        System.out.println("Current top : " + stack.peek());
        System.out.println("Pop : " + stack.pop());
        stack.push(8);
        System.out.println("Current top : " + stack.peek());
        System.out.println("Pop : " + stack.pop());
    }
}
