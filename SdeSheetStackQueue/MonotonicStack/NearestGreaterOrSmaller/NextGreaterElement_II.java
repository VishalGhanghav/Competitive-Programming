package SdeSheetStackQueue.MonotonicStack.NearestGreaterOrSmaller;

import java.util.Stack;

public class NextGreaterElement_II {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> st = new Stack<>(); // Stack to store elements temporarily for comparison
        int n = nums.length;
        int[] nge = new int[n]; // Array to store the next greater elements for nums

        // Loop through the array twice to handle the circular nature
        for (int i = 2 * n - 1; i >= 0; i--) {
            // Pop elements from the stack that are less than or equal to the current element
            while (!st.isEmpty() && st.peek() <= nums[i % n]) {
                st.pop();
            }

            // Only calculate the next greater element for the original array indices
            if (i < n) {
                if (st.isEmpty()) {
                    nge[i] = -1; // No greater element found
                } else {
                    nge[i] = st.peek(); // The top element is the next greater
                }
            }

            // Push the current element onto the stack
            st.push(nums[i % n]);
        }
        return nge;
    }

    public static void main(String[] args) {
        NextGreaterElement_II nge = new NextGreaterElement_II();

        // Example input array
        int[] nums = {2,10,12,1,11};

        // Call the function and get the result
        int[] result = nge.nextGreaterElements(nums);

        // Print the result
        System.out.print("Next Greater Elements: ");
        for (int r : result) {
            System.out.print(r + " ");
        }
    }
}
