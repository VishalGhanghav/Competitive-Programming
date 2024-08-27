package SdeSheetStackQueue.MonotonicStack.Problems;

import java.util.Stack;

public class SumOfSubarrayMinimums {

    public int sumSubarrayMins(int[] arr) {
        long total = 0;
        int n = arr.length;
        int mod = (int) 1e9 + 7;

        // Get the next smaller element for each element in the array
        int[] nse = getNextSmallerArray(arr, n);

        // Get the previous smaller or equal element for each element in the array
        int[] psee = getPrevSmallerOrEqual(arr, n);

        // Calculate the contribution of each element as the minimum of subarrays
        for (int i = 0; i < n; i++) {
            int elmtOnLeft = i - psee[i];  // Number of elements on the left
            int elmtOnRight = nse[i] - i;  // Number of elements on the right

            // Calculate contribution and add to total
            total = (total + (elmtOnLeft * elmtOnRight * 1L * arr[i]) % mod) % mod;
        }
        return (int) total;
    }

    // Function to get the index of the next smaller element for each element in the array
    private int[] getNextSmallerArray(int[] arr, int n) {
        int[] nse = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse the array from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements from the stack that are greater than or equal to the current element
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            // If stack is empty, there's no smaller element, so we set the index to n
            nse[i] = st.isEmpty() ? n : st.peek();

            // Push the current index to the stack
            st.push(i);
        }
        return nse;
    }

    // Function to get the index of the previous smaller or equal element for each element in the array
    private int[] getPrevSmallerOrEqual(int[] arr, int n) {
        int[] psee = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse the array from left to right
        for (int i = 0; i < n; i++) {
            // Pop elements from the stack that are strictly greater than the current element
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }

            // If stack is empty, there's no smaller or equal element, so we set the index to -1
            psee[i] = st.isEmpty() ? -1 : st.peek();

            // Push the current index to the stack
            st.push(i);
        }
        return psee;
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        SumOfSubarrayMinimums obj = new SumOfSubarrayMinimums();

        int[] arr = {3, 1, 2, 4};
        int result = obj.sumSubarrayMins(arr);

        System.out.println("Sum of subarray minimums: " + result);  // Expected output: 17
    }
}

