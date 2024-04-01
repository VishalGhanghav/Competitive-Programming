package Problems;

import java.util.LinkedList;
import java.util.Queue;
/*
 Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 
 */
public class NumberOfIslands {
	    public int numIslands(char[][] grid) {
	        int m=grid.length;
	        int n=grid[0].length;
	        int vis[][]=new int[m][n];
	        int cnt=0;

	        for(int i=0;i<m;i++){
	            for(int j=0;j<n;j++){
	                if(grid[i][j]=='1' && vis[i][j]==0){
	                    cnt++;
	                    bfs(i,j,vis,grid);
	                }
	            }
	        }
	        return cnt;

	    }

	    class Pair{
	        int row;
	        int col;

	        Pair(int row,int col){
	            this.row=row;
	            this.col=col;
	        }
	    }
	    public void bfs(int row,int col,int[][] vis,char[][] grid){
	        int m = grid.length; 
	        int n = grid[0].length;
	        Queue<Pair> q=new LinkedList<>();
	        q.add(new Pair(row,col));

	        while(!q.isEmpty()){
	            int ro=q.peek().row;
	            int co=q.peek().col;
	            q.poll();

	            //find all neighbours
	           for(int delrow=-1;delrow<=1;delrow++){
	               for(int delcol=-1;delcol<=1;delcol++){
	                   if((delrow==-1 && delcol==0) || (delrow==1 && delcol==0) ||
	                        (delrow==0 && delcol==-1) || (delrow==0 && delcol==1)){
	                            //If delrow and delcol are valid
	                            int nrow=ro+delrow;
	                            int ncol=co+delcol;
	                            
	                            if(nrow>=0 && nrow<m && ncol>=0 && ncol<n &&
	                                grid[nrow][ncol]=='1' && vis[nrow][ncol]==0){
	                                    System.out.println(nrow+" "+ncol);
	                                    vis[nrow][ncol]=1;
	                                    q.add(new Pair(nrow,ncol));
	                                }

	                        }
	               }
	           }

	        }
	    }
	
}
