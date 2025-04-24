package SdeSheetBinarySearch.BinarySearchOn2dArray;

public class SearchInSorted2dArray {
    // Brute Force Approach: O(m * n)
    public static boolean searchMatrixBrute(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == target) return true;
            }
        }
        return false;
    }

    // Better Approach: O(m + log n)
    public static boolean searchMatrixBetter(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] <= target && target <= matrix[i][n - 1]) {
                return binarySearch(matrix[i], target);
            }
        }
        return false;
    }

    // Optimal Approach 1 (Flattened Binary Search): O(log(m*n))
    public static boolean searchMatrixOptimal1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int start = 0, end = m * n - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int row = mid / n;
            int col = mid % n;
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) start = mid + 1;
            else end = mid - 1;
        }
        return false;
    }

    // Optimal Approach 2 (Staircase Search): O(m + n)
    public static boolean searchMatrixOptimal2(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] < target) i++;
            else j--;
        }
        return false;
    }

    // Binary search used in Better approach
    private static boolean binarySearch(int[] row, int target) {
        int start = 0, end = row.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (row[mid] == target) return true;
            else if (row[mid] < target) start = mid + 1;
            else end = mid - 1;
        }
        return false;
    }

    // Main method to test all approaches
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 16;

        System.out.println("Brute: " + searchMatrixBrute(matrix, target));
        System.out.println("Better (row + BS): " + searchMatrixBetter(matrix, target));
        System.out.println("Optimal1 (flattened BS): " + searchMatrixOptimal1(matrix, target));
        System.out.println("Optimal2 (staircase): " + searchMatrixOptimal2(matrix, target));
    }
}
