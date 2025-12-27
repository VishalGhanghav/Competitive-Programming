package SdeSheetGraphs.ShortestPath;

import java.util.*;

// Class Name: ShortestPathBinaryMatrix
class ShortestPathBinaryMatrix {

    // Side class kept under main class per instructions
    static class Triplet {
        int dist;
        int row;
        int col;

        Triplet(int dist, int row, int col) {
            this.dist = dist;
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Time Complexity: O(M * N) - Every cell is visited at most once; 8 neighbors checked per cell.
     * Space Complexity: O(M * N) - For the distance array and the BFS queue.
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        // if no 0 at 0,0
        if (grid[0][0] == 1) {
            return -1;
        }
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] == 0 && m == 1)
            return 1;
        if (grid[0][0] == 1)
            return -1;

        Queue<Triplet> q = new LinkedList<>();
        int[][] distance = new int[m][n];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // add dist=1 row=0 col=0
        q.add(new Triplet(1, 0, 0));

        // left lu up ur right dr down ld
        int drow[] = {0, -1, -1, -1, 0, 1, 1, 1};
        int dcol[] = {-1, -1, 0, 1, 1, 1, 0, -1};

        while (!q.isEmpty()) {
            Triplet triplet = q.poll();
            int dist = triplet.dist;
            int row = triplet.row;
            int col = triplet.col;

            // Now check all neighbours for that node
            for (int i = 0; i < 8; i++) {
                int nrow = row + drow[i];
                int ncol = col + dcol[i];

                // If valid node and distance is less than what is currently
                // present at that node
                if (nrow >= 0 && nrow < m && ncol >= 0 && ncol < n
                        && grid[nrow][ncol] == 0 && (dist + 1) < distance[nrow][ncol]) {

                    distance[nrow][ncol] = dist + 1;

                    if (nrow == m - 1 && ncol == n - 1) {
                        return distance[nrow][ncol];
                    }
                    q.add(new Triplet(dist + 1, nrow, ncol));
                }
            }
        }
        // If q is Empty and we didnt reach bottom right .return -1
        return -1;
    }

    public static void main(String[] args) {
        ShortestPathBinaryMatrix solver = new ShortestPathBinaryMatrix();

        // Example grid: 0 is path, 1 is wall
        int[][] grid = {
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        };

        System.out.println("Input Binary Matrix:");
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }

        int result = solver.shortestPathBinaryMatrix(grid);
        System.out.println("\nShortest Path Length (BFS): " + result);
    }
}
