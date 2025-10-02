package SdeSheetDp.src.DpOnPartition;

public class EvaluateExpressionToTrue {
    static final int MOD = 1000000007;

    // Memoization approach
    static long evaluateExpressionWays(String exp, int i, int j, int isTrue, Long[][][] dp) {
        // Base case 1: When the start index is greater than the end index, no ways to evaluate.
        if (i > j) {
            return 0;
        }

        // Base case 2: When the start and end indices are the same.
        if (i == j) {
            //If I want boolean evaluation to be true and exp character is also true return true
            if (isTrue == 1) {
                return exp.charAt(i) == 'T' ? 1 : 0;
            } else {
                return exp.charAt(i) == 'F' ? 1 : 0;
            }
        }

        // Check if the result is already computed
        if (dp[i][j][isTrue] != null) {
            return dp[i][j][isTrue];
        }

        long ways = 0;

        // Iterate through all operators in the current subexpression
        //If isTrue is 1, we will calculate the number of ways that provide the result true
        //and if isTrue is 0, we will calculate the number of ways that give the result false.
        for (int ind = i + 1; ind <= j - 1; ind += 2) {
            long lT = evaluateExpressionWays(exp, i, ind - 1, 1, dp);
            long lF = evaluateExpressionWays(exp, i, ind - 1, 0, dp);
            long rT = evaluateExpressionWays(exp, ind + 1, j, 1, dp);
            long rF = evaluateExpressionWays(exp, ind + 1, j, 0, dp);

            char operator = exp.charAt(ind);

            if (operator == '&') {
                if (isTrue == 1) {
                    ways = (ways + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lF * rF) % MOD) % MOD;
                }
            } else if (operator == '|') {
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD) % MOD;
                }
            } else { // XOR operator
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD + (lT * rT) % MOD) % MOD;
                }
            }
        }

        dp[i][j][isTrue] = ways;
        return ways;
    }

    // Tabulation approach
    static int evaluateExpTabulation(String exp) {
        int n = exp.length();
        long[][][] dp = new long[n][n][2];

        // Initializing the dp array
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= n - 1; j++) {
                if (i > j) continue;
                for (int isTrue = 0; isTrue <= 1; isTrue++) {
                    // Base case when the subexpression is a single character
                    if (i == j) {
                        dp[i][j][isTrue] = (isTrue == 1) ? (exp.charAt(i) == 'T' ? 1 : 0) : (exp.charAt(i) == 'F' ? 1 : 0);
                        continue;
                    }

                    long ways = 0;

                    // Iterate through all operators in the current subexpression
                    for (int ind = i + 1; ind <= j - 1; ind += 2) {
                        long lT = dp[i][ind - 1][1];
                        long lF = dp[i][ind - 1][0];
                        long rT = dp[ind + 1][j][1];
                        long rF = dp[ind + 1][j][0];

                        char operator = exp.charAt(ind);

                        if (operator == '&') {
                            if (isTrue == 1) {
                                ways = (ways + (lT * rT) % MOD) % MOD;
                            } else {
                                ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lF * rF) % MOD) % MOD;
                            }
                        } else if (operator == '|') {
                            if (isTrue == 1) {
                                ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lT * rT) % MOD) % MOD;
                            } else {
                                ways = (ways + (lF * rF) % MOD) % MOD;
                            }
                        } else { // XOR operator
                            if (isTrue == 1) {
                                ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD) % MOD;
                            } else {
                                ways = (ways + (lF * rF) % MOD + (lT * rT) % MOD) % MOD;
                            }
                        }
                    }
                    dp[i][j][isTrue] = ways;
                }
            }
        }
        return (int) dp[0][n - 1][1]; // Return the number of ways to evaluate the entire expression to true
    }

    public static void main(String[] args) {
        String exp = "F|T^F";

        // Memoization approach
        Long[][][] dpMemo = new Long[exp.length()][exp.length()][2];
        int waysMemo = (int) evaluateExpressionWays(exp, 0, exp.length() - 1, 1, dpMemo);
        System.out.println("The total number of ways (Memoization): " + waysMemo);

        // Tabulation approach
        int waysTabulation = evaluateExpTabulation(exp);
        System.out.println("The total number of ways (Tabulation): " + waysTabulation);
    }
}
