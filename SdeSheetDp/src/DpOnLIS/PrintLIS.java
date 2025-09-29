package SdeSheetDp.src.DpOnLIS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PrintLIS {
    public ArrayList<Integer> longestIncreasingSubsequence(int n, int[] arr) {
        // Create a DP array to store the length of the longest increasing subsequence ending at each index
        int[] dp = new int[n];
        // Initialize all elements of dp to 1 as each element is an increasing subsequence of length 1
        Arrays.fill(dp, 1);

        // Array to store the index of the previous element in the longest increasing subsequence
        int[] hash = new int[n];

        // Variables to keep track of the maximum length of the LIS and the index where this maximum length ends
        int maxi = 0;
        int lastIndex = 0;

        // Initialize hash array such that hash[ind] is set to ind initially
        for (int ind = 0; ind < n; ind++) {
            hash[ind] = ind; // Start with the assumption that the longest subsequence ending at ind starts at ind

            // Iterate over all previous elements to find the LIS ending at the current index
            for (int prev = 0; prev < ind; prev++) {
                // Check if arr[ind] can be appended to the LIS ending at prev
                if (arr[ind] > arr[prev] && 1 + dp[prev] > dp[ind]) {
                    // Update the dp array with the maximum length of the LIS ending at ind
                    dp[ind] = 1 + dp[prev];
                    // Update the hash array to point to the previous index in the LIS
                    hash[ind] = prev;
                }
            }

            // Update the maximum length and the index where the LIS of maximum length ends
            if (dp[ind] > maxi) {
                maxi = dp[ind];
                lastIndex = ind;
            }
        }

        // Create an ArrayList to store the longest increasing subsequence
        ArrayList<Integer> res = new ArrayList<>();
        // Add the last element of the LIS to the result
        res.add(arr[lastIndex]);

        // Trace back through the hash array to build the LIS from the end to the beginning
        // Example to illustrate the while loop:
        // Let's say the final hash array is [0, 0, 1, 1, 2, 3, 4, 5]
        // And the lastIndex is 6 (which corresponds to element 101 in arr)
        // Here's the trace back:
        // res = [101] (added the last element 101)
        // lastIndex = 6 (hash[6] = 4, so update lastIndex to 4)
        // res = [101, 7] (added the element 7)
        // lastIndex = 4 (hash[4] = 2, so update lastIndex to 2)
        // res = [101, 7, 3] (added the element 3)
        // lastIndex = 2 (hash[2] = 1, so update lastIndex to 1)
        // res = [101, 7, 3, 2] (added the element 2)
        // lastIndex = 1 (hash[1] = 0, so update lastIndex to 0)
        // res = [101, 7, 3, 2, 10] (added the element 10)
        // lastIndex = 0 (hash[0] = 0, so the loop stops here as hash[lastIndex] == lastIndex)
        // Reverse the result list as we built it backwards
        //Now based on hashArray we will travel
        while(hash[lastIndex]!=lastIndex){
            lastIndex=hash[lastIndex];
            res.add(arr[lastIndex]);
        }
        Collections.reverse(res);

        return res;
    }

    public static void main(String[] args) {
        PrintLIS solution = new PrintLIS();
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        int n = arr.length;

        ArrayList<Integer> lis = solution.longestIncreasingSubsequence(n, arr);
        System.out.println("Longest Increasing Subsequence: " + lis);
    }
}
