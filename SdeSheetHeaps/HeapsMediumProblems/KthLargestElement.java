package SdeSheetHeaps.HeapsMediumProblems;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElement {
    // Brute Force
    // Sort in descending and pick kth element
    // TC: O(n log n), SC: O(1)
    public int findKthLargestBrute(int[] nums, int k) {
        Arrays.sort(nums); // Ascending sort
        int ind = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (ind == k) {
                return nums[i];
            }
            ind++;
        }
        return -1; // Shouldn't reach here
    }

    // Optimal
    // Min-Heap of size k
    // TC: O(n log k), SC: O(k)
    public int findKthLargestOptimal(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove smallest in heap
            }
        }
        return minHeap.peek(); // kth largest
    }

    public static void main(String[] args) {
        KthLargestElement obj = new KthLargestElement();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;

        int bruteResult = obj.findKthLargestBrute(nums.clone(), k);
        System.out.println("Brute Kth Largest: " + bruteResult); // Output: 5

        int optimalResult = obj.findKthLargestOptimal(nums.clone(), k);
        System.out.println("Optimal Kth Largest: " + optimalResult); // Output: 5
    }
}
