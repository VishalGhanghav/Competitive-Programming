package SdeSheetGraphs.DfsBfs;

import java.util.*;

public class NumberOfProvinces {
    // Time Complexity: O(V^2) - Due to iterating through the adjacency matrix to build the list
    // Space Complexity: O(V + E) - For the adjacency list and recursion stack
    public int findCircleNumDFS(int[][] arr) {
        // create adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            adjList.add(new ArrayList<>());
        }
        int row = arr.length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (arr[i][j] == 1 && i != j) {
                    adjList.get(i).add(j);
                }
            }
        }

        int count = 0;
        int visited[] = new int[row];
        for (int i = 0; i < row; i++) {
            if (visited[i] == 0) {
                dfs(i, adjList, visited);
                count++;
            }
        }
        return count;
    }

    public void dfs(int node, List<List<Integer>> adjList, int[] visited) {
        visited[node] = 1;
        for (Integer i : adjList.get(node)) {
            if (visited[i] == 0) {
                dfs(i, adjList, visited);
            }
        }
    }

    // Time Complexity: O(V^2) - Due to adjacency matrix processing
    // Space Complexity: O(V + E) - For the adjacency list and queue
    public int findCircleNumBFS(int[][] arr) {
        // create adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            adjList.add(new ArrayList<>());
        }
        int row = arr.length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (arr[i][j] == 1 && i != j) {
                    adjList.get(i).add(j);
                }
            }
        }
        // The ones not connected are ur answer
        System.out.println("Adjacency List: " + adjList);
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        int visited[] = new int[row];
        for (int i = 0; i < row; i++) {
            if (visited[i] == 0) {
                q.add(i);
                visited[i] = 1;
                while (!q.isEmpty()) {
                    int node = q.poll();
                    for (Integer p : adjList.get(node)) {
                        if (visited[p] == 0) {
                            q.add(p);
                            visited[p] = 1;
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOfProvinces solution = new NumberOfProvinces();

        // Example: 3 nodes, 0 and 1 are connected, 2 is isolated
        int[][] matrix = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };

        System.out.println("Input Matrix: " + Arrays.deepToString(matrix));

        // Testing BFS Approach
        int bfsResult = solution.findCircleNumBFS(matrix);
        System.out.println("BFS Result (Connected Components): " + bfsResult);

        // Testing DFS Approach
        int dfsResult = solution.findCircleNumDFS(matrix);
        System.out.println("DFS Result (Connected Components): " + dfsResult);
    }
}
