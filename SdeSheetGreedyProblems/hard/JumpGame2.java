package SdeSheetGreedyProblems.hard;

public class JumpGame2 {

    // Method to calculate the minimum number of jumps to reach the end of the array
    public int jump(int[] nums) {
        // Initialize jumps count and two pointers (l and r) to define the current window
        int jumps = 0, l = 0, r = 0;
        int n = nums.length;

        // Loop until the right pointer reaches the end of the array
        while (r < n - 1) {
            // farthest keeps track of the farthest point we can reach within the current window
            int farthest = 0;

            // Iterate from the left to the right boundary of the current window
            for (int i = l; i <= r; i++) {
                // Update farthest with the maximum distance we can reach from current position i
                farthest = Math.max(i + nums[i], farthest);
            }

            // Move the window: left becomes right+1, right becomes farthest
            l = r + 1;
            r = farthest;
            jumps += 1;  // Increment jump count after processing the window
        }
        return jumps;  // Return the total number of jumps
    }

    // Main method for testing the jump function
    public static void main(String[] args) {
        JumpGame2 game = new JumpGame2();

        // Test case: minimum jumps to reach the end of the array
        int[] nums = {2, 3, 1, 1, 4};  // Example array
        int result = game.jump(nums);

        // Output the result
        System.out.println("Minimum jumps required: " + result);
    }
}
