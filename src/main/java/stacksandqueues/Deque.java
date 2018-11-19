package stacksandqueues;

/**
 * Whereas a stack allows insertion and deletion of elements at only one end, and a queue allows insertion at one end
 * and deletion at the other end, a deque (double ended queue) allows insertion and deletion at both ends.
 * Write four O(1)-time procedures to insert elements into and delete elements from both ends of a deque
 * implemented by an array.
 */
public class Deque {

    private int[] array;
    private int head, tail;

    public Deque(int capacity) {
        array = new int[capacity+1];
        head = tail = 0;
    }

    public void addFirst(int value) {
        if (isFull()) {
            throw new RuntimeException("Deque overflow");
        } else {
            head--;
            if (head < 0) {
                head = array.length-1;
            }
            array[head] = value;
        }
    }

    public void addLast(int value) {
        if (isFull()) {
            throw new RuntimeException("Deque overflow");
        } else {
            array[tail] = value;
            tail = (tail + 1) % array.length;
        }
    }

    public int removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Deque underflow");
        } else {
            int value = array[head];
            head = (head + 1) % array.length;
            return value;
        }
    }

    public int removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("Deque underflow");
        } else {
            tail--;
            if (tail < 0) {
                tail = array.length-1;
            }
            return array[tail];
        }
    }

    public int peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Deque underflow");
        } else {
            return array[head];
        }
    }

    public int peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("Deque underflow");
        } else {
            return array[tail-1];
        }
    }

    public boolean isEmpty() {
        return head == tail;
    }

    private boolean isFull() {
        return (tail+1) % array.length == head;
    }
}
