package SdeSheetRecursion.hard;
/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.



Example 1:


Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:




Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    public boolean solve(char[][] board){
        // Check for all elements in the board.
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    // If '.' is found, try all 9 numbers and see if any fits
                    for(char c = '1'; c <= '9'; c++){
                        if(isValid(i, j, c, board)){
                            board[i][j] = c;
                            // If valid, check further more on board
                            // If at any point we receive true, always return true
                            if(solve(board)){
                                return true;
                            }else{
                                // If not valid, backtrack by removing the element
                                board[i][j] = '.';
                            }
                        }
                    }
                    // If we check all 9 numbers and don't find any valid, return false
                    return false;
                }
            }
        }
        // If we reach the end of the board and don't get false, return true
        return true;
    }

    public boolean isValid(int row, int col, char ch, char[][] board){
        for(int i = 0; i < 9; i++){
            // Check row
            if(board[row][i] == ch){
                return false;
            }
            // Check column
            if(board[i][col] == ch){
                return false;
            }
            // Check 3x3 sub-matrix
            if(board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == ch){
                return false;
            }
        }
        // If no matching element is found, it is valid
        return true;
    }

    public static void main(String[] args) {
        SudokuSolver solution = new SudokuSolver();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solution.solveSudoku(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
