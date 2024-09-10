package SdeSheetArrays.medium;

import java.util.ArrayList;
import java.util.List;

public class RearrangePositiveNegativeSameSize {
    // Brute Force Approach
    public int[] rearrangeArrayBruteForce(int[] nums) {
        // Important: There are an equal number of positive and negative numbers
        int n = nums.length;

        // Create pos and neg arrays
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();

        // Separate positive and negative numbers into different lists
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                pos.add(nums[i]);
            } else {
                neg.add(nums[i]);
            }
        }

        // Place positive and negative numbers alternately in the original array
        for (int i = 0; i < n / 2; i++) {
            nums[2 * i] = pos.get(i);
            nums[2 * i + 1] = neg.get(i);
        }

        return nums;
    }

    // Optimal Approach
    public int[] rearrangeArrayOptimal(int[] nums) {
        int n = nums.length;
        int pos = 0, neg = 1;
        int[] ans = new int[n];

        // Place positive and negative numbers at their proper positions
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                ans[pos] = nums[i];
                pos += 2; // Move to the next position for positive number
            } else {
                ans[neg] = nums[i];
                neg += 2; // Move to the next position for negative number
            }
        }

        return ans;
    }

    // Main method to test both approaches
    public static void main(String[] args) {
        RearrangePositiveNegativeSameSize rearrange = new RearrangePositiveNegativeSameSize();

        // Example input
        int[] nums = {3, 1, -2, -5, 2, -4};

        // Test the brute force approach
        int[] bruteResult = rearrange.rearrangeArrayBruteForce(nums.clone());
        System.out.println("Brute Force Result:");
        for (int num : bruteResult) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Test the optimal approach
        int[] optimalResult = rearrange.rearrangeArrayOptimal(nums.clone());
        System.out.println("Optimal Result:");
        for (int num : optimalResult) {
            System.out.print(num + " ");
        }
    }
}
