package SdeSheetGreedyProblems;

public class JumpGame1 {

    // Function to determine if you can jump to the last index
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxInd = 0;  // Tracks the furthest index we can reach

        // Iterate over each index in the array
        for (int i = 0; i < n; i++) {
            // If the current index is beyond the furthest index we can reach, return false
            if (i > maxInd) {
                return false;
            }
            // Update maxInd to be the furthest we can reach from the current index
            maxInd = Math.max(maxInd, i + nums[i]);

            // If at any point maxInd exceeds or reaches the last index, we can stop early
            if (maxInd >= n - 1) {
                return true;
            }
        }

        return true;  // Return true if we successfully traverse the array
    }

    // Main method to test the canJump function
    public static void main(String[] args) {
        JumpGame1 jumpGame = new JumpGame1();

        // Test case 1: Example where you can jump to the last index
        int[] nums1 = {2, 3, 1, 1, 4};
        boolean result1 = jumpGame.canJump(nums1);
        System.out.println("Can jump (Test case 1): " + result1);  // Expected output: true

        // Test case 2: Example where you cannot jump to the last index
        int[] nums2 = {3, 2, 1, 0, 4};
        boolean result2 = jumpGame.canJump(nums2);
        System.out.println("Can jump (Test case 2): " + result2);  // Expected output: false
    }
}

