package com.Backtracking;

public class Sudoku {
	public static void main(String[] args) {

		   char[][] board= {
		                    {'9', '5', '7', '.', '1', '3', '.', '8', '4'},
		                    {'4', '8', '3', '.', '5', '7', '1', '.', '6'},
		                    {'.', '1', '2', '.', '4', '9', '5', '3', '7'},
		                    {'1', '7', '.', '3', '.', '4', '9', '.', '2'},
		                    {'5', '.', '4', '9', '7', '.', '3', '6', '.'},
		                    {'3', '.', '9', '5', '.', '8', '7', '.', '1'},
		                    {'8', '4', '5', '7', '9', '.', '6', '1', '3'},
		                    {'.', '9', '1', '.', '3', '6', '.', '7', '5'},
		                    {'7', '.', '6', '1', '8', '5', '4', '.', '9'}
		                    };
		    solveSudoku(board);

		    for (int i = 0; i < 9; i++) {
		      for (int j = 0; j < 9; j++)
		        System.out.print(board[i][j] + " ");
		      System.out.println();
		    }
		  }
	public static void solveSudoku(char[][] board) {
        solve(board);
    }

    public static boolean solve(char[][] board){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                //If we find empty box then try to fill it
                if(board[i][j]=='.'){
                    //we can fill 1-9 chars
                    for(char c='1';c<='9';c++){
                        //can we fill char 1->9
                        if(isValid(board,i,j,c)){
                            board[i][j]=c;
                            //Again the recursion goes ,again i loop complete matrix
                            //Again i check for empty box in matrix
                            if(solve(board)==true){
                                return true;
                            }else{
                                //Backtrack
                                board[i][j]='.';
                            }
                        }
                    }
                    //If i am not able to fill any char 1-9,I will return false
                    return false;
                }
            }
        }
        //finally we will return true as we completely filled the matrix
        return true;
    }

    public static boolean isValid(char[][] board,int row,int col,char ch){
        for(int i=0;i<9;i++){
            //if present in row
            if(board[row][i]==ch){
                return false;
            }
            //if present in col
            if(board[i][col]==ch){
                return false;
            }
            //if present in 3x3 matrix
            if(board[3*(row/3)+i/3][3*(col/3)+i%3]==ch){
                return false;
            }
           
        }
        //If all elements checked and that element is present nowhere return true
         return true;
    }
}
