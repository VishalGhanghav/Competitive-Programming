package SdeSheetRecursion.medium;

import java.util.*;

public class PrintSubsets {

    // ===============================================
    // Approach: Backtracking Recursion
    // TC: O(2^n) ? each element can be picked or not
    // SC: O(n) recursion depth + output list
    // ===============================================
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        int n = nums.length;
        return printSubsets(nums, n, subset, res, 0);
    }

    private static List<List<Integer>> printSubsets(int[] nums, int n, List<Integer> subset,
                                                    List<List<Integer>> res, int ind) {

        if (ind == n) {
            res.add(new ArrayList<>(subset));
            return res;
        }

        // pick
        subset.add(nums[ind]);
        printSubsets(nums, n, subset, res, ind + 1);

        // not pick
        subset.remove(subset.size() - 1);
        printSubsets(nums, n, subset, res, ind + 1);

        return res;
    }

    // ===============================================
    // Main Method: Test cases
    // ===============================================
    public static void main(String[] args) {
        int[][] testCases = {
                {1, 2},
                {1, 2, 3}
        };

        for (int[] nums : testCases) {
            System.out.println("Input: " + Arrays.toString(nums));
            System.out.println("Subsets: " + subsets(nums));
            System.out.println();
        }
    }
}
