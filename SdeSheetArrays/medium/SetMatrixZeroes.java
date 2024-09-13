package SdeSheetArrays.medium;
public class SetMatrixZeroes {

    public static void main(String[] args) {
        // Example matrix
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        // Create an object of SetMatrixZeroes to call non-static methods
        SetMatrixZeroes obj = new SetMatrixZeroes();

        // Brute force approach
        int[][] matrixBrute = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        obj.setZeroesBrute(matrixBrute);
        System.out.println("Brute force result:");
        obj.printMatrix(matrixBrute);

        // Better approach
        int[][] matrixBetter = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        obj.setZeroesBetter(matrixBetter);
        System.out.println("\nBetter approach result:");
        obj.printMatrix(matrixBetter);

        // Optimal approach
        int[][] matrixOptimal = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        obj.setZeroesOptimal(matrixOptimal);
        System.out.println("\nOptimal approach result:");
        obj.printMatrix(matrixOptimal);
    }

    // Brute Force: Visit each element and mark the corresponding row and column with a temporary value.
    public void setZeroesBrute(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Mark rows and columns
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    markRow(i, matrix, n);
                    markCol(j, matrix, m);
                }
            }
        }

        // Replace marked values (-11) with 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -11) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private void markRow(int i, int[][] matrix, int n) {
        for (int j = 0; j < n; j++) {
            if (matrix[i][j] != 0) {
                matrix[i][j] = -11;
            }
        }
    }

    private void markCol(int j, int[][] matrix, int m) {
        for (int i = 0; i < m; i++) {
            if (matrix[i][j] != 0) {
                matrix[i][j] = -11;
            }
        }
    }

    // Better Approach: Use extra space to keep track of rows and columns that should be zeroed.
    public void setZeroesBetter(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] row = new int[m];
        int[] col = new int[n];

        // Mark rows and columns that contain 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        // Set elements to 0 based on marked rows and columns
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] == 1 || col[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // Optimal Approach: Use the first row and column as markers to minimize space usage.
    public void setZeroesOptimal(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int col0 = 1;

        // Mark first row and first column if any zero is found
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    if (j != 0) {
                        matrix[0][j] = 0;
                    } else {
                        col0 = 0;
                    }
                }
            }
        }

        // Traverse the rest of the matrix and set elements to 0 based on markers in the first row/column
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Set first row to 0 if necessary
        if (matrix[0][0] == 0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Set first column to 0 if necessary
        if (col0 == 0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
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

