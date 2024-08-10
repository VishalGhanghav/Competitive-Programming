package SdeSheetDp.src.DpOnLIS;

import java.util.Arrays;

public class LongestStringChain {

    // Function to find the length of the longest string chain
    public int longestStrChain(String[] words) {
        int n = words.length;
        int[] dp = new int[n];
        int maxi = 0;

        // Prerequisite: LIS (Longest Increasing Subsequence)
        // We just need to return maxLength, so no hash array needed

        // Sort the words array by length (ascending order)
        Arrays.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));

        // Fill dp array with 1
        Arrays.fill(dp, 1);

        for (int ind = 0; ind < n; ind++) {
            for (int prev = 0; prev < ind; prev++) {
                // Check if words[ind] can form a valid chain by extending words[prev]
                if (checkIfPossible(words[ind], words[prev]) && 1 + dp[prev] > dp[ind]) {
                    dp[ind] = 1 + dp[prev];
                }
            }
            maxi = Math.max(maxi, dp[ind]);
        }

        return maxi;
    }

    // Function to check if s1 can be formed by removing exactly one character from s2
    public boolean checkIfPossible(String s1, String s2) {
        int first = 0;
        int second = 0;

        // Length of s1 should be s1 = length of s2 + 1 for a valid chain
        if (s1.length() != s2.length() + 1) {
            return false;
        }

        // Compare characters from s1 and s2
        while (first < s1.length()) {
            // If characters match, move both pointers
            if (second < s2.length() && s1.charAt(first) == s2.charAt(second)) {
                first++;
                second++;
            } else {
                // Move only first pointer if characters don't match
                first++;
            }
        }

        // Return true if both strings are fully traversed
        return first == s1.length() && second == s2.length();
    }

    // Main method to run the example
    public static void main(String[] args) {
        LongestStringChain lsc = new LongestStringChain();

        // Example input
        String[] words = {"a", "b", "ba", "bda", "bca", "bdca"};

        // Find the length of the longest string chain
        int result = lsc.longestStrChain(words);//b ba bda bdca

        // Print the result
        System.out.println("Longest String Chain Length: " + result);  // Expected output: 4
    }
}