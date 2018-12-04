package misc;

import java.util.*;

/**
 * Dropbox problem.
 *
 */
public class ObjectPool {

    private int capacity;
    private Random random;
    private Map<Integer, Integer> map;

    public ObjectPool(int capacity) {
        this.capacity = capacity;
        this.random = new Random();
        this.map = new HashMap<>();
    }

    public int acquire() {
        if (capacity == 0) {
            throw new RuntimeException("No object is available");
        }

        int objectId = random.nextInt(capacity);
        while (map.containsKey(objectId)) {
            objectId = map.get(objectId);
        }
        map.put(objectId, capacity-1);
        capacity--;

        return objectId;
    }

    public void release(int objectId) {
        map.remove(objectId);
        capacity++;
    }
}
