package SdeSheetGraphs.DfsBfs;

import java.util.*;

// Class Name: NumberOfEnclaves
class NumberOfEnclaves {

    // Helper class kept as inner class per instructions
    class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Approach 1: Breadth First Search (BFS)
     * TC: O(M * N) - Every cell is processed at most once.
     * SC: O(M * N) - For the visited array and the queue.
     */
    public int numEnclavesBFS(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] visited = new int[m][n];
        int delrow[] = {0, -1, 0, 1};
        int delcol[] = {-1, 0, 1, 0};
        Queue<Pair> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // first row first col last row last col resp
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    if (board[i][j] == 1) {
                        q.add(new Pair(i, j));
                        visited[i][j] = 1;
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            Pair p = q.poll();
            int row = p.row;
            int col = p.col;
            for (int i = 0; i < 4; i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];
                if (nrow < m && nrow >= 0 && ncol < n && ncol >= 0 &&
                        visited[nrow][ncol] == 0 && board[nrow][ncol] == 1) {
                    q.add(new Pair(nrow, ncol));
                    visited[nrow][ncol] = 1;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // if that element not visited and on board it is 1
                if (visited[i][j] == 0 && board[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Approach 2: Depth First Search (DFS)
     * TC: O(M * N) - Every cell visited once.
     * SC: O(M * N) - For visited array and recursion stack.
     */
    public int numEnclavesDFS(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] visited = new int[m][n];
        int delrow[] = {0, -1, 0, 1};
        int delcol[] = {-1, 0, 1, 0};

        // traverse first and last row
        for (int j = 0; j < n; j++) {
            // first row.if not visited call dfs
            if (visited[0][j] == 0 && board[0][j] == 1) {
                dfs(0, j, visited, board, delrow, delcol);
            }
            // last row .if not visited call dfs
            if (visited[m - 1][j] == 0 && board[m - 1][j] == 1) {
                dfs(m - 1, j, visited, board, delrow, delcol);
            }
        }

        for (int i = 0; i < m; i++) {
            // first col.if not visited call dfs
            if (visited[i][0] == 0 && board[i][0] == 1) {
                dfs(i, 0, visited, board, delrow, delcol);
            }
            // last col .if not visited call dfs
            if (visited[i][n - 1] == 0 && board[i][n - 1] == 1) {
                dfs(i, n - 1, visited, board, delrow, delcol);
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 0 && board[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(int row, int col, int[][] visited, int[][] board, int delrow[], int delcol[]) {
        visited[row][col] = 1;
        int m = board.length;
        int n = board[0].length;
        // check for top bottom left right
        for (int i = 0; i < 4; i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];
            if (nrow < m && nrow >= 0 && ncol < n && ncol >= 0 &&
                    visited[nrow][ncol] == 0 && board[nrow][ncol] == 1) {
                dfs(nrow, ncol, visited, board, delrow, delcol);
            }
        }
    }

    public static void main(String[] args) {
        NumberOfEnclaves solver = new NumberOfEnclaves();
        int[][] board = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };

        System.out.println("Board:");
        for(int[] row : board) System.out.println(Arrays.toString(row));

        System.out.println("Enclaves count (BFS): " + solver.numEnclavesBFS(board));
        System.out.println("Enclaves count (DFS): " + solver.numEnclavesDFS(board));
    }
}
