package SdeSheetArrays.easy;

public class FindMissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length;

        // Optimal approach: Using XOR to find the missing number
        int xor1 = 0, xor2 = 0;

        // Compute XOR of all array elements
        for (int i = 0; i < n; i++) {
            xor2 = xor2 ^ nums[i];
            xor1 = xor1 ^ (i + 1); // Compute XOR of all numbers from 1 to n
        }

        // XOR of xor1 and xor2 gives the missing number
        return xor1 ^ xor2;
    }

    public int missingNumberBrute(int[] nums) {
        int n = nums.length;

        // Brute force approach: Using sum of first n natural numbers
        int sumOfNumbers = (n * (n + 1)) / 2;
        int arraySum = 0;

        // Compute sum of elements in the array
        for (int i = 0; i < n; i++) {
            arraySum += nums[i];
        }

        // Difference gives the missing number
        return sumOfNumbers - arraySum;
    }

    public static void main(String[] args) {
        FindMissingNumber finder = new FindMissingNumber();

        int[] nums = {3, 0, 1};

        // Run and print the result using the optimal approach
        System.out.println("Missing Number (Optimal Approach): " + finder.missingNumber(nums));

        // Run and print the result using the brute force approach
        System.out.println("Missing Number (Brute Force Approach): " + finder.missingNumberBrute(nums));
    }
}
