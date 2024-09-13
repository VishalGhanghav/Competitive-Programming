package SdeSheetArrays.medium;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public static void main(String[] args) {
        // Test case 1: Standard matrix
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        // Test case 2: Single row matrix
        int[][] matrix2 = {
                {1, 2, 3, 4}
        };
        // Test case 3: Single column matrix
        int[][] matrix3 = {
                {1},
                {2},
                {3},
                {4}
        };
        // Test case 4: Empty matrix
        int[][] matrix4 = {};

        SpiralMatrix obj = new SpiralMatrix();

        // Run and print results for different test cases
        System.out.println("Spiral order for matrix 1: " + obj.spiralOrder(matrix1));
        System.out.println("Spiral order for matrix 2: " + obj.spiralOrder(matrix2));
        System.out.println("Spiral order for matrix 3: " + obj.spiralOrder(matrix3));
        System.out.println("Spiral order for matrix 4: " + obj.spiralOrder(matrix4));
    }

    // Function to return the elements of a matrix in spiral order
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0) return ans; // Edge case: Empty matrix

        int m = matrix.length;
        int n = matrix[0].length;
        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;

        // Traverse the matrix in a spiral order
        while (top <= bottom && left <= right) {
            // Traverse from left to right along the top row
            for (int i = left; i <= right; i++) {
                ans.add(matrix[top][i]);
            }
            top++; // Move the top boundary down

            // Traverse from top to bottom along the right column
            for (int i = top; i <= bottom; i++) {
                ans.add(matrix[i][right]);
            }
            right--; // Move the right boundary to the left

            // Traverse from right to left along the bottom row, if within valid row bounds
            if (top <= bottom) { // Prevent extra traversal when top surpasses bottom
                for (int i = right; i >= left; i--) {
                    ans.add(matrix[bottom][i]);
                }
                bottom--; // Move the bottom boundary up
            }

            // Traverse from bottom to top along the left column, if within valid column bounds
            if (left <= right) { // Prevent extra traversal when left surpasses right
                for (int i = bottom; i >= top; i--) {
                    ans.add(matrix[i][left]);
                }
                left++; // Move the left boundary to the right
            }
        }
        return ans;
    }
}

