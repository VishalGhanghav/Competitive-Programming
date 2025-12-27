package SdeSheetGraphs.ShortestPath;

import java.util.*;

// Class Name: NumberOfWaysToArriveAtDestination
class NumberOfWaysToArriveAtDestination {

    // Helper class kept as inner class per instructions
    class Pair {
        long first;
        long second;

        Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }
    }

    /**
     * Time Complexity: O(E * log V) - Standard Dijkstra complexity where E is roads and V is n.
     * Space Complexity: O(V + E) - For the adjacency list, distance array, ways array, and PQ.
     */
    public int countPaths(int n, int[][] roads) {
        // CReate graph in adjList format for easy retrival
        int m = roads.length;
        List<List<Pair>> adjList = new ArrayList<>();
        // adjList for all nodes
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        // For each node add node distance.Used 'm' because at max I can have equal to edges
        for (int i = 0; i < m; i++) {
            adjList.get(roads[i][0]).add(new Pair(roads[i][1], roads[i][2]));
            adjList.get(roads[i][1]).add(new Pair(roads[i][0], roads[i][2]));
        }

        // Graph is created lets create dist,ways array and priorityQueue
        PriorityQueue<Pair> pq =
                new PriorityQueue<>(Comparator.comparingLong(p -> p.first));
        // 1 testcase failing so made this long
        long[] dist = new long[n];
        int ways[] = new int[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        pq.add(new Pair(0, 0));
        dist[0] = 0;
        // I can reach 0 by 1 way
        ways[0] = 1;
        // Define modulo value
        int mod = (int) (1e9 + 7);


        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            long distance = pair.first;
            long node = pair.second;

            // Optimization: If extracted distance is already greater than dist[node], skip
            if (distance > dist[(int)node]) continue;

            // for each node check its neighbours
            for (Pair p : adjList.get((int) node)) {
                // this pair is from adjList.format:node,distance
                long adjNode = p.first;
                long adjDistance = p.second;
                // If distance till now==what is in dist array it means I have visited this node before.
                // so increment no of ways
                if ((adjDistance + distance) == dist[(int) adjNode]) {
                    // We are returing ans through ways array
                    // so need to modulo this addition
                    ways[(int) adjNode] = (ways[(int) (int)node] + ways[(int) adjNode]) % mod;
                }
                // distance till now<what is at dist array for adjNode.So add in pq.set new distance
                // and ways will be same as before
                else if ((adjDistance + distance) < dist[(int) adjNode]) {
                    dist[(int) adjNode] = adjDistance + distance;
                    pq.add(new Pair((adjDistance + distance), adjNode));
                    ways[(int) adjNode] = ways[(int) (int)node];
                }
            }
        }
        // In question it is defined to return ans modulo 10^9+7
        // We are returing ans through ways array
        // so need to modulo this
        return ways[n - 1] % mod;

    }

    public static void main(String[] args) {
        NumberOfWaysToArriveAtDestination solver = new NumberOfWaysToArriveAtDestination();
        int n = 7;
        int[][] roads = {
                {0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3},
                {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1},
                {0, 4, 5}, {4, 6, 2}
        };

        int result = solver.countPaths(n, roads);
        System.out.println("Number of shortest paths to destination: " + result);
    }
}