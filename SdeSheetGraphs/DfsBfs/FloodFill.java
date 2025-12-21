package SdeSheetGraphs.DfsBfs;

import java.util.Arrays;

class FloodFill {

    /**
     * Approach: Depth First Search (DFS)
     * TC: O(M * N) - Each pixel is visited at most once.
     * SC: O(M * N) - Worst case recursion stack depth.
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int startColor = image[sr][sc];
        // we will make changes in this array
        int ans[][] = image;
        int[] delrow = {-1, 0, +1, 0};
        int[] delcol = {0, +1, 0, -1};
        dfs(sr, sc, color, ans, image, startColor, delrow, delcol);
        return ans;
    }

    public void dfs(int row, int col, int color, int[][] ans, int[][] image, int startColor,
                    int[] delrow, int[] delcol) {
        ans[row][col] = color;
        int m = image.length;
        int n = image[0].length;
        // Now we need to check all 4 sides
        for (int i = 0; i < 4; i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];
            // check if valid
            if (nrow < m && nrow >= 0 && ncol < n && ncol >= 0
                    && image[nrow][ncol] == startColor && ans[nrow][ncol] != color) {
                dfs(nrow, ncol, color, ans, image, startColor, delrow, delcol);
            }
        }
    }

    public static void main(String[] args) {
        FloodFill solution = new FloodFill();

        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        int sr = 1, sc = 1, newColor = 2;

        System.out.println("Original Image:");
        for (int[] row : image) {
            System.out.println(Arrays.toString(row));
        }

        int[][] result = solution.floodFill(image, sr, sc, newColor);

        System.out.println("\nImage after Flood Fill:");
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}