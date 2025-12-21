package SdeSheetGraphs.DfsBfs;

import java.util.*;



// Class Name: ZeroOneMatrix
class ZeroOneMatrix {

    class Triplet {
        int row;
        int col;
        int dist;

        Triplet(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    // Time Complexity: O(M * N) - Each cell is processed once in the BFS.
    // Space Complexity: O(M * N) - For the distance, visited arrays and the queue.
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int visited[][] = new int[m][n];
        int distance[][] = new int[m][n];
        Queue<Triplet> q = new LinkedList<>();
        //if mat[i][j]=0 then start bfs
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.add(new Triplet(i, j, 0));
                    visited[i][j] = 1;
                }
            }
        }

        bfs(visited, distance, mat, m, n, q);
        return distance;
    }

    public void bfs(int visited[][], int distance[][], int mat[][], int m, int n, Queue<Triplet> q) {

        int delrow[] = { -1, 0, 1, 0 };
        int delcol[] = { 0, 1, 0, -1 };
        while (!q.isEmpty()) {
            Triplet tp = q.poll();
            int row = tp.row;
            int col = tp.col;
            int dist = tp.dist;
            distance[row][col] = dist;
            //now check all 4 neighbours
            for (int i = 0; i < 4; i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];
                //if valid and not visited
                if (nrow < m && nrow >= 0 && ncol < n && ncol >= 0 &&
                        visited[nrow][ncol] != 1) {
                    visited[nrow][ncol] = 1;
                    q.add(new Triplet(nrow, ncol, dist + 1));
                }
            }
        }
    }

    public static void main(String[] args) {
        ZeroOneMatrix solution = new ZeroOneMatrix();

        int[][] mat = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };

        System.out.println("Original Matrix:");
        for (int[] r : mat) System.out.println(Arrays.toString(r));

        int[][] result = solution.updateMatrix(mat);

        System.out.println("\nResultant Distance Matrix:");
        for (int[] r : result) System.out.println(Arrays.toString(r));
    }
}
