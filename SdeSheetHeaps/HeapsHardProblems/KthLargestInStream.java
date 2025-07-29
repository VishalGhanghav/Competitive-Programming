package SdeSheetHeaps.HeapsHardProblems;

import java.util.*;

public class KthLargestInStream {

    PriorityQueue<Integer> minHeap;
    private int p;

    // TC: O(N * log K), SC: O(K)
    // Reason: Each add operation is O(log K), done N times during init
    public KthLargestInStream(int k, int[] nums) {
        //Get k and create a minHeap of size k. to get kth largest
        this.p = k;
        minHeap = new PriorityQueue<>();
        for (int num : nums) {
            add(num);
        }
    }

    // TC: O(log K), SC: O(1)
    // Reason: Heap maintains only K elements at any time
    public int add(int val) {
        minHeap.add(val);
        if (minHeap.size() > p) {
            minHeap.poll();
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 8, 2};
        KthLargestInStream kthLargest = new KthLargestInStream(3, nums);

        int[] inputs = {3, 5, 10, 9, 4};
        for (int val : inputs) {
            int kth = kthLargest.add(val);
            System.out.println("Added: " + val + ", Kth Largest: " + kth);
            System.out.println("Current Heap (size = " + kthLargest.minHeap.size() + "): " + getHeapSnapshot(kthLargest.minHeap));
            System.out.println("------");
        }
    }

    // Helper to return sorted view of heap (for display)
    private static List<Integer> getHeapSnapshot(PriorityQueue<Integer> heap) {
        List<Integer> snapshot = new ArrayList<>(heap);
        Collections.sort(snapshot); // minHeap, so ascending order
        return snapshot;
    }
}
