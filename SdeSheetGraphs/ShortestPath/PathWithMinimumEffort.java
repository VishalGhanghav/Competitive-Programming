package SdeSheetGraphs.ShortestPath;

import java.util.*;

// Class Name: PathWithMinimumEffort
class PathWithMinimumEffort {

    // Side class kept under main class per instructions
    static class Triplet {
        int diff;
        int row;
        int col;

        Triplet(int diff, int row, int col) {
            this.diff = diff;
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Time Complexity: O(E * log V) where E is M*N*4 and V is M*N
     * Space Complexity: O(M * N) for distance array and PriorityQueue
     */
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        // I want to create a minHeap.x=already present y=new incoming.if positive it goes above
        PriorityQueue<Triplet> q = new PriorityQueue<>((x, y) -> x.diff - y.diff);
        q.add(new Triplet(0, 0, 0));

        // to travel 4 dirn we create two arrays
        int delrow[] = {-1, 0, 1, 0};
        int delcol[] = {0, 1, 0, -1};
        int[][] dist = new int[m][n];
        for (int[] arr : dist) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        // Follow djkstra
        dist[0][0] = 0;
        while (!q.isEmpty()) {
            Triplet t = q.poll();
            int diff = t.diff;
            int row = t.row;
            int col = t.col;

            // I will store result when my row and col reach destination and not its neighbour
            if (row == m - 1 && col == n - 1) {
                return diff;
            }

            // travel all 4 neighbours
            for (int i = 0; i < 4; i++) {
                int nrow = delrow[i] + row;
                int ncol = delcol[i] + col;

                // check if my nrow  and ncol is valid
                if (nrow >= 0 && nrow < m && ncol >= 0 && ncol < n) {
                    // Question: A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
                    // find the new effort.(max of current diff and nrow-row diff)
                    int newEffort = Math.max(Math.abs(heights[row][col] - heights[nrow][ncol]), diff);

                    // Question: Return the minimum effort required to travel from the top-left cell to the bottom-right cell
                    // Now whatever newEffort is found .find smallest by comparing with dist array
                    if (newEffort < dist[nrow][ncol]) {
                        dist[nrow][ncol] = newEffort;
                        q.add(new Triplet(newEffort, nrow, ncol));
                    }
                }
            }
        }

        // if unreachable
        return 0;
    }

    public static void main(String[] args) {
        PathWithMinimumEffort solver = new PathWithMinimumEffort();

        int[][] heights = {
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        };

        System.out.println("Heights Grid:");
        for (int[] row : heights) System.out.println(Arrays.toString(row));

        int result = solver.minimumEffortPath(heights);
        System.out.println("\nMinimum Effort Path: " + result);
    }
}
