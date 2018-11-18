package heap;

import java.util.*;

/**
 * Give an O(n lg k)-time algorithm to merge k sorted lists into one sorted list where n is the total number of elements
 * in all the input lists.
 */
public class MergeSortedLists {

    private static class HeapElement {
        private int id;
        private int index;

        public HeapElement(int id, int index) {
            this.id = id;
            this.index = index;
        }
    }

    public static List<Integer> merge(List<List<Integer>> lists) {
        int k = lists.size();
        if (k == 0) {
            return Collections.emptyList();
        }

        PriorityQueue<HeapElement> minPriorityQueue = new PriorityQueue<>(
                Comparator.comparing(heapElement -> lists.get(heapElement.id).get(heapElement.index)));

        for (int i = 0; i < lists.size(); i++) {
            if (!lists.get(i).isEmpty()) {
                minPriorityQueue.offer(new HeapElement(i, 0));
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!minPriorityQueue.isEmpty()) {
            HeapElement heapElement = minPriorityQueue.poll();
            result.add(lists.get(heapElement.id).get(heapElement.index));

            if (heapElement.index + 1 < lists.get(heapElement.id).size()) {
                heapElement.index++;
                minPriorityQueue.offer(heapElement);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        testCase(Arrays.asList(
                Arrays.asList(1, 5, 9),
                Arrays.asList(3, 7),
                Arrays.asList(10),
                Arrays.asList(2, 6, 10),
                Arrays.asList(4, 8),
                Arrays.asList(8),
                Arrays.asList()
        ));
    }

    private static void testCase(List<List<Integer>> lists) {
        System.out.println("Lists : " + lists);
        List<Integer> mergedList = MergeSortedLists.merge(lists);
        System.out.println("Merged List : " + mergedList);
    }
}
