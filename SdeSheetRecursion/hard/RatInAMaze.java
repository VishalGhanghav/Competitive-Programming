package SdeSheetRecursion.hard;

import java.util.ArrayList;
import java.util.List;

public class RatInAMaze {

    public ArrayList<String> findPath(int[][] mat) {
        ArrayList<String> res = new ArrayList<>();
        int n = mat.length;
        int[][] vis = new int[n][n];
        //we will be travelling down left right up
        int[] delrow={1,0,0,-1};
        int[] delcol={0,-1,1,0};
        if(mat[0][0]==1){
            getPath(0,0,n,"",mat,res,delrow,delcol,vis);
        }else{
            res.add("-1");
        }
        return res;
    }

    public void getPath(int row, int col, int n, String temp, int[][] mat, List<String> res,
                        int[] delrow, int[] delcol, int[][] vis) {
        // If we have reached the last cell
        if (row == n - 1 && col == n - 1) {
            res.add(temp);
            return;
        }

        // String of possible directions in lexicographical order
        String direction = "DLRU";
        for (int i = 0; i < 4; i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];

            // Check if the new position is within boundaries and can be visited
            if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < n
                    && vis[nrow][ncol] == 0 && mat[nrow][ncol] == 1) {
                vis[row][col] = 1; // Mark as visited
                String newTemp = temp + direction.charAt(i);  // New string for each call
                getPath(nrow, ncol, n, newTemp, mat, res, delrow, delcol, vis);
                vis[row][col] = 0; // Unmark for backtracking
            }
        }
    }

    public static void main(String[] args) {
        RatInAMaze rat = new RatInAMaze();

        // Example maze
        int[][] mat = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}
        };

        ArrayList<String> paths = rat.findPath(mat);
        if (paths.size() == 1 && paths.get(0).equals("-1")) {
            System.out.println("No path found");
        } else {
            System.out.println("Paths found:");
            for (String path : paths) {
                System.out.println(path);
            }
        }
    }
}

