package stacksandqueues;

import java.util.Stack;

public class MinStack2 {

    private Stack<Integer> data;
    private Stack<Integer> minData;

    public MinStack2() {
        data = new Stack<>();
        minData = new Stack<>();
    }

    public void push(int value) {
        data.push(value);
        if (minData.isEmpty() || minData.peek() >= value) {
            minData.push(value);
        }
    }

    public int pop() {
        int value = data.pop();
        if (minData.peek() == value) {
            minData.pop();
        }
        return value;
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return minData.peek();
    }

    public static void main(String[] args) {
        MinStack2 minStack = new MinStack2();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());  // -3
        minStack.pop();
        System.out.println(minStack.top());  // 0
        System.out.println(minStack.getMin());  // -2
    }
}
