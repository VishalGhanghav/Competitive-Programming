package SdeSheetDp.src.DpOnPartition;

public class MaxSumPartitioning {

    public static void main(String[] args) {
        int[] arr = {1, 15, 7, 9, 2, 5, 10};
        int k = 3;

        MaxSumPartitioning solution = new MaxSumPartitioning();

        // Memoization approach
        Integer[] dpMemo = new Integer[arr.length];
        int maxSumMemo = solution.getMaxSum(0, arr.length, arr, k, dpMemo);
        System.out.println("Max sum using Memoization: " + maxSumMemo);

        // Tabulation approach
        int maxSumTabulation = solution.maxSumAfterPartitioning(arr, k);
        System.out.println("Max sum using Tabulation: " + maxSumTabulation);
    }

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        // tabulation
        int[] dp = new int[n + 1];
        // Base case: if(ind == n) { return 0 }
        dp[n] = 0;
        // In memoization, ind went from 0 -> n, so here we go from n-1 -> 0
        for (int ind = n - 1; ind >= 0; ind--) {
            // Copy the recurrence
            int last = Math.min(n, ind + k);
            // To calculate len
            int len = 0;
            // To calculate max in partition
            int maxInPartition = Integer.MIN_VALUE;
            // To calculate the final answer. At each partition, we get some sum.
            // That needs to be maximized
            int finalAns = Integer.MIN_VALUE;
            for (int j = ind; j < last; j++) {
                len++;
                maxInPartition = Math.max(maxInPartition, arr[j]);
                int sum = len * maxInPartition + dp[j + 1];
                // We need to find the maximum of all the sums
                finalAns = Math.max(finalAns, sum);
            }
            dp[ind] = finalAns;
        }
        return dp[0];
    }

    private int getMaxSum(int ind, int n, int[] arr, int k, Integer[] dp) {
        if (ind == n) {
            return 0;
        }

        if (dp[ind] != null) {
            return dp[ind];
        }

        // I will do front partition and visit for each starting from ind up to k.
        // ind + k should not cross n. So take min of n, ind + k
        int last = Math.min(n, ind + k);
        // To calculate len
        int len = 0;
        // To calculate max in partition
        int maxInPartition = Integer.MIN_VALUE;
        int finalAns = Integer.MIN_VALUE;
        for (int j = ind; j < last; j++) {
            len++;
            maxInPartition = Math.max(maxInPartition, arr[j]);
            int sum = len * maxInPartition + getMaxSum(j + 1, n, arr, k, dp);
            // I need to find the maximum of all the sums
            finalAns = Math.max(finalAns, sum);
        }
        return dp[ind] = finalAns;
    }
}
