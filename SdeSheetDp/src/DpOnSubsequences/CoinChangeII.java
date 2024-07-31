package SdeSheetDp.src.DpOnSubsequences;

public class CoinChangeII {

    public int change(int amount, int[] coins) {
        int n = coins.length;
        Integer[][] dp = new Integer[n + 1][amount + 1];
        // Similar to count number of subsets with given sum.
        // Only difference is here we can have same coin multiple times.
        return getNumberOfCombinations(coins, n, amount, dp);
    }

    private int getNumberOfCombinations(int[] arr, int n, int sum, Integer[][] dp) {
        // If index is 0 and sum > 0, means we didn't find our answer. So, return 0.
        if (n == 0 && sum > 0) {
            return 0;
        }
        // If at any index we get sum = 0, means we found 1 combination.
        if (sum == 0) {
            return 1;
        }

        // Return already computed result to avoid redundant calculations.
        if (dp[n][sum] != null) {
            return dp[n][sum];
        }

        // If the current coin can be included in the combination.
        if (arr[n - 1] <= sum) {
            dp[n][sum] = getNumberOfCombinations(arr, n, sum - arr[n - 1], dp) + getNumberOfCombinations(arr, n - 1, sum, dp);
        } else {
            dp[n][sum] = getNumberOfCombinations(arr, n - 1, sum, dp);
        }

        return dp[n][sum];
    }
    public static void main(String[] args) {
        CoinChangeII solution = new CoinChangeII();

        // Example 1
        int[] coins1 = {1, 2, 5};
        int amount1 = 5;
        System.out.println(solution.change(amount1, coins1)); // Expected output: 4 (combinations: [5], [2, 2, 1], [2, 1, 1, 1], [1, 1, 1, 1, 1])

        // Example 2
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println(solution.change(amount2, coins2)); // Expected output: 0 (no combination possible)

        // Example 3
        int[] coins3 = {10};
        int amount3 = 10;
        System.out.println(solution.change(amount3, coins3)); // Expected output: 1 (combination: [10])
    }
}
