package SdeSheetArrays.medium;

import java.util.Arrays;

public class NextPermutation {

    // Function to find the next permutation of the given array
    public void nextPermutation(int[] nums) {
        int n = nums.length;

        // Step 1: Find the breakpoint or the point where we get the longest prefix match
        // (where nums[i] < nums[i+1] while traversing from right to left)
        int ind = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                ind = i;  // Breakpoint found
                break;
            }
        }

        // If no such point is found, it means the array is in descending order
        // So, we simply reverse the entire array to get the smallest permutation
        if (ind == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        // Step 2: Find the closest greater element than nums[ind] from the back
        // Swap it with nums[ind] to move to the next permutation
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > nums[ind]) {
                swap(nums, i, ind);  // Swap the elements
                break;
            }
        }

        // Step 3: Reverse the elements after the index 'ind' to get the next permutation
        reverse(nums, ind + 1, n - 1);
    }

    // Helper function to swap two elements in the array
    private void swap(int[] arr, int start, int end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }

    // Helper function to reverse a sub-array from index 'start' to 'end'
    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    // Main method to test the next permutation function
    public static void main(String[] args) {
        NextPermutation obj = new NextPermutation();

        // Test case 1
        int[] nums1 = {1, 2, 3};
        System.out.println("Original array: " + Arrays.toString(nums1));
        obj.nextPermutation(nums1);
        System.out.println("Next permutation: " + Arrays.toString(nums1));

        // Test case 2
        int[] nums2 = {3, 2, 1};
        System.out.println("\nOriginal array: " + Arrays.toString(nums2));
        obj.nextPermutation(nums2);
        System.out.println("Next permutation: " + Arrays.toString(nums2));

        // Test case 3
        int[] nums3 = {1, 1, 5};
        System.out.println("\nOriginal array: " + Arrays.toString(nums3));
        obj.nextPermutation(nums3);
        System.out.println("Next permutation: " + Arrays.toString(nums3));

        // Test case 4
        int[] nums4 = {1, 3, 2};
        System.out.println("\nOriginal array: " + Arrays.toString(nums4));
        obj.nextPermutation(nums4);
        System.out.println("Next permutation: " + Arrays.toString(nums4));
    }
}
