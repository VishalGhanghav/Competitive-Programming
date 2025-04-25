package SdeSheetBinarySearch.BinarySearchOn2dArray;

public class FindPeakElementIn2DArray {
    // Brute Force: O(m * n)
    public static int[] findPeakGridBrute(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int up = (i > 0) ? mat[i - 1][j] : -1;
                int down = (i < m - 1) ? mat[i + 1][j] : -1;
                int left = (j > 0) ? mat[i][j - 1] : -1;
                int right = (j < n - 1) ? mat[i][j + 1] : -1;

                if (mat[i][j] > up && mat[i][j] > down && mat[i][j] > left && mat[i][j] > right) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    // Better: Just find the max element in the entire matrix
    public static int[] findPeakGridBetter(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int maxVal = -1;
        int[] pos = new int[2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] > maxVal) {
                    maxVal = mat[i][j];
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        return pos;
    }

    // Optimal: Binary Search on Columns — O(m * log n)
    public static int[] findPeakGridOptimal(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int start = 0, end = n - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int row = getMaxInColumn(mat, m, mid);

            int left = (mid > 0) ? mat[row][mid - 1] : -1;
            int right = (mid < n - 1) ? mat[row][mid + 1] : -1;

            if (mat[row][mid] > left && mat[row][mid] > right) {
                return new int[]{row, mid};
            } else if (mat[row][mid] < left) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return new int[]{-1, -1};
    }

    private static int getMaxInColumn(int[][] mat, int m, int col) {
        int maxIndex = 0;
        for (int i = 1; i < m; i++) {
            if (mat[i][col] > mat[maxIndex][col]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // ------------------ Main Method ------------------
    public static void main(String[] args) {
        int[][] mat = {
                {10, 8, 10, 10},
                {14, 13, 12, 11},
                {15, 9, 11, 21},
                {16, 17, 19, 20}
        };

        int[] resBrute = findPeakGridBrute(mat);
        System.out.println("Brute Peak at: [" + resBrute[0] + ", " + resBrute[1] + "]");

        int[] resBetter = findPeakGridBetter(mat);
        System.out.println("Better Peak at: [" + resBetter[0] + ", " + resBetter[1] + "]");

        int[] resOptimal = findPeakGridOptimal(mat);
        System.out.println("Optimal Peak at: [" + resOptimal[0] + ", " + resOptimal[1] + "]");
    }
}
