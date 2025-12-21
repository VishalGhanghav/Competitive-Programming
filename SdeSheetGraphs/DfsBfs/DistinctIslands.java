package SdeSheetGraphs.DfsBfs;

import java.util.*;

// Class Name: DistinctIslands
class DistinctIslands {

    // Side class kept under main class per instructions
    class Pair {
        int row;
        int col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Time Complexity: O(M * N * log(Length of Island)) - Every cell visited once; HashSet operations.
     * Space Complexity: O(M * N) - To store the visited array and the shapes in the HashSet.
     */
    int countDistinctIslands(int[][] grid) {
        // Your Code here
        HashSet<ArrayList<String>> set = new HashSet<>();
        int m = grid.length;
        int n = grid[0].length;
        int visited[][] = new int[m][n];
        int delrow[] = {0, 1, 0, -1};
        int delcol[] = {1, 0, -1, 0};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    // ArrayList of pair is not working as it its trying to consider pair as object
                    // and hascodes are not matching
                    ArrayList<String> shape = new ArrayList<>();
                    dfs(i, j, grid, visited, i, j, delrow, delcol, shape, m, n);
                    set.add(shape);
                }
            }
        }
        return set.size();
    }

    public void dfs(int row0, int col0, int[][] grid, int[][] visited, int row, int col, int[] delrow,
                    int delcol[], ArrayList<String> shape, int m, int n) {
        visited[row][col] = 1;
        // row0 is my base
        shape.add(toString(row - row0, col - col0));
        for (int i = 0; i < 4; i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];
            if (nrow < m && nrow >= 0 && ncol < n && ncol >= 0 &&
                    visited[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {

                dfs(row0, col0, grid, visited, nrow, ncol, delrow, delcol, shape, m, n);
            }
        }
    }

    private String toString(int r, int c) {
        return Integer.toString(r) + " " + Integer.toString(c);
    }

    public static void main(String[] args) {
        DistinctIslands solver = new DistinctIslands();

        int[][] grid = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {1, 1, 0, 1, 1}
        };

        System.out.println("Grid Input:");
        for (int[] row : grid) System.out.println(Arrays.toString(row));

        int result = solver.countDistinctIslands(grid);
        System.out.println("\nNumber of Distinct Islands: " + result);
    }
}
