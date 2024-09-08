package SdeSheetArrays.easy;

import java.util.HashMap;
import java.util.Map;

public class LongestSubarrayWithSumKBoth {
    // Brute Force Approach 1: Check all subarrays and calculate their sums
    // This approach considers every possible subarray and computes the sum.
    // If the sum equals k, it updates the maximum length found.
    public static int lenOfLongSubarrBrute1(int arr[], int n, int k) {
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int p = i; p <= j; p++) {
                    sum += arr[p]; // Calculate sum of subarray from index i to j
                }
                if (sum == k) {
                    maxLen = Math.max(maxLen, j - i + 1); // Update maxLen if sum equals k
                }
            }
        }
        return maxLen;
    }

    // Brute Force Approach 2: Optimized version of Brute Force 1
    // Instead of recalculating the sum for every subarray, it builds the sum incrementally.
    public static int lenOfLongSubarrBrute2(int arr[], int n, int k) {
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j]; // Incrementally build the sum from index i to j
                if (sum == k) {
                    maxLen = Math.max(maxLen, j - i + 1); // Update maxLen if sum equals k
                }
            }
        }
        return maxLen;
    }

    // Better Approach: Using HashMap for O(n) complexity, handles negative numbers too
    // This approach uses a HashMap to store the cumulative sum up to each index.
    // It checks if the difference between the current sum and k has been seen before.
    public static int lenOfLongSubarrHashing(int arr[], int n, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxLen = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i]; // Add current element to cumulative sum

            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1); // If sum equals k, update maxLen
            }

            int rem = sum - k; // Check if sum-k exists in the map
            if (map.containsKey(rem)) {
                int subArrayLen = i - map.get(rem); // Calculate subarray length
                maxLen = Math.max(maxLen, subArrayLen); // Update maxLen if required
            }

            // Store sum if it's not already present in the map
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int arr[] = {10, 2, -2, -20, 10};
        int n = arr.length;
        int k = -10;

        System.out.println("Brute Force Approach 1: " + lenOfLongSubarrBrute1(arr, n, k));
        System.out.println("Brute Force Approach 2: " + lenOfLongSubarrBrute2(arr, n, k));
        System.out.println("Hashing Approach: " + lenOfLongSubarrHashing(arr, n, k));
    }
}
