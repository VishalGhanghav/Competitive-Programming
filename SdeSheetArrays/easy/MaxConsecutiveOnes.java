package SdeSheetArrays.easy;

public class MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        int cnt = 0, maxCount = 0; // Initialize counters for current sequence and maximum sequence
        int n = nums.length;

        // Traverse the array to find the maximum number of consecutive 1s
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                cnt++; // Increment current count if a 1 is found
                maxCount = Math.max(maxCount, cnt); // Update maxCount if the current count is greater
            } else {
                cnt = 0; // Reset current count if a 0 is found
            }
        }
        return maxCount; // Return the maximum count of consecutive 1s
    }

    public static void main(String[] args) {
        MaxConsecutiveOnes mco = new MaxConsecutiveOnes();

        // Test case 1
        int[] nums1 = {1, 1, 0, 1, 1, 1};
        System.out.println("Max Consecutive Ones: " + mco.findMaxConsecutiveOnes(nums1)); // Output should be 3

        // Test case 2
        int[] nums2 = {1, 0, 1, 1, 0, 1};
        System.out.println("Max Consecutive Ones: " + mco.findMaxConsecutiveOnes(nums2)); // Output should be 2

        // Test case 3
        int[] nums3 = {0, 0, 0};
        System.out.println("Max Consecutive Ones: " + mco.findMaxConsecutiveOnes(nums3)); // Output should be 0
    }
}

