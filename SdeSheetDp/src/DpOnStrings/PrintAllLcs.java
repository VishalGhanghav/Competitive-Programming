package SdeSheetDp.src.DpOnStrings;
import java.util.*;
public class PrintAllLcs {
        // Method to find all longest common subsequences
        public List<String> all_longest_common_subsequences(String s, String t) {
            int m = s.length();
            int n = t.length();

            // Initialize the dp array to store lengths of LCS
            int[][] dp = new int[m + 1][n + 1];

            // Fill the dp array using tabulation
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // If characters match, add 1 to the value from the previous diagonal cell
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    } else {
                        // If characters don't match, take the maximum value from the left or top cell
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            // Use a sorted set to store and sort all LCS
            Set<String> result = new TreeSet<>();

            // Backtrack to find all LCS
            backtrack(dp, s, t, m, n, "", result);

            // Convert the sorted set to a list and return
            return new ArrayList<>(result);
        }

        // Helper method for backtracking to find all LCS
        private void backtrack(int[][] dp, String s, String t, int i, int j, String current, Set<String> result) {
            // Base case: if either string is exhausted, add the current LCS to the result set
            if (i == 0 || j == 0) {
                result.add(new StringBuilder(current).reverse().toString());
                return;
            }

            // If characters match, move diagonally and add the character to the current LCS
            if (s.charAt(i - 1) == t.charAt(j - 1)) {
                backtrack(dp, s, t, i - 1, j - 1, current + s.charAt(i - 1), result);
            } else {
                // If characters don't match, move in the direction of the greater value
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    backtrack(dp, s, t, i - 1, j, current, result);
                } else if (dp[i - 1][j] < dp[i][j - 1]) {
                    backtrack(dp, s, t, i, j - 1, current, result);
                } else {
                    // If values are equal, move in both directions to find all possible LCS
                    backtrack(dp, s, t, i - 1, j, current, result);
                    backtrack(dp, s, t, i, j - 1, current, result);
                }
            }
        }

        // Main method to test the solution
        public static void main(String[] args) {
            PrintAllLcs sol = new PrintAllLcs();

            String s = "abaabaaaaa";
            String t = "aabbaaaaaabb";

            List<String> lcsList = sol.all_longest_common_subsequences(s, t);
            System.out.println("All Longest Common Subsequences:");
            for (String lcs : lcsList) {
                System.out.println(lcs);
            }
        }
}

