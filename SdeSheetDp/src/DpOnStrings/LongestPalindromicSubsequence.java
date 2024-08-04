package SdeSheetDp.src.DpOnStrings;

public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        // Just reverse the string and find LCS of both strings.
        // example: bbbab babbb LCS=bbbb. That's the LPS
        // cbbd dbbc LCS=bb. That's LPS

        String rev = new StringBuilder(s).reverse().toString();
        int m = s.length();
        int[][] dp = new int[m + 1][m + 1];
        return getLcs(s, rev, m, m, dp);
    }

    public int getLcs(String s1, String s2, int m, int n, int[][] dp) {
        // No base case needed as all elements are already set to zero

        // Fill the dp array using LCS logic
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n]; // Return the length of the longest palindromic subsequence
    }

    // Main method to test the solution
    public static void main(String[] args) {
        LongestPalindromicSubsequence sol = new LongestPalindromicSubsequence();

        String s1 = "bbbab";
        String s2 = "cbbd";

        int lps1 = sol.longestPalindromeSubseq(s1);//bbbb len=4
        int lps2 = sol.longestPalindromeSubseq(s2);//bb len=2

        System.out.println("Longest Palindromic Subsequence of \"" + s1 + "\": " + lps1);
        System.out.println("Longest Palindromic Subsequence of \"" + s2 + "\": " + lps2);
    }
}
