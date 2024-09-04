package SdeSheetArrays.easy;

public class MoveZeroes {

    // Brute Force Approach
    public void moveZeroesBrute(int[] nums) {
        int i = 0;
        int n = nums.length;
        // Step 1: Move all non-zero elements to the front
        for (int j = 0; j < n; j++) {
            if (nums[j] != 0) {
                nums[i] = nums[j];
                i++;
            }
        }
        // Step 2: Fill the remaining positions with zeros
        for (int k = i; k < n; k++) {
            nums[k] = 0;
        }
    }

    // Optimal Approach
    public void moveZeroesOptimal(int[] nums) {
        int j = -1; // This will hold the position of the first zero
        // Step 1: Find the first zero in the array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                j = i;
                break;
            }
        }
        // If there are no zeros, return
        if (j == -1) {
            return;
        }

        // Step 2: Swap non-zero elements with zero positions
        for (int i = j + 1; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        MoveZeroes mz = new MoveZeroes();

        // Example array
        int[] nums = {0, 1, 0, 3, 12};

        System.out.println("Original Array:");
        printArray(nums);

        // Brute Force Approach
        int[] numsBrute = nums.clone(); // Clone array to maintain the original state
        mz.moveZeroesBrute(numsBrute);
        System.out.println("\nArray after Brute Force moveZeroes:");
        printArray(numsBrute);

        // Optimal Approach
        int[] numsOptimal = nums.clone(); // Clone array to maintain the original state
        mz.moveZeroesOptimal(numsOptimal);
        System.out.println("\nArray after Optimal moveZeroes:");
        printArray(numsOptimal);
    }

    // Helper method to print the array
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
