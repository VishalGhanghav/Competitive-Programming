package SdeSheetDp.src.DpOnSquares;

import java.util.Stack;

public class MaximalAreaOfRectangle {
    // Function to calculate the maximum rectangular area in a histogram
    public int calculate(int height[]) {
        // Stack to store the indices of the histogram bars
        Stack<Integer> st = new Stack<>();
        int maxArea = 0; // Variable to store the maximum area found

        // Iterate through all bars of the histogram
        for (int i = 0; i <= height.length; i++) {
            // Process the stack if the current bar is shorter than the bar at the top of the stack
            // or if we've reached the end of the array
            while (st.size() > 0 && (i == height.length || height[st.peek()] > height[i])) {
                int prev = height[st.pop()]; // Height of the bar at the top of the stack
                int width; // Width of the rectangle formed by the popped bar

                // If the stack is empty, it means the popped bar was the smallest so far
                if (st.size() == 0)
                    width = i; // Width extends from the start to the current position
                else
                    width = i - st.peek() - 1; // Width is the difference between current index and the new top index

                int area = prev * width; // Calculate the area with the popped bar as the smallest height
                maxArea = Math.max(area, maxArea); // Update maxArea if the current area is larger
            }
            st.push(i); // Push the current index onto the stack
        }
        return maxArea; // Return the maximum area found
    }

    // Main method to test the calculate function
    public static void main(String[] args) {
        MaximalAreaOfRectangle obj = new MaximalAreaOfRectangle();

        // Example test case
        int[] height = {2, 1, 5, 6, 2, 3};
        int maxArea = obj.calculate(height);

        // Output the result
        System.out.println("The maximum rectangular area is: " + maxArea);
    }
}
