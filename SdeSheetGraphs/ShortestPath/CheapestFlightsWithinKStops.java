package SdeSheetGraphs.ShortestPath;

import java.util.*;

// Class Name: CheapestFlightsWithinKStops
class CheapestFlightsWithinKStops {

    // Helper classes kept as inner classes per instructions
    class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    class Triplet {
        int first;
        int second;
        int third;

        Triplet(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    /**
     * Time Complexity: O(E) - Where E is the number of flights.
     * Since we prioritize stops (which increase by 1 each time), it behaves like BFS.
     * Space Complexity: O(V + E) - For the adjacency list and the distance array.
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<Pair>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        int m = flights.length;
        for (int i = 0; i < m; i++) {
            adjList.get(flights[i][0]).add(new Pair(flights[i][1], flights[i][2]));
        }

        Queue<Triplet> q = new LinkedList<>();
        // format for triplet: stops,node,distance
        int dist[] = new int[n];
        // fill dist with maxValue
        Arrays.fill(dist, Integer.MAX_VALUE);
        q.add(new Triplet(0, src, 0));

        while (!q.isEmpty()) {
            Triplet t = q.poll();
            int stops = t.first;
            int node = t.second;
            int distance = t.third;

            // If stops>k skip that particular node and keep on checking for other nodes in queue
            if (stops > k) {
                continue;
            }

            // check neighbours of node with help of adjList
            for (Pair p : adjList.get(node)) {
                // for each pair get its node and distance
                int nbnode = p.first;
                int nbdistance = p.second;

                // if nbdistance+currNodeDistance is less than what is sitting in my dist array
                // and also stops taken till now is less than equal to K
                if ((nbdistance + distance) < dist[nbnode] && stops <= k) {
                    dist[nbnode] = nbdistance + distance;
                    q.add(new Triplet(stops + 1, nbnode, dist[nbnode]));
                }
            }
        }

        // we never reached the distance
        if (dist[dst] == Integer.MAX_VALUE) {
            return -1;
        }
        return dist[dst];
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops solver = new CheapestFlightsWithinKStops();

        int n = 4;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        int src = 0, dst = 3, k = 1;

        int result = solver.findCheapestPrice(n, flights, src, dst, k);

        System.out.println("Flights: " + Arrays.deepToString(flights));
        System.out.println("Source: " + src + ", Destination: " + dst + ", K stops: " + k);
        System.out.println("Cheapest Price: " + (result == -1 ? "Not Possible" : result));
    }
}