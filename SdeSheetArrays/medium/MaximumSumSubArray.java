package SdeSheetArrays.medium;

import java.util.ArrayList;
import java.util.List;

public class MaximumSumSubArray {

    // Brute Force Approach: Check all possible subarrays
    public int maxSubArrayBrute(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // Calculate sum of subarray from i to j
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    // Better Approach: Avoid recalculating sums by extending previous subarray
    public int maxSubArrayBetter(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    // Optimal Approach: Kadane's Algorithm
    public int maxSubArrayOptimal(int[] nums) {
        int n = nums.length;
        int maxi = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            maxi = Math.max(maxi, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxi;
    }

    // Optimal Approach with subarray printing
    public List<Integer> maxSubArrayWithPrint(int[] nums) {
        int n = nums.length;
        int maxi = Integer.MIN_VALUE;
        int sum = 0;
        int start = 0, ansStart = -1, ansEnd = -1;
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (sum == 0) {
                start = i;
            }
            sum += nums[i];
            if (sum > maxi) {
                maxi = sum;
                ansStart = start;
                ansEnd = i;
            }
            if (sum < 0) {
                sum = 0;
            }
        }

        for (int i = ansStart; i <= ansEnd; i++) {
            res.add(nums[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        MaximumSumSubArray msa = new MaximumSumSubArray();
        int[] nums = {-2, -3, 4, -1, -2, 1, 5, -3};

        // Run Brute Force approach
        System.out.println("Brute Force Max Sum: " + msa.maxSubArrayBrute(nums));

        // Run Better approach
        System.out.println("Better Approach Max Sum: " + msa.maxSubArrayBetter(nums));

        // Run Optimal (Kadane's) approach
        System.out.println("Optimal (Kadane's) Max Sum: " + msa.maxSubArrayOptimal(nums));

        // Run Optimal approach with subarray printing
        List<Integer> maxSubarray = msa.maxSubArrayWithPrint(nums);
        System.out.println("Max Subarray with Print: " + maxSubarray);
    }
}
