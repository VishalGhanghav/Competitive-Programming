package SdeSheetDp.src.DpOnSubsequences;

import java.util.Arrays;

public class PartitionsWithGIvenDifference {
    private static final int MOD = (int) Math.pow(10, 9) + 7;

    // Function to count the number of valid partitions
    public static int countPartitions(int n, int d, int[] arr) {
        int sum = Arrays.stream(arr).sum();

        // Check if (sum + d) is even, otherwise return 0
        if ((sum + d) % 2 != 0) {
            return 0;
        }

        int targetSum = (sum + d) / 2;

        // If the target sum is greater than the total sum, no valid partition exists
        if (targetSum > sum) {
            return 0;
        }

        // Initialize memoization table
        Integer[][] dp = new Integer[n + 1][targetSum + 1];
        return countSubsets(arr, n, targetSum, dp);
    }

    // Helper function to count the subsets with a given sum using memoization
    private static int countSubsets(int[] arr, int n, int sum, Integer[][] dp) {
        // Base cases
        if(n==0 && sum==0){
            return 1;
        }

        if(n==0 && sum>0){
            return 0;
        }

        // Check memoization table
        if (dp[n][sum] != null) {
            return dp[n][sum];
        }

        // Recursive cases
        if (arr[n - 1] <= sum) {
            dp[n][sum] = (countSubsets(arr, n - 1, sum - arr[n - 1], dp) +
                    countSubsets(arr, n - 1, sum, dp)) % MOD;
        } else {
            dp[n][sum] = countSubsets(arr, n - 1, sum, dp) % MOD;
        }

        return dp[n][sum];
    }

    // Main function to test the implementation
    public static void main(String[] args) {
        int n1 = 4;
        int d1 = 3;
        int[] arr1 = {5, 2, 6, 4};
        System.out.println("Test Case 1 Output: " + countPartitions(n1, d1, arr1)); // Output: 1

        int n2 = 4;
        int d2 = 0;
        int[] arr2 = {1, 1, 1, 1};
        System.out.println("Test Case 2 Output: " + countPartitions(n2, d2, arr2)); // Output: 6

        // Additional test case
        int n3 = 5;
        int d3 = 5;
        int[] arr3 = {1, 3, 3, 2, 1};
        System.out.println("Test Case 3 Output: " + countPartitions(n3, d3, arr3)); // Custom test case
    }
}
