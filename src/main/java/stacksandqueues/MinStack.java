package stacksandqueues;

import java.util.Stack;

public class MinStack {

    private Stack<int[]> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int value) {
        stack.push(new int[]{value, Math.min(value, !stack.isEmpty() ? stack.peek()[1] : Integer.MAX_VALUE)});
    }

    public int pop() {
        return stack.pop()[0];
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());  // -3
        minStack.pop();
        System.out.println(minStack.top());  // 0
        System.out.println(minStack.getMin());  // -2
    }
}
