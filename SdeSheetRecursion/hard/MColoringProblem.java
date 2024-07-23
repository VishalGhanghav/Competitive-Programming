package SdeSheetRecursion.hard;

public class MColoringProblem {
    public static void main(String[] args) {
        // Example graph represented by an adjacency matrix
        boolean graph[][] = {
                {false, true, true, true},
                {true, false, true, false},
                {true, true, false, true},
                {true, false, true, false}
        };

        int m = 3; // Number of colors
        int n = graph.length; // Number of vertices

        MColoringProblem coloring = new MColoringProblem();
        if (coloring.graphColoring(graph, m, n)) {
            System.out.println("Solution exists");
        } else {
            System.out.println("Solution does not exist");
        }
    }

    public boolean graphColoring(boolean graph[][], int m, int n) {
        // Store colors for all vertices
        int colors[] = new int[n];
        for (int i = 0; i < n; i++) {
            colors[i] = -1;
        }
        // Start coloring from the first node
        if (solve(graph, colors, m, n, 0)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean solve(boolean graph[][], int[] colors, int m, int n, int node) {
        // If all vertices are colored
        if (node == n) {
            return true;
        }
        // Try for all the colors
        for (int c = 1; c <= m; c++) {
            if (canBeColored(graph, n, colors, node, c)) {
                colors[node] = c;
                if (solve(graph, colors, m, n, node + 1) == true) {
                    return true;
                }
                // If cannot be colored, backtrack
                colors[node] = -1;
            }
        }
        return false;
    }

    // Checks if any of the adjacent nodes have the same color as what we picked
    public boolean canBeColored(boolean graph[][], int n, int[] colors, int node, int col) {
        for (int i = 0; i < n; i++) {
            //Check if there is an edge between the current node
            // and another node i, and if the other node i has the same color.
            if (graph[node][i] == true && colors[i] == col) {
                return false;
            }
        }
        return true;
    }
}
