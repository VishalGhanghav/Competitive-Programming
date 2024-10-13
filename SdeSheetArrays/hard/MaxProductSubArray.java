package SdeSheetArrays.hard;

public class MaxProductSubArray {

    // Brute Force Approach: O(n^3) complexity
    public static int bruteForceMaxProduct(int[] nums) {
        int n = nums.length;
        int maxProduct = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int prod = 1;
                for (int k = i; k <= j; k++) {
                    prod = prod * nums[k];  // Calculate product of subarray from i to j
                }
                maxProduct = Math.max(maxProduct, prod);  // Keep track of maximum product found
            }
        }

        return maxProduct;
    }

    // Better Approach: O(n^2) complexity
    public static int betterMaxProduct(int[] nums) {
        int n = nums.length;
        int maxProduct = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int prod = 1;
            for (int j = i; j < n; j++) {
                prod = prod * nums[j];  // Calculate product of subarray from i to j
                maxProduct = Math.max(maxProduct, prod);  // Update max product
            }
        }

        return maxProduct;
    }

    // Optimal Approach: O(n) complexity using prefix and suffix
    public static int optimalMaxProduct(int[] nums) {
        int n = nums.length;
        int maxProduct = Integer.MIN_VALUE;
        int prefix = 1;
        int suffix = 1;

        for (int i = 0; i < n; i++) {
            // Reset prefix and suffix to 1 if they hit zero
            if (prefix == 0) {
                prefix = 1;
            }
            if (suffix == 0) {
                suffix = 1;
            }

            prefix = prefix * nums[i];           // Calculate prefix product
            suffix = suffix * nums[n - 1 - i];   // Calculate suffix product

            // Compare the current maxProduct with the larger of prefix or suffix at each step
            int maxOfPreSuff = Math.max(prefix, suffix);
            maxProduct = Math.max(maxProduct, maxOfPreSuff);
        }

        return maxProduct;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};  // Example array input

        // Brute Force Approach
        System.out.println("Brute Force Max Product: " + bruteForceMaxProduct(nums));

        // Better Approach
        System.out.println("Better Approach Max Product: " + betterMaxProduct(nums));

        // Optimal Approach
        System.out.println("Optimal Approach Max Product: " + optimalMaxProduct(nums));
    }
}

