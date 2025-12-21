package SdeSheetGraphs.TopoSort;

import java.util.*;

class CourseScheduleII {

    /**
     * APPROACH 1: BFS (Kahn's Algorithm) - Original Logic
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Here I need ordering extra compared to course schedule I.
        // So instead of increasing count .I will just add that node in
        // integer array and return the result
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
        int res[] = new int[numCourses];
        while (!q.isEmpty()) {
            int node = q.poll();
            res[cnt++] = node;
            for (int i : adjList.get(node)) {
                indegree[i]--;
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }
        }
        if (cnt == numCourses) {
            return res;
        }
        int[] emptyArray = {};
        return emptyArray;
    }

    /**
     * APPROACH 2: DFS (Cycle Detection + Topological Sort)
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     */
    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        for (int[] pre : prerequisites) adj.get(pre[1]).add(pre[0]);

        int[] visited = new int[numCourses];
        int[] pathVisited = new int[numCourses];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (hasCycleAndSort(i, adj, visited, pathVisited, stack)) {
                    return new int[0];
                }
            }
        }

        int[] result = new int[numCourses];
        int idx = 0;
        while (!stack.isEmpty()) {
            result[idx++] = stack.pop();
        }
        return result;
    }

    private boolean hasCycleAndSort(int node, List<List<Integer>> adj, int[] visited, int[] pathVisited, Stack<Integer> stack) {
        visited[node] = 1;
        pathVisited[node] = 1;

        for (int neighbor : adj.get(node)) {
            if (visited[neighbor] == 0) {
                if (hasCycleAndSort(neighbor, adj, visited, pathVisited, stack)) return true;
            } else if (pathVisited[neighbor] == 1) {
                return true;
            }
        }

        pathVisited[node] = 0;
        stack.push(node);
        return false;
    }

    public static void main(String[] args) {
        CourseScheduleII solver = new CourseScheduleII();
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};

        // 0-> 1 -> 3
        // |        |
        // 2 --------
        System.out.println("--- BFS (Original Logic) Output ---");
        int[] bfsRes = solver.findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(bfsRes));

        System.out.println("\n--- DFS Output ---");
        int[] dfsRes = solver.findOrderDFS(numCourses, prerequisites);
        System.out.println(Arrays.toString(dfsRes));
    }
}