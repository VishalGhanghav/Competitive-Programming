package SdeSheetDp.src.DpOnLIS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] arr) {
        // Initialize the length of the array
        int n = arr.length;

        // DP array to store the length of the longest divisible subset ending with arr[i]
        int[] dp = new int[n];

        // Hash array to keep track of the previous index for the subset
        int[] hash = new int[n];

        // Variables to track the maximum length of divisible subset and its ending index
        int maxi = 0;
        int lastIndex = 0;

        // Initialize DP array with 1 (each element can form a subset of length 1)
        Arrays.fill(dp, 1);

        // Hash array will be updated during iteration to keep track of previous indices
        // (No need to initialize hash array with -1 as it will be updated in the loop)

        // Sort the array to ensure we check elements in increasing order
        Arrays.sort(arr);

        // Iterate through each element of the array
        for (int ind = 0; ind < n; ind++) {
            hash[ind] = ind; // Initialize hash with the index itself
            for (int prev = 0; prev < ind; prev++) {
                // Check if the current element is divisible by the previous element
                // and if we can form a longer subset by including the current element
                if (arr[ind] % arr[prev] == 0 && 1 + dp[prev] > dp[ind]) {
                    dp[ind] = 1 + dp[prev];
                    hash[ind] = prev; // Update hash to point to the previous index
                }
            }
            // Update maxi and lastIndex if we found a longer subset
            if (dp[ind] > maxi) {
                maxi = dp[ind];
                lastIndex = ind;
            }
        }

        // List to store the result subset
        List<Integer> res = new ArrayList<>();

        // Start from the lastIndex and trace back to build the result subset
        res.add(arr[lastIndex]);
        while (hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            res.add(arr[lastIndex]);
        }

        // Reverse the result list to get the correct order
        Collections.reverse(res);

        return res;
    }

    public static void main(String[] args) {
        LongestDivisibleSubset lds = new LongestDivisibleSubset();

        int[] arr = {1, 2, 3, 4, 6, 12};

        List<Integer> result = lds.largestDivisibleSubset(arr);

        System.out.println("Largest Divisible Subset: " + result);
    }
}
