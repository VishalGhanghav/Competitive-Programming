package SdeSheetTwoPointerSliding.medium;

import java.util.HashMap;
import java.util.Map;

public class BinarySubarrayWithSum {
    // Brute: TC: O(N^2), SC: O(1)
    public static int numSubarraysWithSumBrute(int[] nums, int goal) {
        int noOfSubArrays = 0;
        int n = nums.length;
        // Brute
        // Simply go all elmt and check if sum<goal then res++
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == goal) {
                    noOfSubArrays++;
                }
                if (sum > goal) {
                    break;
                }
            }
        }
        return noOfSubArrays;
    }

    // Better: TC: O(N), SC: O(N)
    public static int numSubarraysWithSumBetter(int[] nums, int goal) {
        int prefixSum = 0, cnt = 0;
        int n = nums.length;
        // Map to have prefixSum and its frequency
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        // We have found sum 0 with count 1 at start
        prefixSumMap.put(0, 1);

        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            // If prefixSum - goal already present means we have found this subarray before and add its count
            if (prefixSumMap.containsKey(prefixSum - goal)) {
                cnt += prefixSumMap.get(prefixSum - goal);
            }
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }
        return cnt;
    }

    // Optimal: TC: O(N), SC: O(1) sliding window
    public static int numSubarraysWithSumOptimal(int[] nums, int goal) {
        // We calculate all subarrays with sum<=goal - sum<=goal-1
        return sumAtMostGoal(nums, goal) - sumAtMostGoal(nums, goal - 1);
    }

    private static int sumAtMostGoal(int[] nums, int goal) {
        if (goal < 0) {
            return 0;
        }
        int n = nums.length;
        int sum = 0, cntOfSubarrays = 0, l = 0, r = 0;

        while (r < n) {
            sum += nums[r];
            /*if(sum <= goal) {
                //No of subarrays <=goal are of window size.eg:101 are 1 01 101
                cntOfSubarrays += (r-l+1);
                r++;
            } else {
                while(sum > goal) {
                    sum -= nums[l];
                    l++;
                }
                if(sum<=goal) {
                    cntOfSubarrays += (r-l+1);
                    r++;
                }
            }*/
            // Why commented: If observed we are doing cntOfSubarrays += (r-l+1);
            // in both cases, so let's get it out
            while (sum > goal) {
                sum -= nums[l];
                l++;
            }
            // after while(sum>goal), sum will always be <= goal
            cntOfSubarrays += (r - l + 1);
            r++;
        }
        return cntOfSubarrays;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;

        System.out.println("Brute: " + numSubarraysWithSumBrute(nums, goal));     // Expected: 4
        System.out.println("Better: " + numSubarraysWithSumBetter(nums, goal));   // Expected: 4
        System.out.println("Optimal: " + numSubarraysWithSumOptimal(nums, goal)); // Expected: 4
    }
}
