package stacksandqueues;

public class Stack {

    private int[] array;
    private int top;

    public Stack(int capacity) {
        array = new int[capacity];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (top == array.length-1) {
            throw new RuntimeException("Stack overflow");
        } else {
            array[++top] = value;
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        } else {
            return array[top--];
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        } else {
            return array[top];
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack(7);
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
