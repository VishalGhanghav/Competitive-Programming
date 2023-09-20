package Problems;


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

