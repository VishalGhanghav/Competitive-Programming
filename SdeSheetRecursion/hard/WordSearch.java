package SdeSheetRecursion.hard;
/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.



Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false


Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.


Follow up: Could you use search pruning to make your solution faster with a larger board?
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int[] delrow = {-1, 0, 1, 0};
        int[] delcol = {0, -1, 0, 1};
        // visited array so that whichever character we visited before is not visited again
        int[][] vis = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // if I found the first letter of word, we can recursively traverse
                if (board[i][j] == word.charAt(0)) {
                    // mark 1st element visited, so it's not revisited in future
                    vis[i][j] = 1;
                    // if we get true once, that's it, don't check further
                    // passed 1 as wordIndex as 0 is already marked
                    if (solve(i, j, 1, word, m, n, vis, delrow, delcol, board)) {
                        return true;
                    }
                    // if it's not the word we want, backtrack the visited
                    vis[i][j] = 0;
                }
            }
        }
        return false;
    }

    public boolean solve(int row, int col, int wordIndex, String word, int m, int n,
                         int[][] vis, int[] delrow, int[] delcol, char[][] board) {
        // if I reach last wordIndex, it means complete word traversed, so return true
        if (word.length() == wordIndex) {
            return true;
        }
        // I need to check 4 directions for the starting word
        for (int i = 0; i < 4; i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];
            // see if nrow and ncol fall in the matrix.
            // if yes, check if char is same as per word index and not visited
            if (nrow >= 0 && nrow < m && ncol >= 0 && ncol < n
                    && board[nrow][ncol] == word.charAt(wordIndex) && vis[nrow][ncol] == 0) {
                vis[nrow][ncol] = 1;
                // if we get true once, return true and don't backtrack
                if (solve(nrow, ncol, wordIndex + 1, word, m, n, vis, delrow, delcol, board)) {
                    return true;
                }
                // if I traverse for a particular word (ABCCED), and don't find after (ABC)
                // need to backtrack the visited matrix
                vis[nrow][ncol] = 0;
            }
        }
        // if visited all directions for current char you started for, and didn't find
        // required word, return false
        return false;
    }

    public static void main(String[] args) {
        WordSearch ws = new WordSearch();
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";

        System.out.println("Word: " + word1 + " -> " + ws.exist(board, word1)); // should return true
        System.out.println("Word: " + word2 + " -> " + ws.exist(board, word2)); // should return true
        System.out.println("Word: " + word3 + " -> " + ws.exist(board, word3)); // should return false
    }
}

