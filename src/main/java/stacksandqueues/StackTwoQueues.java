package stacksandqueues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Puh O(n). Pop and Peek O(1)
 */
public class StackTwoQueues {

    private Queue<Integer> queue;

    public StackTwoQueues() {
        queue = new LinkedList<>();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void push(int value) {
        queue.offer(value);
        int size = queue.size();
        for (int i = 0; i < size-1; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        return queue.poll();
    }

    public int peek() {
        return queue.peek();
    }

    public static void main(String[] args) {
        StackTwoQueues stack = new StackTwoQueues();
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
