package SdeSheetDp.src.DpOnStrings;

public class NoOfDeletionsToConvertStringAtoB {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Get the length of the longest common subsequence (LCS)
        int lcsLength = getLcs(word1, word2, m, n, dp);

        // Calculate the minimum number of deletions needed
        // by removing characters not part of the LCS from both strings
        // (m - lcsLength) characters need to be removed from word1
        // (n - lcsLength) characters need to be removed from word2
        int noOfDeletions = (m - lcsLength) + (n - lcsLength);
        return noOfDeletions;
    }

    private int getLcs(String word1, String word2, int m, int n, int[][] dp) {
        // Base case already handled as dp[i][0] and dp[0][j] are initialized to 0

        // Fill the dp array using the LCS logic
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
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
        NoOfDeletionsToConvertStringAtoB sol = new NoOfDeletionsToConvertStringAtoB();

        String word1 = "sea";
        String word2 = "eat";
        //ea=lcs remove s,e

        int minDist = sol.minDistance(word1, word2);
        System.out.println("Minimum number of deletions: " + minDist);
    }
}
