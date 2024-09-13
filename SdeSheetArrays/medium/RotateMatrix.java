package SdeSheetArrays.medium;

public class RotateMatrix {

    public static void main(String[] args) {
        // Example matrix
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // Create an object of RotateMatrix to call non-static methods
        RotateMatrix obj = new RotateMatrix();

        // Brute force approach
        int[][] matrixBrute = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        obj.rotateBrute(matrixBrute);
        System.out.println("Brute force result:");
        obj.printMatrix(matrixBrute);

        // Optimal approach
        int[][] matrixOptimal = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        obj.rotate(matrixOptimal);
        System.out.println("\nOptimal approach result:");
        obj.printMatrix(matrixOptimal);
    }

    // Brute Force Approach: Use a temporary matrix and copy rotated values
    public void rotateBrute(int[][] matrix) {
        int n = matrix.length;
        int[][] ans = new int[n][n];

        // Create rotated matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[j][n - 1 - i] = matrix[i][j];
            }
        }

        // Copy back to original matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = ans[i][j];
            }
        }
    }

    // Optimal Approach: Rotate the matrix in place by first transposing it and then reversing each row
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Transpose the matrix (swap i,j with j,i)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(matrix, i, j);
            }
        }

        // Reverse each row
        for (int i = 0; i < n; i++) {
            reverse(matrix[i]);
        }
    }

    // Swap elements in the matrix during transposition
    private void swap(int[][] matrix, int i, int j) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }

    // Reverse a single row
    private void reverse(int[] arr) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // Utility function to print the matrix
    public void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}

