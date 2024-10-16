package SdeSheetGreedyProblems;

public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        // Brute: Recursion
        // Better: Memoization
        // Optimal: DP
        // Even more Optimal: Greedy
        // I will be using range rather than count
        int n = s.length();
        int min = 0, max = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                min += 1;
                max += 1;
            } else if (s.charAt(i) == ')') {
                min -= 1;
                max -= 1;
            } else {
                min -= 1;
                max += 1;
            }

            // Now min can go to -1. So set it back to 0
            if (min < 0) {
                min = 0;
            }
            // If max goes negative, it's invalid
            if (max < 0) {
                return false;
            }
        }
        // If min is 0 at the end, it's valid
        return min == 0;
    }

    // Recursion approach
    public boolean checkValidStringRecursion(String s, int index, int openCount) {
        if (openCount < 0) return false;  // Invalid state
        if (index == s.length()) return openCount == 0;  // Valid if no open parenthesis left

        char c = s.charAt(index);
        if (c == '(') {
            return checkValidStringRecursion(s, index + 1, openCount + 1);
        } else if (c == ')') {
            return checkValidStringRecursion(s, index + 1, openCount - 1);
        } else {  // If it's '*', consider all cases
            return checkValidStringRecursion(s, index + 1, openCount + 1) ||  // As '('
                    checkValidStringRecursion(s, index + 1, openCount - 1) ||  // As ')'
                    checkValidStringRecursion(s, index + 1, openCount);        // As ''
        }
    }

    // Memoization approach
    public boolean checkValidStringMemo(String s) {
        // Create a memoization table initialized with -1 (unvisited states)
        int[][] memo = new int[s.length()][s.length() * 2 + 1];  // Table size [index][openCount shifted]
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;  // -1 means this state has not been computed yet
            }
        }
        // Start the recursive helper function with index = 0 and openCount = 0
        return checkValidStringMemoHelper(s, 0, 0, memo);
    }

    private boolean checkValidStringMemoHelper(String s, int index, int openCount, int[][] memo) {
        // Base case: If the openCount is negative, it's an invalid state
        if (openCount < 0) return false;

        // If we've processed all characters, return true if openCount is 0 (valid), otherwise false
        if (index == s.length()) return openCount == 0;

        // Memoization check: if this state has been computed, return the stored result
        if (memo[index][openCount + s.length()] != -1) {
            // Return true if the memoized result is 1 (valid), otherwise false (invalid)
            return memo[index][openCount + s.length()] == 1;
        }

        char c = s.charAt(index);
        boolean result;

        // Recursively check the different cases based on the current character
        if (c == '(') {
            // If it's an open parenthesis '(', increment openCount and move to the next character
            result = checkValidStringMemoHelper(s, index + 1, openCount + 1, memo);
        } else if (c == ')') {
            // If it's a closing parenthesis ')', decrement openCount and move to the next character
            result = checkValidStringMemoHelper(s, index + 1, openCount - 1, memo);
        } else {
            // If it's '*', it can be '(', ')' or empty, so explore all possibilities
            result = checkValidStringMemoHelper(s, index + 1, openCount + 1, memo) ||  // Treat '*' as '('
                    checkValidStringMemoHelper(s, index + 1, openCount - 1, memo) ||  // Treat '*' as ')'
                    checkValidStringMemoHelper(s, index + 1, openCount, memo);        // Treat '*' as empty
        }

        // Memoize the result for this state:
        // memo[index][openCount + s.length()] stores the result for the current index and openCount
        // We shift openCount by s.length() to ensure it's non-negative in the table
        memo[index][openCount + s.length()] = result ? 1 : 0;  // Store 1 if result is true, otherwise 0

        // Return the computed result (whether the substring starting from 'index' is valid)
        return result;
    }


    // DP approach
    public boolean checkValidStringDP(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            for (int j = 0; j <= n; j++) {
                if (c == '(') {
                    if (j > 0) dp[i][j] = dp[i - 1][j - 1];  // Open new parenthesis
                } else if (c == ')') {
                    if (j < n) dp[i][j] = dp[i - 1][j + 1];  // Close one parenthesis
                } else {
                    dp[i][j] = dp[i - 1][j];  // '*' treated as empty
                    if (j > 0) dp[i][j] |= dp[i - 1][j - 1];  // '*' treated as '('
                    if (j < n) dp[i][j] |= dp[i - 1][j + 1];  // '*' treated as ')'
                }
            }
        }
        return dp[n][0];  // Check if valid at the end
    }

    public static void main(String[] args) {
        ValidParenthesisString vsc = new ValidParenthesisString();

        // Test case
        String testString = "(*))";

        // Running all approaches
        System.out.println("Greedy: " + vsc.checkValidString(testString)); // Greedy approach
        System.out.println("Recursion: " + vsc.checkValidStringRecursion(testString, 0, 0)); // Recursion
        System.out.println("Memoization: " + vsc.checkValidStringMemo(testString)); // Memoization
        System.out.println("DP: " + vsc.checkValidStringDP(testString)); // DP approach
    }

}
