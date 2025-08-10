package SdeSheetTwoPointerSliding.medium;

public class MaximumPointsFromCards {
    // Brute: TC: O(N*K), SC: O(1)
    // Try all combinations of picking from left and right
    public static int maxScoreBrute(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int maxSum = 0;
        // Pick i from left, k-i from right
        for (int leftCount = 0; leftCount <= k; leftCount++) {
            int sum = 0;
            // Take from left
            for (int i = 0; i < leftCount; i++) {
                sum += cardPoints[i];
            }
            // Take from right
            for (int i = 0; i < k - leftCount; i++) {
                sum += cardPoints[n - 1 - i];
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    // Optimal: TC: O(K), SC: O(1)
    // Sliding window approach
    public static int maxScoreOptimal(int[] cardPoints, int k) {
        // Approach:
        // Simple: we will first find sum of first k elements (left sum) and not consider last elements.
        // Then remove one from start and pick from end, checking maximum sum each time.

        int lsum = 0;
        for (int i = 0; i < k; i++) {
            lsum += cardPoints[i];
        }
        // Initially let's say lsum is maxSum
        int maxSum = lsum;
        int rsum = 0;
        int n = cardPoints.length;
        int rind = n - 1;
        // Now remove one element from start and pick one from end
        for (int i = k - 1; i >= 0; i--) {
            lsum = lsum - cardPoints[i];
            rsum = rsum + cardPoints[rind];
            rind--;
            maxSum = Math.max(maxSum, lsum + rsum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] cards = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;
        System.out.println("Brute: " + maxScoreBrute(cards, k));     // Expected: 12
        System.out.println("Optimal: " + maxScoreOptimal(cards, k)); // Expected: 12
    }
}
