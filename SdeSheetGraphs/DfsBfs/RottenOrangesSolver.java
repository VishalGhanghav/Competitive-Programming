package SdeSheetGraphs.DfsBfs;

import java.util.*;

// Class Name: RottingOrangesSolver
class RottenOrangesSolver {

    static class Tuple {
        int first;
        int second;
        int third;

        Tuple(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    /**
     * Approach: Multi-source BFS
     * TC: O(M * N) - Each cell is visited at most once.
     * SC: O(M * N) - For the visited array and the queue in the worst case.
     */
    public int orangesRotting(int[][] grid) {
        Queue<Tuple> q = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    visited[i][j] = 2;
                    q.add(new Tuple(i, j, 0));
                }
            }
        }

        int time = 0;
        while (!q.isEmpty()) {
            int row = q.peek().first;
            int col = q.peek().second;
            time = q.peek().third;
            q.poll();

            int drow[] = {-1, 0, 1, 0};
            int dcol[] = {0, -1, 0, 1};

            for (int i = 0; i < 4; i++) {
                int nrow = row + drow[i];
                int ncol = col + dcol[i];

                if (nrow >= 0 && nrow < m && ncol < n && ncol >= 0
                        && visited[nrow][ncol] != 2 && grid[nrow][ncol] == 1) {
                    q.add(new Tuple(nrow, ncol, time + 1));
                    visited[nrow][ncol] = 2;
                }
            }
        }

        // If we have any fresh orange left return -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    return -1;
                }
            }
        }
        return time;
    }

    public static void main(String[] args) {
        RottenOrangesSolver solver = new RottenOrangesSolver();

        // Example 1: Standard case
        int[][] grid1 = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println("Grid 1 Result (Expected 4): " + solver.orangesRotting(grid1));

        // Example 2: Impossible to rot all
        int[][] grid2 = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };
        System.out.println("Grid 2 Result (Expected -1): " + solver.orangesRotting(grid2));

        // Example 3: No fresh oranges
        int[][] grid3 = {
                {0, 2, 0}
        };
        System.out.println("Grid 3 Result (Expected 0): " + solver.orangesRotting(grid3));
    }
}
