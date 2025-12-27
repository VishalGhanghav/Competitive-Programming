package SdeSheetGraphs.ShortestPath;

import java.util.*;

// Class Name: ShortestPathInDAG
class ShortestPathInDAG {

    // Side class kept under main class per instructions
    class Pair {
        int node;
        int distance;

        Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    /**
     * Time Complexity: O(N + M) - Topological sort takes O(N + M) and distance relaxation takes O(N + M).
     * Space Complexity: O(N + M) - For adjacency list, visited array, stack, and distance array.
     */
    public int[] shortestPath(int N, int M, int[][] edges) {
        // Code here

        // create a graph
        List<List<Pair>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<Pair>());

        }
        // I will run through edges array for creating adjList
        for (int i = 0; i < M; i++) {
            int startNode = edges[i][0];
            int connectedNode = edges[i][1];
            int distance = edges[i][2];
            adjList.get(startNode).add(new Pair(connectedNode, distance));
        }

        Stack<Integer> s = new Stack<>();
        int visited[] = new int[N];
        for (int i = 0; i < N; i++) {
            if (visited[i] == 0) {
                toposort(i, adjList, visited, s);
            }
        }
        // Now my stack is filled in toposort
        // Now I need distance array filled with max values
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        // take the top of stack ,add in distance array if not present
        // else check if distance is less than we need.
        while (!s.isEmpty()) {
            int node = s.pop();
            // for the node i have got check its neighbous in adjList
            // and add their dist if not present in dist array or update if less
            if (dist[node] != Integer.MAX_VALUE) {
                for (Pair nbPair : adjList.get(node)) {
                    int nb = nbPair.node;
                    int nbDist = nbPair.distance;

                    // If distance of my currNode+distance of neighbour node is less than
                    // whats sitting at dist[nb] array then change it
                    if ((dist[node] + nbDist) < dist[nb]) {
                        dist[nb] = dist[node] + nbDist;
                    }

                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }
        return dist;

    }

    public void toposort(int node, List<List<Pair>> adjList, int[] visited, Stack<Integer> s) {
        visited[node] = 1;
        // Now check neighbours
        for (Pair p : adjList.get(node)) {
            int nb = p.node;
            if (visited[nb] == 0) {
                toposort(nb, adjList, visited, s);
            }
        }
        // when the dfs finishes for a node completely ,we add that while backtracking in stack
        s.add(node);
    }

    public static void main(String[] args) {
        ShortestPathInDAG solver = new ShortestPathInDAG();
        int N = 6, M = 7;
        int[][] edges = {
                {0, 1, 2}, {0, 4, 1},
                {4, 5, 4}, {4, 2, 2},
                {1, 2, 3}, {2, 3, 6},
                {5, 3, 1}
        };

        int[] result = solver.shortestPath(N, M, edges);

        System.out.println("Shortest Paths from Source 0:");
        for (int i = 0; i < N; i++) {
            System.out.println("To " + i + ": " + result[i]);
        }
    }
}