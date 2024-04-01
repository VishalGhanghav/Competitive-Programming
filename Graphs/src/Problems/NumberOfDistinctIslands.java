package Problems;
/*
 Given a boolean 2D matrix grid of size n * m. You have to find the number of distinct islands where a group of connected 1s (horizontally or vertically) forms an island. Two islands are considered to be distinct if and only if one island is not equal to another (not rotated or reflected).

Example 1:

Input:
grid[][] = {{1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1}}
Output:
1
Explanation:
grid[][] = {{1, 1, 0, 0, 0}, 
            {1, 1, 0, 0, 0}, 
            {0, 0, 0, 1, 1}, 
            {0, 0, 0, 1, 1}}
Same colored islands are equal.
We have 2 equal islands, so we 
have only 1 distinct island.

Example 2:

Input:
grid[][] = {{1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1},
            {1, 1, 0, 1, 1}}
Output:
3
Explanation:
grid[][] = {{1, 1, 0, 1, 1}, 
            {1, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 1}, 
            {1, 1, 0, 1, 1}}
Same colored islands are equal.
We have 4 islands, but 2 of them
are equal, So we have 3 distinct islands.
 */

import java.util.*;
class NumberOfDistinctIslands {
    private void dfs(int row, int col, int[][] vis, char[][] grid,ArrayList<Pair> al,int row0,int col0) {
      vis[row0][col0] = 1; 
      al.add(new Pair(row0-row,col0-col));
      int m = grid.length; 
      int n = grid[0].length; 
      int drow[]= {-1,0,+1,0};
      int dcol[]= {0,-1,0,+1};
      //neighbours
      for(int i=0;i<4;i++) {
    	  int nrow=drow[i]+row0;
    	  int ncol=dcol[i]+col0;
    	  //if neighbours are valid
    	  if(nrow>=0 && nrow<m && ncol>=0 && ncol<n &&
    			  grid[nrow][col]=='1' && vis[nrow][ncol]==0) {
    		  dfs(row,col,vis,grid,al,nrow,ncol);
    	  }
      }
  }

    // Function to find the number of islands.
    public int numIslands(char[][] grid) {
        int m = grid.length; 
        int n = grid[0].length; 
        int[][] vis = new int[m][n];
        HashSet<ArrayList<Pair>> hs=new HashSet<>();
        for(int row = 0; row < m ; row++) {
            for(int col = 0; col < n ;col++) {
                // if not visited and is a land
            	
                if(vis[row][col] == 0 && grid[row][col] == '1') {
                	ArrayList<Pair> al=new ArrayList<>();
                    dfs(row, col, vis, grid,al,row,col); 
                    hs.add(al);
                }
            }
        }
        return hs.size(); 
    }
    
    public static void main(String[] args)
    {
        char[][] grid =  {
            {'1', '1', '0', '1', '1'},
            {'1', '0', '0', '0', '0'},
            {'0', '0', '0', '1', '1'},
            {'1', '1', '0', '1', '0'}
        };
                
        NumberOfDistinctIslands obj = new NumberOfDistinctIslands();
        System.out.println(obj.numIslands(grid));
    }

}
class Pair {
    int first;
    int second; 
    public Pair(int first, int second) {
        this.first = first; 
        this.second = second; 
    }
}

