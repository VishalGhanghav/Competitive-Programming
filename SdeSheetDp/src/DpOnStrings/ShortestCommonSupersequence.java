package SdeSheetDp.src.DpOnStrings;

public class ShortestCommonSupersequence {
    public String shortestCommonSupersequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Step1:Base case is if s1/s2.length==0 set 0 in dp array.
        // But here its not needed as int[][] will have 0 by default

        // Step2:Fill up the dp array using LCS.
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // I have got the filled dp array
        // To get shortest supersequence .
        // Let me attach an image Of how we will move reverse in the dp array to get our
        // result.
        int i = m;
        int j = n;
        // using string builder to reverse string using function later and better time
        // complexity
        StringBuilder res = new StringBuilder();
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                res.append(s1.charAt(i - 1));
                i--;
                j--;
            } else {
                // 2 3
                // 1 1 as 3>1 so go up
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    // Go up means we need to add element from s1.
                    // As that will not be visited again
                    res.append(s1.charAt(i - 1));
                    i--;
                } else {
                    // Move left, add the element from s2
                    res.append(s2.charAt(j - 1));
                    j--;
                }
            }
        }
        // We might have not reached 0,0. So we will go up to there. Either we need to go left or either up.
        // Any one of the below will be executed
        while (i > 0) {
            res.append(s1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            res.append(s2.charAt(j - 1));
            j--;
        }
        // After traversing this we will get the reverse string. So just reverse to get proper result
        return res.reverse().toString();
    }

    // Main method to test the solution
    public static void main(String[] args) {
        ShortestCommonSupersequence sol = new ShortestCommonSupersequence();

        String s1 = "abac";
        String s2 = "cab";

        String scs = sol.shortestCommonSupersequence(s1, s2);
        System.out.println("Shortest Common Supersequence: " + scs);
    }
}
