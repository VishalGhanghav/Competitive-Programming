package SdeSheetStackQueue.MonotonicStack.Problems;

public class TrappingRainwater {

    public int trap(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1, lmax = 0, rmax = 0, total = 0;

        while (left < right) {
            // if left building is smaller than right, deal with left side
            if (height[left] <= height[right]) {
                // Check if there is someone greater than height[left] on the left
                if (height[left] < lmax) {
                    // Water can be trapped here
                    total += lmax - height[left];
                } else {
                    // No one greater than height[left] on the left, so height[left] is the new lmax
                    lmax = height[left];
                }
                left++;
            } else {
                // We are dealing with the right side
                // Check if there is someone greater than height[right] on the right
                if (height[right] < rmax) {
                    // Water can be trapped here
                    total += rmax - height[right];
                } else {
                    // No one greater than height[right] on the right, so height[right] is the new rmax
                    rmax = height[right];
                }
                right--;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        // Example array where the function will calculate trapped water
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        // Create an instance of the class to call the trap method
        TrappingRainwater solution = new TrappingRainwater();

        // Output the result
        System.out.println("The amount of trapped water is: " + solution.trap(height));

        System.out.println("The amount of trapped water (prefixSuffixTrap)is: " + solution.prefixSuffixTrap(height));
    }


    static int prefixSuffixTrap(int[] arr) {
        int n = arr.length;
        int prefix[] = new int[n];
        int suffix[] = new int[n];
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = Math.max(prefix[i - 1], arr[i]);
        }
        suffix[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], arr[i]);
        }
        int waterTrapped = 0;
        for (int i = 0; i < n; i++) {
            waterTrapped += Math.min(prefix[i], suffix[i]) - arr[i];
        }
        return waterTrapped;
    }

}
