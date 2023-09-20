package arrays1;
/*
 Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.

 

Example 1:


Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
Example 2:


Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 

Constraints:

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-231 <= matrix[i][j] <= 231 - 1

 */
public class SetMatrixZeroes {
/*
 Create new array.copy all elements to that array. put zeroes at row and col places 
 and again copy back
 
 Time: O(mxn)*(m+n)  Space:O(m+n)
 public void setZeroes(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        int[][] arr=new int[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                arr[i][j]=matrix[i][j];
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    for(int k=0;k<n;k++){
                        arr[i][k]=0;
                    }
                    for(int k=0;k<m;k++){
                        arr[k][j]=0;
                    }
                }
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=arr[i][j];
            }
        }
  
    } 
Time:			Space:
O(m*n)*O(m+n)	O(m*n)
i j    k+k
 */
}
