package stacksandqueues;

/**
 * implement two stacks in one array A[1..n] in such a way that neither stack overflows unless the total number of
 * elements in both stacks together is n.
 * The PUSH and POP operations should run in O(1) time.
 */
public class TwoStacks {

    private int[] array;
    private int top1, top2;

    public TwoStacks(int capacity) {
        array = new int[capacity];
        top1 = -1;
        top2 = capacity;
    }

    public boolean isEmpty(int stackId) {
        checkStackId(stackId);
        return stackId == 1 ? top1 == -1 : top2 == array.length;
    }

    public void push(int stackId, int value) {
        if (top1 + 1 == top2) {
            throw new RuntimeException("Stack overflow");
        }

        if (stackId == 1) {
            array[++top1] = value;
        } else {
            array[--top2] = value;
        }
    }

    public int pop(int stackId) {
        if (isEmpty(stackId)) {
            throw new RuntimeException("Stack underflow");
        }

        return stackId == 1 ? array[top1--] : array[top2++];
    }

    public int peek(int stackId) {
        if (isEmpty(stackId)) {
            throw new RuntimeException("Stack underflow");
        }

        return stackId == 1 ? array[top1] : array[top2];
    }

    private void checkStackId(int stackId) {
        if (stackId != 1 && stackId != 2) {
            throw new IllegalArgumentException("Invalid stack id" + stackId);
        }
    }
}
