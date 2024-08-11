package SdeSheetDp.src.DpOnLIS;

import java.util.Arrays;

public class NumberOfLIS {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;

        // DP array to store the length of the longest increasing subsequence ending at each index
        int[] dp = new int[n];
        // Count array to store the number of longest increasing subsequences ending at each index
        int[] cnt = new int[n];

        // Initialize DP and count arrays
        Arrays.fill(dp, 1); // Each element is an increasing subsequence of length 1
        Arrays.fill(cnt, 1); // Each element has at least one subsequence (itself)

        int maxi = 1; // Variable to store the length of the longest increasing subsequence
        int res = 0;  // Variable to store the number of longest increasing subsequences

        // Fill DP and count arrays
        for (int ind = 0; ind < n; ind++) {
            for (int prev = 0; prev < ind; prev++) {
                // Check if the current element can extend the subsequence ending at `prev`
                if (nums[ind] > nums[prev] && 1 + dp[prev] > dp[ind]) {
                    // We found a longer subsequence ending at `ind`
                    dp[ind] = 1 + dp[prev];
                    // Update the count of LIS ending at `ind` to be the count of LIS ending at `prev`
                    cnt[ind] = cnt[prev];
                } else if (nums[ind] > nums[prev] && 1 + dp[prev] == dp[ind]) {
                    // We found another LIS of the same length ending at `ind`
                    cnt[ind] += cnt[prev];
                }
            }
            // Update the maximum length of LIS found so far
            if (dp[ind] > maxi) {
                maxi = dp[ind];
            }
        }

        // Calculate the total number of LIS of length `maxi`
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxi) {
                res += cnt[i];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        NumberOfLIS solution = new NumberOfLIS();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        //2 3 7 101  //2 5 7 101 //2 3 7 18 //2 5 7 18
        int result = solution.findNumberOfLIS(nums);
        System.out.println("Number of Longest Increasing Subsequences: " + result);
    }
}
