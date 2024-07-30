package SdeSheetDp.src.DpOnSubsequences;

class PartitionEqualSubsetSum {
    // Function to check if array can be partitioned into two subsets with equal sum
    public boolean canPartition(int[] arr) {
        int arraySum = 0;
        for (int i = 0; i < arr.length; i++) {
            arraySum += arr[i];
        }
        if (arraySum % 2 == 0) {
            // If the total sum is even, check if there exists a subset with sum equal to half of the total sum
            Boolean[][] dp = new Boolean[arr.length + 1][(arraySum / 2) + 1];
            return findSubsetSum(arr, arr.length, arraySum / 2, dp);
        } else {
            // An array having an odd sum cannot have two subsets with equal sum
            return false;
        }
    }

    // Helper function to find if there's a subset with a given sum
    public boolean findSubsetSum(int[] arr, int n, int sum, Boolean[][] dp) {
        if (sum == 0) {
            return true;
        }
        if (sum > 0 && n == 0) {
            return false;
        }
        // If the value is already computed, return it for better time complexity
        if (dp[n][sum] != null) {
            return dp[n][sum];
        }
        if (arr[n - 1] <= sum) {
            // Choose the current element or not choose it
            return dp[n][sum] = findSubsetSum(arr, n - 1, sum - arr[n - 1], dp) || findSubsetSum(arr, n - 1, sum, dp);
        } else {
            // If the current element cannot be included in the subset
            return dp[n][sum] = findSubsetSum(arr, n - 1, sum, dp);
        }
    }

    public static void main(String[] args) {
        // Example usage
        PartitionEqualSubsetSum subsetSumEqualsTarget = new PartitionEqualSubsetSum();

        int[] arr1 = {1, 5, 11, 5};
        System.out.println("Can partition array {1, 5, 11, 5} into two subsets with equal sum: " + subsetSumEqualsTarget.canPartition(arr1));

        int[] arr2 = {1, 2, 3, 5};
        System.out.println("Can partition array {1, 2, 3, 5} into two subsets with equal sum: " + subsetSumEqualsTarget.canPartition(arr2));
    }
}
