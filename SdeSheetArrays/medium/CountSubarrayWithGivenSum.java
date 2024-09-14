package SdeSheetArrays.medium;

import java.util.HashMap;
import java.util.Map;

public class CountSubarrayWithGivenSum {

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {1, 1, 1};
        int target1 = 2;
        System.out.println("Subarrays summing to target (Brute Force): " + subarraySumBrute(nums1, target1));
        System.out.println("Subarrays summing to target (Better): " + subarraySumBetter(nums1, target1));
        System.out.println("Subarrays summing to target (Optimal): " + subarraySum(nums1, target1));

        // Test case 2: Including negative numbers
        int[] nums2 = {-1, -1, 1};
        int target2 = 0;
        System.out.println("Subarrays summing to target (Brute Force): " + subarraySumBrute(nums2, target2));
        System.out.println("Subarrays summing to target (Better): " + subarraySumBetter(nums2, target2));
        System.out.println("Subarrays summing to target (Optimal): " + subarraySum(nums2, target2));

        // Test case 3: Edge case with an empty array
        int[] nums3 = {};
        int target3 = 0;
        System.out.println("Subarrays summing to target (Brute Force): " + subarraySumBrute(nums3, target3));
        System.out.println("Subarrays summing to target (Better): " + subarraySumBetter(nums3, target3));
        System.out.println("Subarrays summing to target (Optimal): " + subarraySum(nums3, target3));
    }

    // Brute Force Approach: Time complexity O(n^3)
    public static int subarraySumBrute(int[] nums, int target) {
        int n = nums.length;
        int cnt = 0;

        // Brute Force: Check each possible subarray sum
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k]; // Sum up the subarray from index i to j
                }
                if (sum == target) {
                    cnt++; // If sum matches target, increase count
                }
            }
        }
        return cnt;
    }

    // Better Approach: Time complexity O(n^2)
    public static int subarraySumBetter(int[] nums, int target) {
        int n = nums.length;
        int cnt = 0;

        // Check subarray sums in O(n^2)
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j]; // Increment sum from index i to j
                if (sum == target) {
                    cnt++; // If sum matches target, increase count
                }
            }
        }
        return cnt;
    }

    // Optimal Approach: Time complexity O(n), using prefix sum and a HashMap
    public static int subarraySum(int[] nums, int target) {
        int n = nums.length;
        int cnt = 0;
        int prefixSum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        // Initializing map with base case (0 sum at the start)
        map.put(0, 1);

        // Iterate over the array
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i]; // Calculate the running prefix sum

            // Check if (prefixSum - target) exists in the map, meaning we found a subarray with sum == target
            int rem = prefixSum - target;
            if (map.containsKey(rem)) {
                cnt += map.get(rem); // Add the frequency of rem to the count
            }

            // Store/update the current prefixSum in the map
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return cnt;
    }
}
