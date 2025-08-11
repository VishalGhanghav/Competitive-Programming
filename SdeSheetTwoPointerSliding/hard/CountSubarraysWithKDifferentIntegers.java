package SdeSheetTwoPointerSliding.hard;

import java.util.HashMap;
import java.util.Map;

public class CountSubarraysWithKDifferentIntegers {

        // Brute: TC: O(N^2), SC: O(K)
        // Check all subarrays and count if they have exactly K distinct elements
        public int subarraysWithKDistinctBrute(int[] nums, int k) {
            int n = nums.length;
            int cntOfSubarrays = 0;
            // If map.size() == k means we got k no of distinct elements
            for (int i = 0; i < n; i++) {
                Map<Integer, Integer> freqMap = new HashMap<>();
                for (int j = i; j < n; j++) {
                    freqMap.put(nums[j], freqMap.getOrDefault(nums[j], 0) + 1);
                    if (freqMap.size() == k) {
                        cntOfSubarrays++;
                    } else if (freqMap.size() > k) {
                        break;
                    }
                }
            }
            return cntOfSubarrays;
        }

        // Optimal: TC: O(N), SC: O(K)
        // Count subarrays with at most K distinct, subtract at most (K-1) distinct to get exactly K distinct
        public int subarraysWithKDistinctOptimal(int[] nums, int k) {
            return noOfSubarraysAtMostKDiffIntegers(nums, k) - noOfSubarraysAtMostKDiffIntegers(nums, k - 1);
        }

        // Helper: Count subarrays with at most K distinct integers
        private int noOfSubarraysAtMostKDiffIntegers(int[] nums, int k) {
            int i = 0, j = 0;
            Map<Integer, Integer> freqMap = new HashMap<>();
            int cntOfSubarrays = 0, n = nums.length;
            while (j < n) {
                freqMap.put(nums[j], freqMap.getOrDefault(nums[j], 0) + 1);
                // Reduce window until it has at most k distinct integers
                while (freqMap.size() > k) {
                    freqMap.put(nums[i], freqMap.get(nums[i]) - 1);
                    if (freqMap.get(nums[i]) == 0) {
                        freqMap.remove(nums[i]);
                    }
                    i++;
                }
                // All subarrays ending at j and starting between i..j are valid
                cntOfSubarrays += (j - i + 1);
                j++;
            }
            return cntOfSubarrays;
        }

        public static void main(String[] args) {
            CountSubarraysWithKDifferentIntegers solver = new CountSubarraysWithKDifferentIntegers();
            int[] nums = {1, 2, 1, 2, 3};
            int k = 2;
            System.out.println("Brute: " + solver.subarraysWithKDistinctBrute(nums, k));   // Expected: 7
            System.out.println("Optimal: " + solver.subarraysWithKDistinctOptimal(nums, k)); // Expected: 7
        }

}
