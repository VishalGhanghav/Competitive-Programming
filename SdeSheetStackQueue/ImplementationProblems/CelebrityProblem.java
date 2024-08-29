package SdeSheetStackQueue.ImplementationProblems;

public class CelebrityProblem {

    public int celebrity(int mat[][]) {
        int m = mat.length;
        int n = mat[0].length;
        int top = 0, down = n - 1;

        while (top < down) {
            // If top knows down, then top cannot be the celebrity
            if (mat[top][down] == 1) {
                top++;
            } else if (mat[down][top] == 1) {
                // If down knows top, then down cannot be the celebrity
                down--;
            } else {
                // Both don't know each other (mat[top][down] and mat[down][top] == 0)
                // Both cannot be celebrities, so we move both pointers
                top++;
                down--;
            }
        }

        // After completing the loop, top and down should point at the same position
        if (top > down) {
            return -1; // No celebrity found
        }

        // Verify if the person at index 'top' is actually a celebrity
        for (int i = 0; i < n; i++) {
            if (i == top) {
                continue; // Skip checking the diagonal element
            }
            // Check if all column elements in row 'top' are 0 and all row elements in column 'top' are 1
            if (!(mat[top][i] == 0 && mat[i][top] == 1)) {
                return -1; // Not a celebrity
            }
        }

        // If the loop completes without returning -1, we have found our celebrity at index 'top'
        return top;
    }

    public static void main(String[] args) {
        CelebrityProblem cp = new CelebrityProblem();

        // Example 1
        int[][] mat1 = {
                {0, 1, 0},
                {0, 0, 0},
                {0, 1, 0}
        };
        System.out.println("Celebrity index: " + cp.celebrity(mat1)); // Output: 1

        // Example 2
        int[][] mat2 = {
                {0, 1},
                {1, 0}
        };
        System.out.println("Celebrity index: " + cp.celebrity(mat2)); // Output: -1

        // Example 3
        int[][] mat3 = {
                {0, 1, 1},
                {0, 0, 0},
                {0, 1, 0}
        };
        System.out.println("Celebrity index: " + cp.celebrity(mat3)); // Output: 1
    }
}
