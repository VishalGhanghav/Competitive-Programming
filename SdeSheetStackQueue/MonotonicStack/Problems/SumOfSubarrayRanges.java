package SdeSheetStackQueue.MonotonicStack.Problems;

import java.util.Stack;

public class SumOfSubarrayRanges {
    public static void main(String[] args) {
        // Test array
        int[] test={1,2,3};
        int[] nums = {1, 4, 3, 2, 5};
        SumOfSubarrayRanges obj = new SumOfSubarrayRanges();
        long result = obj.subArrayRanges(nums);
        System.out.println("res1: "+obj.subArrayRanges(test));
        System.out.println("Sum of subarray ranges: " + result);
    }

    /**
     * Calculates the sum of the ranges of all subarrays.
     * The range of a subarray is defined as the difference between its maximum and minimum values.
     *
     * @param nums Array of integers
     * @return Sum of the ranges of all subarrays
     */
    public long subArrayRanges(int[] nums) {
        int n = nums.length;

        // Arrays to store indices of the nearest and previous smaller and greater elements
        int[] nse = nearestSmaller(nums, n);
        int[] pse = previousSmaller(nums, n);
        int[] nge = nearestGreater(nums, n);
        int[] pge = previousGreater(nums, n);

        // Initialize variables to accumulate the sum of maximums and minimums
        long largest = 0, smallest = 0;

        // Calculate sum of subarray maximums
        for (int i = 0; i < n; i++) {
            long right = nge[i] - i;  // Number of elements to the right of the current element that are greater
            long left = i - pge[i];   // Number of elements to the left of the current element that are greater
            largest += left * right * nums[i]; // Multiply the number of subarrays in which nums[i] is the maximum
        }

        // Calculate sum of subarray minimums
        for (int i = 0; i < n; i++) {
            long right = nse[i] - i;  // Number of elements to the right of the current element that are smaller
            long left = i - pse[i];   // Number of elements to the left of the current element that are smaller
            smallest += left * right * nums[i]; // Multiply the number of subarrays in which nums[i] is the minimum
        }

        // The result is the difference between the sum of subarray maximums and minimums
        return largest - smallest;
    }

    /**
     * Finds the index of the nearest greater element to the right for each element in the array.
     *
     * @param arr Array of integers
     * @param n Length of the array
     * @return Array of indices of the nearest greater elements
     */
    private int[] nearestGreater(int[] arr, int n) {
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse the array from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Maintain a stack of indices such that the elements are in decreasing order
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop(); // Pop elements that are less than or equal to the current element
            }
            // If stack is empty, no greater element to the right; otherwise, the top of the stack is the nearest greater element
            ans[i] = st.isEmpty() ? n : st.peek();
            st.push(i); // Push current index onto the stack
        }
        return ans;
    }

    /**
     * Finds the index of the nearest smaller element to the right for each element in the array.
     *
     * @param arr Array of integers
     * @param n Length of the array
     * @return Array of indices of the nearest smaller elements
     */
    private int[] nearestSmaller(int[] arr, int n) {
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse the array from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Maintain a stack of indices such that the elements are in increasing order
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop(); // Pop elements that are greater than or equal to the current element
            }
            // If stack is empty, no smaller element to the right; otherwise, the top of the stack is the nearest smaller element
            ans[i] = st.isEmpty() ? n : st.peek();
            st.push(i); // Push current index onto the stack
        }
        return ans;
    }

    /**
     * Finds the index of the previous greater element to the left for each element in the array.
     *
     * @param arr Array of integers
     * @param n Length of the array
     * @return Array of indices of the previous greater elements
     */
    private int[] previousGreater(int[] arr, int n) {
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse the array from left to right
        for (int i = 0; i < n; i++) {
            // Maintain a stack of indices such that the elements are in decreasing order
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop(); // Pop elements that are less than or equal to the current element
            }
            // If stack is empty, no greater element to the left; otherwise, the top of the stack is the previous greater element
            ans[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i); // Push current index onto the stack
        }
        return ans;
    }

    /**
     * Finds the index of the previous smaller element to the left for each element in the array.
     *
     * @param arr Array of integers
     * @param n Length of the array
     * @return Array of indices of the previous smaller elements
     */
    private int[] previousSmaller(int[] arr, int n) {
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse the array from left to right
        for (int i = 0; i < n; i++) {
            // Maintain a stack of indices such that the elements are in increasing order
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop(); // Pop elements that are greater than or equal to the current element
            }
            // If stack is empty, no smaller element to the left; otherwise, the top of the stack is the previous smaller element
            ans[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i); // Push current index onto the stack
        }
        return ans;
    }
}
