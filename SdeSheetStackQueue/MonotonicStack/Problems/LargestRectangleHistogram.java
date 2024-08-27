package SdeSheetStackQueue.MonotonicStack.Problems;
import java.util.Stack;

public class LargestRectangleHistogram {

    // Optimal Approach to find the largest rectangle area in a histogram
    public int largestRectangleAreaOptimal(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int n = heights.length;
        int maxArea = 0;

        // Traverse each bar in the histogram
        for (int i = 0; i < n; i++) {
            // Process bars that are greater than or equal to the current bar
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                int element = st.pop(); // Pop the top element
                int nse = i; // The next smaller element index is the current index
                int pse = st.isEmpty() ? -1 : st.peek(); // The previous smaller element index
                int area = heights[element] * (nse - pse - 1); // Calculate area for the popped element
                maxArea = Math.max(area, maxArea); // Update max area if current area is larger
            }
            st.push(i); // Push the current index onto the stack
        }

        // Process remaining elements in the stack
        while (!st.isEmpty()) {
            int element = st.pop();
            int nse = n; // No next smaller element, so set to array length
            int pse = st.isEmpty() ? -1 : st.peek(); // Previous smaller element index
            int area = heights[element] * (nse - pse - 1); // Calculate area for the popped element
            maxArea = Math.max(area, maxArea); // Update max area if current area is larger
        }
        return maxArea;
    }

    // Brute Force Approach to find the largest rectangle area in a histogram
    public int largestRectangleAreaBrute(int[] heights) {
        int n = heights.length;
        int[] nseArray = nearestSmaller(heights, n); // Next Smaller Element for each bar
        int[] pseArray = previousSmaller(heights, n); // Previous Smaller Element for each bar
        int maxArea = 0;

        // Calculate area for each bar using its width and height
        for (int i = 0; i < n; i++) {
            int nse = nseArray[i]; // Get the next smaller element index
            int pse = pseArray[i]; // Get the previous smaller element index
            int area = heights[i] * (nse - pse - 1); // Calculate area for the current bar
            maxArea = Math.max(maxArea, area); // Update max area if current area is larger
        }
        return maxArea;
    }

    // Helper function to find the next smaller element index for each bar
    private int[] nearestSmaller(int[] arr, int n) {
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from right to left to find the nearest smaller element
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            ans[i] = st.isEmpty() ? n : st.peek(); // Set the next smaller element index
            st.push(i);
        }
        return ans;
    }

    // Helper function to find the previous smaller element index for each bar
    private int[] previousSmaller(int[] arr, int n) {
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from left to right to find the previous smaller element
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            ans[i] = st.isEmpty() ? -1 : st.peek(); // Set the previous smaller element index
            st.push(i);
        }
        return ans;
    }

    // Main method to execute both brute force and optimal approaches
    public static void main(String[] args) {
        LargestRectangleHistogram lrh = new LargestRectangleHistogram();
        int[] heights = {2, 1, 5, 6, 2, 3};

        // Call the brute force approach
        int maxAreaBrute = lrh.largestRectangleAreaBrute(heights);
        System.out.println("Largest Rectangle Area (Brute Force): " + maxAreaBrute);

        // Call the optimal approach
        int maxAreaOptimal = lrh.largestRectangleAreaOptimal(heights);
        System.out.println("Largest Rectangle Area (Optimal): " + maxAreaOptimal);
    }
}

