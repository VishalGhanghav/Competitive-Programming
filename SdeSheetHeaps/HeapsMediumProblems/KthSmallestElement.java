package SdeSheetHeaps.HeapsMediumProblems;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestElement {

    // Brute Force
    // Sort and return kth smallest
    // TC: O(n log n), SC: O(1)
    public static int kthSmallestBrute(int[] nums, int k) {
        Arrays.sort(nums); // Ascending order
        return nums[k - 1]; // kth smallest is at index k-1
    }

    // Optimal
    // Max Heap of size k
    // TC: O(n log k), SC: O(k)
    public static int kthSmallestOptimal(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            maxHeap.add(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // remove largest among k+1 to retain k smallest
            }
        }
        return maxHeap.peek(); // kth smallest element
    }

    public static void main(String[] args) {
        int[] nums = {7, 10, 4, 3, 20, 15};
        int k = 3;

        int bruteResult = kthSmallestBrute(nums.clone(), k);
        System.out.println("Brute Kth Smallest: " + bruteResult); // Output: 7

        int optimalResult = kthSmallestOptimal(nums.clone(), k);
        System.out.println("Optimal Kth Smallest: " + optimalResult); // Output: 7
    }
}

