package SdeSheetStackQueue.MonotonicStack.NearestGreaterOrSmaller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NearestSmallerElementToLeft {
    static List<Integer> leftSmaller(int n, int a[]) {
        Stack<Integer> st = new Stack<>(); // Stack to keep track of elements for comparison
        List<Integer> ans = new ArrayList<>(); // List to store the results

        // Traverse the array from left to right
        for (int i = 0; i < n; i++) {
            // Pop elements from the stack that are greater than or equal to the current element
            while (!st.isEmpty() && st.peek() >= a[i]) {
                st.pop();
            }

            // If stack is empty, no smaller element to the left, so add -1
            if (st.isEmpty()) {
                ans.add(-1);
            } else {
                // Otherwise, the top element is the nearest smaller element to the left
                ans.add(st.peek());
            }

            // Push the current element onto the stack
            st.push(a[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {1, 6, 4, 10, 2, 5}; // Example array
        int n = a.length;

        // Call the function and get the result
        List<Integer> result = leftSmaller(n, a);

        // Print the result
        System.out.print("Nearest Smaller Elements to the Left: ");
        for (int r : result) {
            System.out.print(r + " ");
        }
    }
}
