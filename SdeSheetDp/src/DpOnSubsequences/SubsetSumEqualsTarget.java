package SdeSheetDp.src.DpOnSubsequences;

public class SubsetSumEqualsTarget {
    static Boolean isSubsetSum(int N, int arr[], int sum){
        // sum and index changing so 2d dp
        Boolean[][] dp = new Boolean[N + 1][sum + 1];

        // Call the helper function to determine if a subset sum equals the target
        return getSubsetSum(N, arr, sum, dp);
    }

    public static Boolean getSubsetSum(int n, int[] arr, int sum, Boolean[][] dp) {
        // Base case: if the sum is 0, return true
        if (sum == 0) {
            return true;
        }

        // Base case: if there are no elements left and the sum is not 0, return false
        if (sum > 0 && n == 0) {
            return false;
        }

        // If the value is already computed, return it
        if (dp[n][sum] != null) {
            return dp[n][sum];
        }

        // If the current element can be included in the subset
        if (arr[n - 1] <= sum) {
            // Choose the current element or not choose it
            return dp[n][sum] = getSubsetSum(n - 1, arr, sum - arr[n - 1], dp) || getSubsetSum(n - 1, arr, sum, dp);
        } else {
            // If the current element cannot be included in the subset
            return dp[n][sum] = getSubsetSum(n - 1, arr, sum, dp);
        }
    }

    public static void main(String[] args) {
        // Example usage
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        int N = arr.length;

        // Check if there exists a subset with the given sum
        if (isSubsetSum(N, arr, sum)) {
            System.out.println("Found a subset with the given sum");
        } else {
            System.out.println("No subset with the given sum");
        }
    }
}
