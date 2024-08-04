package SdeSheetDp.src.DpOnStrings;

public class MinInsertionToConvertStringIntoPalindrome {
    public int minInsertions(String s) {
        // To find the minimum number of insertions to make the string a palindrome,
        // we first find the longest palindromic subsequence (LPS).
        // The number of insertions required will be equal to the difference between
        // the length of the string and the length of the LPS.

        // Reverse the string to facilitate LCS calculation
        String rev = new StringBuilder(s).reverse().toString();
        int m = s.length();
        int[][] dp = new int[m + 1][m + 1];

        // Compute the length of the longest palindromic subsequence
        int lpsLength = getLcs(s, rev, m, m, dp);

        // Minimum insertions required to make the string a palindrome
        int noOfInsertions = s.length() - lpsLength;
        return noOfInsertions;
    }

    public int getLcs(String s1, String s2, int m, int n, int[][] dp) {
        // Compute the length of the longest common subsequence (LCS)
        // between s1 and s2 using dynamic programming.

        // Fill the dp array using the LCS logic
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // Return the length of the longest common subsequence
        return dp[m][n];
    }

    // Main method to test the solution
    public static void main(String[] args) {
        MinInsertionToConvertStringIntoPalindrome sol = new MinInsertionToConvertStringIntoPalindrome();

        String s = "aebcbda";
        //lps=abcba .left out=ed .So output=length of ed ie.2

        int minInsertions = sol.minInsertions(s);
        System.out.println("Minimum number of insertions required: " + minInsertions);
    }
}
