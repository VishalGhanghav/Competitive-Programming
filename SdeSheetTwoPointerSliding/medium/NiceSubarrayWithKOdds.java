package SdeSheetTwoPointerSliding.medium;

import java.util.HashMap;
import java.util.Map;

public class NiceSubarrayWithKOdds {

        // Brute: TC: O(N^2), SC: O(1)
        public static int numberOfSubarraysBrute(int[] nums, int k) {
            int noOfOddSubarrays = 0;
            int n = nums.length;
            // Brute
            for (int i = 0; i < n; i++) {
                int noOfOdds = 0;
                for (int j = i; j < n; j++) {
                    if (nums[j] % 2 == 1) {
                        noOfOdds++;
                    }
                    if (noOfOdds == k) {
                        noOfOddSubarrays++;
                    }
                    if (noOfOdds > k) break; // small optimization
                }
            }
            return noOfOddSubarrays;
        }

        // Better: TC: O(N), SC: O(N)
        public static int numberOfSubarraysBetter(int[] nums, int k) {
            int noOfOddSubarrays = 0;
            int n = nums.length;
            // Better:
            // Do nums%2 to get all num as binary and apply binary subarray with sum
            // As odd num will only result in sum
            Map<Integer, Integer> prefixMap = new HashMap<>();
            int prefixSum = 0;

            // We have found sum 0 with count 1 at start
            prefixMap.put(0, 1);
            for (int i = 0; i < n; i++) {
                prefixSum += nums[i] % 2;

                if (prefixMap.containsKey(prefixSum - k)) {
                    noOfOddSubarrays += prefixMap.get(prefixSum - k);
                }
                prefixMap.put(prefixSum, prefixMap.getOrDefault(prefixSum, 0) + 1);
            }
            return noOfOddSubarrays;
        }

        // Optimal: TC: O(N), SC: O(1)
        public static int numberOfSubarraysOptimal(int[] nums, int k) {
            // We use sumAtMost(K) - sumAtMost(k-1) to get sum == k
            // sumAtMost(k) gives all subarrays less than equal to k.
            return sumAtMostGoal(nums, k) - sumAtMostGoal(nums, k - 1);
        }

        private static int sumAtMostGoal(int[] nums, int goal) {
            if (goal < 0) {
                return 0;
            }
            int n = nums.length;
            int sum = 0, cntOfSubarrays = 0, l = 0, r = 0;
            while (r < n) {
                sum += nums[r] % 2;
                while (sum > goal) {
                    sum -= nums[l] % 2;
                    l++;
                }
                // after while(sum>goal.sum will always be <=goal)
                cntOfSubarrays += (r - l + 1);
                r++;
            }
            return cntOfSubarrays;
        }

        public static void main(String[] args) {
            int[] nums = {1, 1, 2, 1, 1};
            int k = 3;

            System.out.println("Brute: " + numberOfSubarraysBrute(nums, k));     // Expected: 2
            System.out.println("Better: " + numberOfSubarraysBetter(nums, k));   // Expected: 2
            System.out.println("Optimal: " + numberOfSubarraysOptimal(nums, k)); // Expected: 2
        }
}
