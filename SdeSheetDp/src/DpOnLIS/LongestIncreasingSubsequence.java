package SdeSheetDp.src.DpOnLIS;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // We use n + 1 for the DP array to handle the case where prevIndex is -1.
        // We avoid negative indexing by shifting coordinates, i.e., increasing by 1 everywhere.

        // Initialize the DP table
        int[][] dp = new int[n + 1][n + 1];

        // Fill the DP table in reverse order
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int prevInd = n - 1; prevInd >= -1; prevInd--) {
                long profit = 0;
                if (prevInd == -1 || nums[ind] > nums[prevInd]) {
                    // We can include nums[ind] in the LIS
                    // - `1 + dp[ind + 1][ind + 1]`:
                    //     1: because we are including the current element nums[ind] in the subsequence
                    //     dp[ind + 1][ind + 1]: this is the result of considering the next index (ind + 1)
                    //     and treating the current element (ind) as the new previous element (hence, `ind + 1` as the previous index for the next recursive call)
                    // - `dp[ind + 1][prevInd + 1]`:
                    //     This considers the result of not including the current element (nums[ind])
                    //     and proceeds with the next index (ind + 1) while keeping the previous index the same (prevInd + 1)
                    dp[ind][prevInd + 1] = Math.max(1 + dp[ind + 1][ind + 1],
                            dp[ind + 1][prevInd + 1]);
                } else {
                    // We cannot include nums[ind] in the LIS if nums[ind] <= nums[prevInd]
                    // - `dp[ind + 1][prevInd + 1]`:
                    //     This is the result of not including the current element (nums[ind])
                    //     and moving to the next index (ind + 1) while keeping the previous index unchanged (prevInd + 1)
                    dp[ind][prevInd + 1] = dp[ind + 1][prevInd + 1];
                }
            }
        }
        return dp[0][0];
    }

    private int getLengthOfLIS(int ind, int prevInd, int[] nums, int n, Integer[][] dp) {
        // Base case: when we reach the end of the array
        if (ind == n) {
            return 0;
        }

        // Check if the value is already computed
        if (dp[ind][prevInd + 1] != null) {
            return dp[ind][prevInd + 1];
        }

        long profit = 0;
        if (prevInd == -1 || nums[ind] > nums[prevInd]) {
            // We can include nums[ind] in the LIS
            // - `1 + getLengthOfLIS(ind + 1, ind, nums, n, dp)`:
            //     1: because we are including the current element nums[ind] in the subsequence
            //     getLengthOfLIS(ind + 1, ind, nums, n, dp): recursive call considering the next index (ind + 1)
            //     and treating the current element (ind) as the new previous element (prevInd)
            // - `getLengthOfLIS(ind + 1, prevInd, nums, n, dp)`:
            //     This considers the result of not including the current element (nums[ind])
            //     and proceeds with the next index (ind + 1) while keeping the previous index the same (prevInd)
            dp[ind][prevInd + 1] = Math.max(1 + getLengthOfLIS(ind + 1, ind, nums, n, dp),
                    getLengthOfLIS(ind + 1, prevInd, nums, n, dp));
        } else {
            // We cannot include nums[ind] in the LIS if nums[ind] <= nums[prevInd]
            // - `getLengthOfLIS(ind + 1, prevInd, nums, n, dp)`:
            //     This is the result of not including the current element (nums[ind])
            //     and moving to the next index (ind + 1) while keeping the previous index unchanged (prevInd)
            dp[ind][prevInd + 1] = getLengthOfLIS(ind + 1, prevInd, nums, n, dp);
        }
        return dp[ind][prevInd + 1];
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int length = solution.lengthOfLIS(nums);
        System.out.println("Length of Longest Increasing Subsequence: " + length);
    }

    private static int longestIncreasingSubsequenceBrute(int[] arr) {
        int[] maxLen = new int[1];

        // inner recursive function to generate subsequences
        generateSubsequences(0, arr, new ArrayList<>(), maxLen);
        return maxLen[0];
    }

    // Recursive function to generate all subsequences and check increasing ones
    private static void generateSubsequences(int index, int[] arr, List<Integer> current, int[] maxLen) {
        if (index == arr.length) {
            // Check if current subsequence is strictly increasing
            boolean isIncreasing = true;
            for (int i = 1; i < current.size(); i++) {
                if (current.get(i) <= current.get(i - 1)) {
                    isIncreasing = false;
                    break;
                }
            }

            if (isIncreasing) {
                maxLen[0] = Math.max(maxLen[0], current.size());
            }
            return;
        }

        // Not take current element
        generateSubsequences(index + 1, arr, current, maxLen);

        // Take current element
        current.add(arr[index]);
        generateSubsequences(index + 1, arr, current, maxLen);

        // Backtrack
        current.remove(current.size() - 1);
    }
}
