package SdeSheetDp.src.DpOnStrings;

public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int l1 = text1.length();
        int l2 = text2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        // Uncomment the line below to use memoization
        // Integer[][] memo = new Integer[l1 + 1][l2 + 1];
        // return getLcsMemo(text1, text2, l1, l2, memo);
        return getLcsDp(text1, text2, l1, l2, dp);
    }

    // Tabulation approach to find the longest common subsequence
    public int getLcsDp(String text1, String text2, int l1, int l2, int[][] dp) {
        // Fill dp array using tabulation
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[l1][l2];
    }

    // Memoization approach to find the longest common subsequence
    public int getLcsMemo(String s1, String s2, int m, int n, Integer[][] dp) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (dp[m][n] != null) {
            return dp[m][n];
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            dp[m][n] = 1 + getLcsMemo(s1, s2, m - 1, n - 1, dp);
        } else {
            dp[m][n] = Math.max(getLcsMemo(s1, s2, m, n - 1, dp), getLcsMemo(s1, s2, m - 1, n, dp));
        }
        return dp[m][n];
    }

    // Main method to test the solution
    public static void main(String[] args) {
        LongestCommonSubsequence sol = new LongestCommonSubsequence();

        String text1 = "abcde";
        String text2 = "ace";

        int lcsLength = sol.longestCommonSubsequence(text1, text2);

        System.out.println("Length of Longest Common Subsequence: " + lcsLength);

        // Test memoization approach
        // Integer[][] memo = new Integer[text1.length() + 1][text2.length() + 1];
        // int lcsLengthMemo = sol.getLcsMemo(text1, text2, text1.length(), text2.length(), memo);
        // System.out.println("Length of Longest Common Subsequence (Memoization): " + lcsLengthMemo);
    }
}
