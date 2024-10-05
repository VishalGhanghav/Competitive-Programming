package SdeSheetArrays.hard;

import java.util.*;

public class FourSumProblem {

    // Brute force approach: O(n^4) time complexity
    public static List<List<Integer>> fourSumBruteForce(int[] nums, int target) {
        int n = nums.length;
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];
                        if (sum == target) {
                            List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            Collections.sort(temp);
                            set.add(temp);  // Use set to avoid duplicates
                        }
                    }
                }
            }
        }

        return new ArrayList<>(set);
    }

    // Hashing approach: O(n^3) time complexity
    public static List<List<Integer>> fourSumWithHashing(int[] nums, int target) {
        int n = nums.length;
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Set<Long> tempSet = new HashSet<>();
                for (int k = j + 1; k < n; k++) {
                    long sum = (long) nums[i] + nums[j] + nums[k];
                    long fourth = (long) target - sum;

                    // Check if we have seen the number that would complete the quadruplet
                    if (tempSet.contains(fourth)) {
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], (int) fourth);
                        Collections.sort(temp);
                        set.add(temp);  // Use set to avoid duplicates
                    }

                    tempSet.add((long) nums[k]);
                }
            }
        }

        return new ArrayList<>(set);
    }

    // Two-pointer approach: O(n^3) time complexity
    public static List<List<Integer>> fourSumTwoPointer(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);  // Sorting is required for two-pointer approach

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;  // Skip duplicates for the first element

            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;  // Skip duplicates for the second element

                int k = j + 1;
                int l = n - 1;

                while (k < l) {
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum > target) {
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        // Found a quadruplet
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                        res.add(temp);
                        k++;
                        l--;

                        // Skip duplicates for the third element
                        while (k < l && nums[k] == nums[k - 1]) {
                            k++;
                        }

                        // Skip duplicates for the fourth element
                        while (k < l && nums[l] == nums[l + 1]) {
                            l--;
                        }
                    }
                }
            }
        }

        return res;
    }

    // Main method to test all 3 approaches
    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};  // Example input
        int target = 0;  // Example target

        // Brute Force Approach
        System.out.println("Brute Force Approach:");
        List<List<Integer>> resultBruteForce = fourSumBruteForce(nums, target);
        printResult(resultBruteForce);

        // Hashing Approach
        System.out.println("\nHashing Approach:");
        List<List<Integer>> resultHashing = fourSumWithHashing(nums, target);
        printResult(resultHashing);

        // Two-pointer Approach
        System.out.println("\nTwo-pointer Approach:");
        List<List<Integer>> resultTwoPointer = fourSumTwoPointer(nums, target);
        printResult(resultTwoPointer);
    }

    // Utility method to print the results
    public static void printResult(List<List<Integer>> result) {
        for (List<Integer> quadruplet : result) {
            System.out.println(quadruplet);
        }
    }
}
