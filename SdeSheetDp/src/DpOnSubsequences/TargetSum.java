package SdeSheetDp.src.DpOnSubsequences;

import java.util.Arrays;

public class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        //s1+s2=totalSum  s1-s2=target
        //s1=totalSum+target/2
        //Its exactly same as count subsets with given sum .Just a little tweak of s1-s2=target
        int sum = Arrays.stream(nums).sum();

        // Check if (sum + d) is even, otherwise return 0
        if((sum+target)%2!=0 || sum<target || sum + target < 0 ) {
            return 0;
        }

        int targetSum = (sum + target) / 2;
        // Initialize memoization table
        int n=nums.length;
        Integer[][] dp = new Integer[n + 1][targetSum + 1];
        return countSubsets(nums, n, targetSum, dp);
    }

    private int countSubsets(int[] arr, int n, int sum, Integer[][] dp) {
        // Base cases
        if (sum == 0 && n==0) {
            return 1; // There's one way to get sum 0 (empty subset)
        }
        if (n == 0 && sum>0) {
            return 0; // No way to get a positive sum with zero elements
        }

        // Check memoization table
        if (dp[n][sum] != null) {
            return dp[n][sum];
        }

        // Recursive cases
        if (arr[n - 1] <= sum) {
            return dp[n][sum] = countSubsets(arr, n-1, sum - arr[n - 1], dp) +
                    countSubsets(arr, n - 1, sum, dp);
        } else {
            return dp[n][sum] = countSubsets(arr, n - 1, sum, dp) ;
        }
    }

    public static void main(String[] args) {
        TargetSum solution = new TargetSum();

        // Example 1
        int[] nums1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        System.out.println("Example 1: " + solution.findTargetSumWays(nums1, target1)); // Output: 5
        /*
       Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
        -1 + 1 + 1 + 1 + 1 = 3
        +1 - 1 + 1 + 1 + 1 = 3
        +1 + 1 - 1 + 1 + 1 = 3
        +1 + 1 + 1 - 1 + 1 = 3
        +1 + 1 + 1 + 1 - 1 = 3
         */
        // Example 2
        int[] nums2 = {1};
        int target2 = 1;
        System.out.println("Example 2: " + solution.findTargetSumWays(nums2, target2)); // Output: 1

        // Additional Examples
        int[] nums3 = {1, 2, 3, 4};
        int target3 = 2;
        System.out.println("Additional Example 1: " + solution.findTargetSumWays(nums3, target3)); // Output might be 2

        int[] nums4 = {2, 2, 2, 2};
        int target4 = 4;
        System.out.println("Additional Example 2: " + solution.findTargetSumWays(nums4, target4)); // Output might be 6

        int[] nums5 = {5, 6, 7, 8};
        int target5 = 3;
        System.out.println("Additional Example 3: " + solution.findTargetSumWays(nums5, target5)); // Output might be 1
    }
}
