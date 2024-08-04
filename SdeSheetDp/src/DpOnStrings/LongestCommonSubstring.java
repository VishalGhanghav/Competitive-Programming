package SdeSheetDp.src.DpOnStrings;

public class LongestCommonSubstring {
    // Method to find the longest common substring length using tabulation
    public int longestCommonSubstr(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int ans = 0;

        // Fill dp array using tabulation
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return ans;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        LongestCommonSubstring sol = new LongestCommonSubstring();

        String s1 = "abcde";
        String s2 = "abfce";
        //op:ab

        // Test tabulation approach
        int lcsLengthTabulation = sol.longestCommonSubstr(s1, s2);
        System.out.println("Length of Longest Common Substring (Tabulation): " + lcsLengthTabulation);
    }
}
