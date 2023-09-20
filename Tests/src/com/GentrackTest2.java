package com;
/*
 You are given an m x n integer matrix matrix with the following two properties:
Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.
You must write a solution in O(log(m * n)) time complexity.
 
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3 Output: true
please solve this question
 */
public class GentrackTest2 {
	public static void main(String args[]) {
		int[][] matrix= {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
		int target=12;
		Boolean op=getTarget( target, matrix);
		System.out.println(op);	
		}
/*
 1 3 5 7
 10 11 16 20
 23 30 34 60
 */
		private static Boolean getTarget(int target, int[][] matrix) {
			int rows=matrix.length;
			int cols=matrix[0].length;
			System.out.println(cols);
			int i=0,j=cols-1;
			while(i>=0 && i<rows && j>0 && j<=cols) {
				if(matrix[i][j]>target) {
					System.out.println(matrix[i][j]);
					j--;
				}else if(matrix[i][j]==target){
					return true;
				}else if(matrix[i][j]<target){
					
					i++;
				}
			}
			
			return false;
		}
	
}
