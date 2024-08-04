package SdeSheetDp.src.DpOnStrings;

public class EditDistance {

    /*
    Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
    You have the following three operations permitted on a word:
    1. Insert a character
    2. Delete a character
    3. Replace a character

    Example 1:
    Input: word1 = "horse", word2 = "ros"
    Output: 3
    Explanation:
    horse -> rorse (replace 'h' with 'r')
    rorse -> rose (remove 'r')
    rose -> ros (remove 'e')

    Example 2:
    Input: word1 = "intention", word2 = "execution"
    Output: 5
    Explanation:
    intention -> inention (remove 't')
    inention -> enention (replace 'i' with 'e')
    enention -> exention (replace 'n' with 'x')
    exention -> exection (replace 'n' with 'c')
    exection -> execution (insert 'u')

    Constraints:
    0 <= word1.length, word2.length <= 500
    word1 and word2 consist of lowercase English letters.
    */

    public int minDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Integer[][] dp = new Integer[m + 1][n + 1];
        // return getMinDistance(s1, s2, m, n, dp);

        // Tabulation
        int[][] dp = new int[m + 1][n + 1];

        // Base case
        // If s1 is empty, the number of insertions/deletions will be from s2
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // If s2 is empty, the number of insertions/deletions will be from s1
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // We will get insert/delete/replace only when chars in strings are not equal
                    // If insertion, hypothetically we will add one element in s1, so 'i' remains at the same position but j-1
                    // If deletion, we delete so i-1, j remains at the same position
                    // If replace, we will replace the element which is present in s2, so i-1, j-1 as common
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[m][n];
    }

    private int getMinDistance(String s1, String s2, int m, int n, Integer[][] dp) {
        // If s1 becomes empty and there are elements in s2, we will need n operations to convert to s2
        if (m == 0) {
            return n;
        }
        if (n == 0) {
            return m;
        }
        if (dp[m][n] != null) {
            return dp[m][n];
        }

        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return dp[m][n] = getMinDistance(s1, s2, m - 1, n - 1, dp);
        } else {
            // We will get insert/delete/replace only when chars in strings are not equal
            // If insertion, hypothetically we will add one element in s1, so 'i' remains at the same position but j-1
            // If deletion, we delete so i-1, j remains at the same position
            // If replace, we will replace the element which is present in s2, so i-1, j-1 as common
            return dp[m][n] = 1 + Math.min(getMinDistance(s1, s2, m, n - 1, dp),
                    Math.min(getMinDistance(s1, s2, m - 1, n, dp), getMinDistance(s1, s2, m - 1, n - 1, dp)));
        }
    }

    // Main method for testing the code
    public static void main(String[] args) {
        EditDistance solution = new EditDistance();

        // Test cases
        String word1 = "horse";
        String word2 = "ros";
        System.out.println("Minimum number of operations: " + solution.minDistance(word1, word2));  // Output: 3

        word1 = "intention";
        word2 = "execution";
        System.out.println("Minimum number of operations: " + solution.minDistance(word1, word2));  // Output: 5
    }
}
