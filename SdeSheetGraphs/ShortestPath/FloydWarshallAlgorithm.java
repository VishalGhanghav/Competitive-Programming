package SdeSheetGraphs.ShortestPath;

import java.util.*;

// Class Name: FloydWarshallAlgorithm
class FloydWarshallAlgorithm {

    /**
     * Approach 1: Matrix Input (Direct Implementation)
     * TC: O(V^3) - Triple nested loop over the number of vertices.
     * SC: O(1) - Updating the matrix in-place (O(V^2) if counting input matrix storage).
     */
    public void shortest_distance(int[][] matrix) {
        // Code here
        int m = matrix.length;
        // If -1 i will set it to max_value
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = (int) 1e9;
                }
            }
        }

        // For each node in graph. I will be traversing whole matrix
        // For each node
        for (int via = 0; via < m; via++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    // matrix[i][j] will be min of current value in matrix and path to the destination from src
                    matrix[i][j] = Math.min(matrix[i][j],
                            matrix[i][via] + matrix[via][j]);
                }
            }
        }

        // What if there is a negative cycle
        for (int i = 0; i < m; i++) {
            if (matrix[i][i] < 0) {
                System.out.println("Graph contains negative cycle");
            }
        }

        // Resetting to -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1e9) {
                    matrix[i][j] = -1;
                }
            }
        }
    }

    /**
     * Approach 2: Edge List Input (Create Matrix)
     * TC: O(V^3 + E) - E to build matrix, V^3 for algorithm.
     * SC: O(V^2) - To store the created adjacency matrix.
     */
    public int[][] shortest_distance_from_edges(int V, int[][] edges) {
        int[][] matrix = new int[V][V];

        // Initialize matrix: 0 for diagonal, -1 (to be converted to 1e9) for others
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) matrix[i][j] = 0;
                else matrix[i][j] = -1;
            }
        }

        // Fill matrix with edge weights
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            matrix[u][v] = wt;
        }

        // Use the original logic
        shortest_distance(matrix);
        return matrix;
    }

    public static void main(String[] args) {
        FloydWarshallAlgorithm solver = new FloydWarshallAlgorithm();

        // Example 1: Matrix Input
        int[][] matrix = {
                {0, 1, 43},
                {1, 0, 6},
                {-1, -1, 0}
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        solver.shortest_distance(matrix);

        System.out.println("\nAll-Pairs Shortest Paths (Approach 1):");
        printMatrix(matrix);

        // Example 2: Edge List Input
        int V = 4;
        int[][] edges = {
                {0, 1, 2}, {1, 0, 1}, {1, 2, 3}, {3, 0, 3}, {3, 1, 5}, {2, 3, 2}
        };

        int[][] resultMatrix = solver.shortest_distance_from_edges(V, edges);
        System.out.println("\nAll-Pairs Shortest Paths (Approach 2 - Edge List):");
        printMatrix(resultMatrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
