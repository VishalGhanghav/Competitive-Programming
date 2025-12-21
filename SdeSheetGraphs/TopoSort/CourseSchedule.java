package SdeSheetGraphs.TopoSort;

import java.util.*;

class CourseSchedule {

    /**
     * APPROACH 1: BFS (Kahn's Algorithm)
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E) for Adjacency List
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Using BFS
        List<List<Integer>> adjList = new ArrayList<>();
        // I will first create Adjacency List to fetch neighbours or required courses
        // numCourses in for loop because those many nodes are present
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        // Here i will add all the courses that are present in given 2-d array
        int m = prerequisites.length;
        for (int i = 0; i < m; i++) {
            // the pair [0, 1], indicates that to take course 0 you have to first take course 1.
            // SO that means 1 comes before 0 in graph .ie . 1->0
            int prerequisite = prerequisites[i][1];
            int course = prerequisites[i][0];
            adjList.get(prerequisite).add(course);
        }
        // to complete task 1 ,
        // i need to complete task 0.which means we have topo sort kinda dependency
        // So lets use toposort ,I will prefer BFS for it
        int indegree[] = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            for (int node : adjList.get(i)) {
                indegree[node]++;
            }
        }

        // I will all vertices with indegree 0 in queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // now actually perform bfs(toposort way)
        int cnt = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            cnt++;
            for (int i : adjList.get(node)) {
                indegree[i]--;
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }
        }

        // it is valid if there is no cycle.
        // How to determine that:put cnt for no of nodes in topo sort.
        // If it is equal to numCOurses .Its valid else not valid
        if (cnt == numCourses) {
            return true;
        }
        return false;
    }

    /**
     * APPROACH 2: DFS (Cycle Detection)
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        for (int[] pre : prerequisites) {
            adj.get(pre[1]).add(pre[0]);
        }

        int[] visited = new int[numCourses];
        int[] pathVisited = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (hasCycle(i, adj, visited, pathVisited)) return false;
            }
        }
        return true;
    }

    private boolean hasCycle(int node, ArrayList<ArrayList<Integer>> adj, int[] visited, int[] pathVisited) {
        visited[node] = 1;
        pathVisited[node] = 1;

        for (int neighbor : adj.get(node)) {
            if (visited[neighbor] == 0) {
                if (hasCycle(neighbor, adj, visited, pathVisited)) return true;
            } else if (pathVisited[neighbor] == 1) {
                return true;
            }
        }

        pathVisited[node] = 0;
        return false;
    }

    public static void main(String[] args) {
        CourseSchedule sol = new CourseSchedule();

        // Example 1: Possible to finish
        int numCourses1 = 2;
        int[][] pre1 = {{1, 0}};

        // Example 2: Impossible (Cycle: 0 -> 1 -> 0)
        int numCourses2 = 2;
        int[][] pre2 = {{1, 0}, {0, 1}};

        System.out.println("--- BFS (Kahn's) Results ---");
        System.out.println("Example 1: " + sol.canFinish(numCourses1, pre1));
        System.out.println("Example 2: " + sol.canFinish(numCourses2, pre2));

        System.out.println("\n--- DFS Results ---");
        System.out.println("Example 1: " + sol.canFinishDFS(numCourses1, pre1));
        System.out.println("Example 2: " + sol.canFinishDFS(numCourses2, pre2));
    }
}
