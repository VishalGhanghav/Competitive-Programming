package arrays1;
/*
 You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

 

Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
Example 2:


Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000
 */
public class RotateMatrix {
	 public void rotate(int[][] matrix) {
	        /*
	        
	        Brute:Time O(n^2)
	        Space:O(n^2)Extra rotated matrix
	        int m=matrix.length;
	        int n=matrix[0].length;
	        int[][] rotated=new int[m][n];

	        for(int i=0;i<m;i++){
	            for(int j=0;j<n;j++){
	                rotated[j][m-1-i]=matrix[i][j];
	            }
	        }
	         for(int i=0;i<m;i++){
	            for(int j=0;j<n;j++){
	               System.out.print(rotated[i][j]+" ");
	            }
	            System.out.println();
	        }*/
	        /*Optimal:Constant space*/
	        int m=matrix.length;
	        for (int i = 0; i < matrix.length; i++) {
	            for (int j = i; j < matrix[0].length; j++) {
	                int temp=matrix[i][j];
	                matrix[i][j]=matrix[j][i];
	                matrix[j][i]=temp;
	            }
	        }
	        for(int i=0;i<m;i++){
	            reverseRow(matrix[i]);
	        }
	        
	    }

	    public void reverseRow(int[] arr){
	        //reverse array
	        for(int i=0,j=arr.length-1;i<j;i++,j--){
	            //swap arr[i] and arr[j]
	            int temp=arr[i];
	            arr[i]=arr[j];
	            arr[j]=temp;
	        }

	    }
}
