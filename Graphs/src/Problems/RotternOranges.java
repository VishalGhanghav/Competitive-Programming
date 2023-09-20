package Problems;

import java.util.LinkedList;
import java.util.Queue;
/*
 You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

 

Example 1:


Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
 */
public class RotternOranges {
	class Solution {
	    class Tuple{
	        int first;
	        int second;
	        int third;
	        Tuple(int first,int second,int third){
	            this.first=first;
	            this.second=second;
	            this.third=third;
	        }
	    }
	    public int orangesRotting(int[][] grid) {
	        int m=grid.length;
	        int n=grid[0].length;

	        int[][] vis=new int[m][n];
	        Queue<Tuple> q=new LinkedList<>();
	        //Add all starting oranges which are rotten in queue
	        //and set visited array
	        for(int i=0;i<m;i++){
	            for(int j=0;j<n;j++){
	                if(grid[i][j]==2){
	                    vis[i][j]=2;
	                    q.add(new Tuple(i,j,0));
	                }else{
	                    vis[i][j]=0;
	                }
	            }
	        }
	        int time=0;
	        while(!q.isEmpty()){
	            int row=q.peek().first;
	            int col=q.peek().second;
	            time=q.peek().third;
	            q.poll();

	            int drow[]={-1,0,1,0};
	            int dcol[]={0,-1,0,1};
	            //Find all neighbour oranges
	            for(int i=0;i<4;i++){
	                int nrow=row+drow[i];
	                int ncol=col+dcol[i];
	                //Valid or not && are you already visited && 
	                //are you a fresh orange(ie not 0 or 2)
	                if(nrow>=0 && nrow<m && ncol>=0 && ncol<n &&
	                   vis[nrow][ncol]!=2 && grid[nrow][ncol]==1 ){
	                       q.add(new Tuple(nrow,ncol,time+1));
	                       vis[nrow][ncol]=2;
	                   }

	            }
	        }
	        //If we have any fresh orange left return -1
	        for(int i=0;i<m;i++){
	            for(int j=0;j<n;j++){
	                if(grid[i][j]==1 && vis[i][j]==0){
	                    return -1;
	                }
	                  
	            }
	        }
	        return time;
	    }
	}
}
