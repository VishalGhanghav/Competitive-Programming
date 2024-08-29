package SdeSheetStackQueue.MonotonicStack.Problems;

import java.util.Stack;

public class MaximalRectangle {

    // Function to find the maximal rectangle area in a binary matrix
    public int maximalRectangle(char[][] matrix) {
        int maxArea = 0;
        int n = matrix[0].length;

        int height[] = new int[n];

        // We are creating a histogram here
        // Basically create a histogram based on each row and then pass it to
        // the histogram function to calculate the max area in that histogram
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            // Calculate for each row separately
            maxArea = Math.max(maxArea, largestRectangleArea(height));
        }
        return maxArea;
    }

    // Function to find the largest rectangle area in a histogram
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int n = heights.length;

        int maxArea = 0;

        // Using Optimal Approach
        // I try to use the pse approach to solve this
        for (int i = 0; i < n; i++) {
            // Whenever we get a pop, we can get the nse, pse, and area for that element
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                int element = st.pop();
                // Its nse will be the current element
                int nse = i;
                // Its pse will be the stack's next element
                // If the stack is empty, pse = -1
                int pse = st.isEmpty() ? -1 : st.peek();
                // Area is calculated for the element that is being popped
                int area = heights[element] * (nse - pse - 1);
                maxArea = Math.max(area, maxArea);
            }
            st.push(i);
        }

        // For remaining elements
        while (!st.isEmpty()) {
            int element = st.pop();
            // They will not have nse, so set to n
            int nse = n;
            // Its pse will be the stack's next element
            // If the stack is empty, pse = -1
            int pse = st.isEmpty() ? -1 : st.peek();
            // Area is calculated for the element that is being popped
            int area = heights[element] * (nse - pse - 1);
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

    // Main method to test the maximalRectangle function
    public static void main(String[] args) {
        MaximalRectangle mr = new MaximalRectangle();
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        int maxRectangle = mr.maximalRectangle(matrix);
        System.out.println("Maximal Rectangle Area: " + maxRectangle);
    }
}
