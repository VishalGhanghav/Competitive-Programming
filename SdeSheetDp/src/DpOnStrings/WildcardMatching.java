package SdeSheetDp.src.DpOnStrings;

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // Will be used for memoization
        // Boolean[][] dp = new Boolean[m + 1][n + 1];
        // return isWildcardMatching(s, p, m, n, dp);

        boolean[][] dp = new boolean[m + 1][n + 1];

        // Tabulation
        // Base cases
        // if s and p both exhausted
        dp[0][0] = true;

        // if s exhausted and p not exhausted.
        // for any n>0 ie.j>0
        for (int j = 1; j <= n; j++) {
            boolean flag = true;
            for (int k = 1; k <= j; k++) {
                if (p.charAt(k - 1) != '*') {
                    flag = false;
                    break;
                }
            }
            dp[0][j] = flag;
        }

        // if s has something but p exhausted return false
        for (int i = 1; i <= m; i++) {
            dp[i][0] = false;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // if chars are matching or there is a ?.Then decrement both index
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    // if there is *.It can match from 0 to multiple elements.
                    // we use pick not pick .check notebook for understanding
                    // we take * dont take other half or vice versa
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    // if neither its ? nor matching nor a *.
                    // So the elements are not matching return false
                    dp[i][j] = false;
                }
            }
        }

        return dp[m][n];
    }

    private boolean isWildcardMatching(String s, String p, int m, int n, Boolean[][] dp) {
        // Base case
        // If s exhausted and even p is exhausted then its possible
        if (m == 0 && n == 0) {
            return true;
        }

        // If s exhausted and p not exhausted then
        // check if p has * completely.If even at one place not a *.Its not possible
        if (m == 0 && n > 0) {
            for (int k = 1; k <= n; k++) {
                if (p.charAt(k - 1) != '*') {
                    return false;
                }
            }
            // all are *
            return true;
        }

        // s has something but p exhausted
        if (m > 0 && n == 0) {
            return false;
        }

        if (dp[m][n] != null) {
            return dp[m][n];
        }

        // if chars are matching or there is a ?.Then decrement both index
        if (s.charAt(m - 1) == p.charAt(n - 1) || p.charAt(n - 1) == '?') {
            return dp[m][n] = isWildcardMatching(s, p, m - 1, n - 1, dp);
        }

        // if there is *.It can match from 0 to multiple elements.
        // we use pick not pick .check notebook for understanding
        if (p.charAt(n - 1) == '*') {
            // we take * dont take other half or vice versa
            return dp[m][n] = isWildcardMatching(s, p, m - 1, n, dp) || isWildcardMatching(s, p, m, n - 1, dp);
        }

        // if neither its ? nor matching nor a *.
        // So the elements are not matching return false
        return dp[m][n] = false;
    }

    // Main method for testing the code
    public static void main(String[] args) {
        WildcardMatching solution = new WildcardMatching();

        // Test cases
        String s = "aa";
        String p = "a";
        System.out.println("Is Match: " + solution.isMatch(s, p));  // Output: false

        s = "aa";
        p = "*";
        System.out.println("Is Match: " + solution.isMatch(s, p));  // Output: true

        s = "cb";
        p = "?a";
        System.out.println("Is Match: " + solution.isMatch(s, p));  // Output: false

        s = "adceb";
        p = "*a*b";
        System.out.println("Is Match: " + solution.isMatch(s, p));  // Output: true

        s = "acdcb";
        p = "a*c?b";
        System.out.println("Is Match: " + solution.isMatch(s, p));  // Output: false
    }
}
