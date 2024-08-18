package SdeSheetDp.src.DpOnPartition;

public class PalindromePartitioning {
    public int minCut(String s) {
        int n = s.length();
        // memoization
        // Integer[] dp = new Integer[n + 1];
        // // Our code is also doing a partition at the end. So final result -1
        // return getMinCuts(0, n, s, dp) - 1;

        // tabulation
        int[] dp = new int[n + 1];
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            int minCost = Integer.MAX_VALUE;
            // I will move j for each index and check if palindrome is possible
            for (int j = i; j < n; j++) {
                // I don't need to store temp string as it will always be from i to j index
                // if I had used temp string: temp = temp + s.charAt(j)
                if (isPalindrome(i, j, s)) {
                    int cost = 1 + dp[j + 1];
                    minCost = Math.min(cost, minCost);
                }
            }
            dp[i] = minCost;
        }
        return dp[0] - 1;
    }

    private int getMinCuts(int i, int n, String s, Integer[] dp) {
        if (i == n) {
            return 0;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        int minCost = Integer.MAX_VALUE;
        // I will move j for each index and check if palindrome is possible
        for (int j = i; j < n; j++) {
            // I don't need to store temp string as it will always be from i to j index
            // if I had used temp string: temp = temp + s.charAt(j)
            if (isPalindrome(i, j, s)) {
                int cost = 1 + getMinCuts(j + 1, n, s, dp);
                minCost = Math.min(cost, minCost);
            }
        }
        return dp[i] = minCost;
    }

    private boolean isPalindrome(int i, int j, String s) {
        while (i < j) {
            // at any moment char at i not equal to char at j. return false
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning obj = new PalindromePartitioning();

        // Example test case
        String s = "aab";
        int result = obj.minCut(s);

        // Output the result
        System.out.println("The minimum cuts needed for a palindrome partitioning are: " + result);
    }
}
