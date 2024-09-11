package SdeSheetArrays.medium;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    // Brute Force Approach: O(n^2)
    public int longestConsecutiveBrute(int[] nums) {
        int n = nums.length;
        int longest = Integer.MIN_VALUE;
        if (n == 0) {
            return 0;
        }

        // Brute force approach - checks each element and performs linear search
        for (int i = 0; i < n; i++) {
            int cnt = 1;
            int x = nums[i];
            // Search if element+1 is present further in the array
            while (linearSearch(nums, x + 1)) {
                cnt++;
                x++;
            }
            longest = Math.max(cnt, longest);
        }
        return longest;
    }

    // Better Approach: O(nlogn) using sorting
    public int longestConsecutiveBetter(int[] nums) {
        int n = nums.length;
        int longest = Integer.MIN_VALUE;
        if (n == 0) {
            return 0;
        }

        // Sort the array
        Arrays.sort(nums);

        int cnt = 1, lastSmaller = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] - 1 == lastSmaller) {
                cnt++;
                lastSmaller = nums[i];
            } else if (nums[i] != lastSmaller) {
                cnt = 1;
                lastSmaller = nums[i];
            }
            longest = Math.max(cnt, longest);
        }
        return longest;
    }

    // Optimal Approach: O(n) using HashSet
    public int longestConsecutiveOptimal(int[] nums) {
        int n = nums.length;
        int longest = Integer.MIN_VALUE;
        if (n == 0) {
            return 0;
        }

        // Use a HashSet to eliminate duplicates and allow fast lookups
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int num : set) {
            // Start a sequence only if `num - 1` is not in the set (starting point of a sequence)
            if (!set.contains(num - 1)) {
                int cnt = 1;
                int x = num;
                while (set.contains(x + 1)) {
                    x++;
                    cnt++;
                }
                longest = Math.max(cnt, longest);
            }
        }
        return longest;
    }

    // Helper function for Brute Force to perform linear search
    private boolean linearSearch(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }

    // Main method to test all three approaches
    public static void main(String[] args) {
        LongestConsecutiveSequence obj = new LongestConsecutiveSequence();

        // Example input arrays
        int[] nums1 = {100, 4, 200, 1, 3, 2};   // Expected output: 4 (for the sequence 1, 2, 3, 4)
        int[] nums2 = {0, 0, 1, 2};             // Expected output: 3 (for the sequence 0, 1, 2)
        int[] nums3 = {9, 1, 8, 3, 2, 5};       // Expected output: 2 (for the sequence 1, 2)

        // Test Brute Force Approach
        System.out.println("Brute Force Approach:");
        System.out.println("Test case 1: " + obj.longestConsecutiveBrute(nums1));  // Output: 4
        System.out.println("Test case 2: " + obj.longestConsecutiveBrute(nums2));  // Output: 3
        System.out.println("Test case 3: " + obj.longestConsecutiveBrute(nums3));  // Output: 2

        // Test Better Approach
        System.out.println("\nBetter Approach (Sorting):");
        System.out.println("Test case 1: " + obj.longestConsecutiveBetter(nums1));  // Output: 4
        System.out.println("Test case 2: " + obj.longestConsecutiveBetter(nums2));  // Output: 3
        System.out.println("Test case 3: " + obj.longestConsecutiveBetter(nums3));  // Output: 2

        // Test Optimal Approach
        System.out.println("\nOptimal Approach (HashSet):");
        System.out.println("Test case 1: " + obj.longestConsecutiveOptimal(nums1));  // Output: 4
        System.out.println("Test case 2: " + obj.longestConsecutiveOptimal(nums2));  // Output: 3
        System.out.println("Test case 3: " + obj.longestConsecutiveOptimal(nums3));  // Output: 2
    }
}

