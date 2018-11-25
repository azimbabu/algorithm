package stacksandqueues;

import java.util.Stack;

public class MaxStack {

    private Stack<Integer> data;
    private Stack<Integer> maxData;

    public MaxStack() {
        data = new Stack<>();
        maxData = new Stack<>();
    }

    public void push(int value) {
        data.push(value);
        if (maxData.isEmpty() || maxData.peek() <= value) {
            maxData.push(value);
        }
    }

    public int pop() {
        int value = data.pop();
        if (maxData.peek() == value) {
            maxData.pop();
        }
        return value;
    }

    public int peek() {
        return data.peek();
    }

    public int peekMax() {
        return maxData.peek();
    }

    public int popMax() {
        int value = maxData.pop();

        Stack<Integer> tempData = new Stack<>();
        while (!data.isEmpty() && data.peek() != value) {
            tempData.push(data.pop());
        }

        data.pop(); // remove value

        while (!tempData.isEmpty()) {
            push(tempData.pop());
        }

        return value;
    }

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(5);
        maxStack.push(1);
        System.out.println(maxStack.popMax());
        System.out.println(maxStack.peekMax());
    }
}
