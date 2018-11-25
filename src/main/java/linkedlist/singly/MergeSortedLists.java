package linkedlist.singly;

import util.list.singly.ListUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list.
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 */
public class MergeSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparing(index -> lists[index].getKey()));
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                minHeap.offer(i);
            }
        }

        ListNode dummyHead = new ListNode(0);
        ListNode result = dummyHead;
        while (!minHeap.isEmpty()) {
            int index = minHeap.poll();
            ListNode current = lists[index];
            ListNode next = current.getNext();

            result.setNext(current);
            current.setNext(null);
            result = result.getNext();

            if (next != null) {
                lists[index] = next;
                minHeap.offer(index);
            }
        }

        return dummyHead.getNext();
    }

    public static void main(String[] args) {
        ListNode[] lists = {
                ListUtils.buildList(Arrays.asList(1, 4, 5)),
                ListUtils.buildList(Arrays.asList(1, 3, 4)),
                ListUtils.buildList(Arrays.asList(2, 6))
        };

        ListNode result = new MergeSortedLists().mergeKLists(lists);
        System.out.println("Merged list : " + ListUtils.toString(result));
    }
}
