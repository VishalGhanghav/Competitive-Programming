package SdeSheetStackQueue.MonotonicStack.NearestGreaterOrSmaller;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NumberOfNGEToRIght {
    public static int[] countNGEs(int arr[], int queries, int indices[]) {
        Stack<Integer> st = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[queries];

        // Traverse the array from right to left
        for (int i = arr.length - 1; i >= 0; i--) {
            // Pop elements from the stack that are less than or equal to the current element
            while (!st.isEmpty() && st.peek() < arr[i]) {
                st.pop();
            }

            // If the stack is empty, no elements are greater to the right
            if (st.isEmpty()) {
                map.put(i, 0);
            } else {
                // The count of greater elements is the number of elements currently in the stack
                map.put(i, st.size());
            }

            // Push the current element onto the stack
            st.push(arr[i]);
        }

        // Retrieve the count for each query index
        int k = 0;
        for (int ind : indices) {
            ans[k++] = map.get(ind);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 7, 5, 8, 10, 6}; // Example array
        int[] indices = {0, 5}; // Example indices for queries
        int queries = indices.length; // Number of queries

        // Call the function and get the result
        int[] result = countNGEs(arr, queries, indices);//output : for 3:4,7,8,10
        //for 8: 10 ie.4,1 is output

        // Print the result
        System.out.print("Count of Next Greater Elements to the Right: ");
        for (int r : result) {
            System.out.print(r + " ");
        }
    }
}
