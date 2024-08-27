package SdeSheetStackQueue.MonotonicStack.NearestGreaterOrSmaller;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElementInOtherArray {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> st = new Stack<>(); // Stack to keep track of elements for which we are finding the next greater
        int n = nums2.length;

        // Map to store the next greater element for each element in nums2
        Map<Integer, Integer> map = new HashMap<>();

        // Traverse nums2 from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements from the stack that are less than or equal to the current element
            while (!st.isEmpty() && st.peek() <= nums2[i]) {
                st.pop();
            }

            // If the stack is empty, no greater element exists, so store -1
            if (st.isEmpty()) {
                map.put(nums2[i], -1);
            } else {
                // Otherwise, the top element of the stack is the next greater element
                map.put(nums2[i], st.peek());
            }
            // Push the current element onto the stack
            st.push(nums2[i]);
        }

        // Prepare the result array for nums1
        int[] ans = new int[nums1.length];
        int k = 0;

        // For each element in nums1, find the corresponding next greater element using the map
        for (int num : nums1) {
            if (map.containsKey(num)) {
                ans[k] = map.get(num);
                k++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        NextGreaterElementInOtherArray nge = new NextGreaterElementInOtherArray();

        // Example input arrays
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};

        // Call the function and get the result
        int[] result = nge.nextGreaterElement(nums1, nums2);

        // Print the result
        System.out.print("Next Greater Elements: ");
        for (int r : result) {
            System.out.print(r + " ");
        }
    }
}
