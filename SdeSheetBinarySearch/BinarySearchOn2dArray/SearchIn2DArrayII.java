package SdeSheetBinarySearch.BinarySearchOn2dArray;

public class SearchIn2DArrayII {
    // Brute force: check every element
    public static boolean searchMatrixBrute(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    // Better: apply binary search on each row
    public static boolean searchMatrixBetter(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (binarySearch(matrix[i], target)) {
                return true;
            }
        }
        return false;
    }

    // Optimal: Staircase Search (Start from top-right)
    public static boolean searchMatrixOptimal(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
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
        System.out.println("Better (row BS): " + searchMatrixBetter(matrix, target));
        System.out.println("Optimal (staircase): " + searchMatrixOptimal(matrix, target));
    }
}
