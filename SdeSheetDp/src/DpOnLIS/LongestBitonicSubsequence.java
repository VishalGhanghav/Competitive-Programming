package SdeSheetDp.src.DpOnLIS;

public class LongestBitonicSubsequence {
    public static int LongestBitonicSequence(int n, int[] arr) {
        // Initialize DP arrays for increasing and decreasing subsequences
        int[] dp1 = new int[n]; // dp1[i] will hold the length of the longest increasing subsequence ending at index i
        int[] dp2 = new int[n]; // dp2[i] will hold the length of the longest decreasing subsequence starting at index i

        // Fill dp1 for increasing subsequences
        for (int ind = 0; ind < n; ind++) {
            dp1[ind] = 1;
            for (int prev = 0; prev < ind; prev++) {
                if (arr[ind] > arr[prev] && 1 + dp1[prev] > dp1[ind]) {
                    dp1[ind] = 1 + dp1[prev];
                }
            }
        }

        // Fill dp2 for decreasing subsequences from the right
        for (int ind = n - 1; ind >= 0; ind--) {
            dp2[ind] = 1;
            for (int prev = n - 1; prev > ind; prev--) {
                if (arr[ind] > arr[prev] && 1 + dp2[prev] > dp2[ind]) {
                    dp2[ind] = 1 + dp2[prev];
                }
            }
        }

        // Calculate the maximum length of the bitonic subsequence
        int lbsLength = 0;
        for (int ind = 0; ind < n; ind++) {
            int res = dp1[ind] + dp2[ind] - 1; // Subtract 1 to avoid counting the peak twice
            lbsLength = Math.max(lbsLength, res);
        }
        return lbsLength;
    }

    public static void main(String[] args) {
        // Test cases for the strictly increasing or decreasing valid
        int[] arr1 = {1, 2, 5, 3, 2};
        System.out.println("Longest Bitonic Subsequence Length: " + LongestBitonicSequence(arr1.length, arr1)); // Expected output: 5

        int[] arr2 = {1, 11, 2, 10, 4, 5, 2, 1};
        System.out.println("Longest Bitonic Subsequence Length: " + LongestBitonicSequence(arr2.length, arr2)); // Expected output: 6

        int[] arr3 = {5, 6, 7};
        System.out.println("Longest Bitonic Subsequence Length: " + LongestBitonicSequence(arr3.length, arr3)); // Expected output: 3 (valid bitonic sequence is itself)
    }
}
