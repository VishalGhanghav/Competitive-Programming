package SdeSheetGraphs.DfsBfs;

import java.util.*;

// Class Name: SurroundedRegions
class SurroundedRegions {

    /**
     * Approach: Boundary DFS
     * TC: O(M * N) - Every cell is visited at most once.
     * SC: O(M * N) - Worst case recursion stack depth for a board of all 'O's.
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        int m = board.length;
        int n = board[0].length;
        int visited[][] = new int[m][n];
        int delrow[] = {-1, 0, 1, 0};
        int delcol[] = {0, 1, 0, -1};

        // Traverse first and last rows
        for (int j = 0; j < n; j++) {
            // First row
            if (visited[0][j] == 0 && board[0][j] == 'O') {
                dfs(0, j, visited, board, delrow, delcol);
            }
            // Last row
            if (visited[m - 1][j] == 0 && board[m - 1][j] == 'O') {
                dfs(m - 1, j, visited, board, delrow, delcol);
            }
        }

        // Traverse first and last columns
        for (int i = 0; i < m; i++) {
            // First column
            if (visited[i][0] == 0 && board[i][0] == 'O') {
                dfs(i, 0, visited, board, delrow, delcol);
            }
            // Last column
            if (visited[i][n - 1] == 0 && board[i][n - 1] == 'O') {
                dfs(i, n - 1, visited, board, delrow, delcol);
            }
        }

        // Replace all unvisited 'O's with 'X'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 0 && board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(int row, int col, int visited[][], char[][] board, int delrow[], int delcol[]) {
        visited[row][col] = 1;
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < 4; i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];

            if (nrow >= 0 && nrow < m && ncol >= 0 && ncol < n
                    && visited[nrow][ncol] == 0 && board[nrow][ncol] == 'O') {
                dfs(nrow, ncol, visited, board, delrow, delcol);
            }
        }
    }

    public static void main(String[] args) {
        SurroundedRegions solution = new SurroundedRegions();

        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        System.out.println("Original Board:");
        printBoard(board);

        solution.solve(board);

        System.out.println("\nCaptured Board:");
        printBoard(board);
    }

    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}
