package SdeSheetDp.src.DpOnStrings;

public class DistinctSubsequences {
    // Method to count the number of distinct subsequences of s1 that equals s2
    public int numDistinct(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        //for memoization
        /*Integer[][] dp = new Integer[m + 1][n + 1];
        return getDistinctSubsequences(s1, s2, m, n, dp);*/

        //memoization also gave timeout so tabulation
        int[][] dp = new int[m + 1][n + 1];

        // Base case:
        // If index of s2 = 0, it means we have found the answer
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        // If s1 = 0 and s2 elements left, no result found so return 0
        for (int j = 1; j <= n; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Simply copy the recurrence from memoization and make changes here
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // We have two conditions:
                    // If pick: we can pick and reduce index in both strings.
                    // Not pick: no pick and reduce index in m.
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    // Not pick always
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        // We will get our result at end
        return dp[m][n];
    }

    // Helper method for memoization approach
    private int getDistinctSubsequences(String s1, String s2, int m, int n, Integer[][] dp) {
        if (n == 0) {
            return 1;
        }
        // If n != 0 and m == 0, it means we didn't find combination so return 0
        if (m == 0) {
            return 0;
        }

        if (dp[m][n] != null) {
            return dp[m][n];
        }

        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            // We have two conditions:
            // If pick: we can pick and reduce index in both strings.
            // Not pick: no pick and reduce index in m.
            dp[m][n] = getDistinctSubsequences(s1, s2, m - 1, n - 1, dp) + getDistinctSubsequences(s1, s2, m - 1, n, dp);
        } else {
            // Not pick always
            dp[m][n] = getDistinctSubsequences(s1, s2, m - 1, n, dp);
        }

        return dp[m][n];
    }

    private int space(String s1,String s2,int m,int n) {
        int[] prev = new int[n+1];
        int[] curr = new int[n+1];

        prev[0] = 1;
        for(int i=1;i<=m;i++) {
            curr[0]=1;
            for(int j=1;j<=n;j++) {
                //simply copy the recurrence from memoization and make changes here
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    //we have two conditions.
                    //If pick:we can pick and reduce index in both string.
                    //not pick:no pick and reduce index in m.
                    curr[j]= prev[j-1]+ prev[j];
                }else{
                    //not pick always
                    curr[j]= prev[j];
                }
            }
            prev = curr.clone();
        }
        return prev[n];
    }

    // Main method for testing the code
    public static void main(String[] args) {
        DistinctSubsequences solution = new DistinctSubsequences();

        // Test cases
        String s1 = "rabbbit";
        String s2 = "rabbit";
        System.out.println("Number of distinct subsequences: " + solution.numDistinct(s1, s2));  // Output: 3

        s1 = "babgbag";
        s2 = "bag";
        System.out.println("Number of distinct subsequences: " + solution.numDistinct(s1, s2));  // Output: 5
    }
}
