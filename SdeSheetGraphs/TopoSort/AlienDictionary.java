package SdeSheetGraphs.TopoSort;

import java.util.*;

class AlienDictionary {

    /**
     * APPROACH 1: BFS (Kahn's Algorithm) - Original Logic
     * Time Complexity: O(N * len + K) - N is number of words, K is alphabet size.
     * Space Complexity: O(K + E) - E is number of distinct character relations.
     */
    public String findOrder(String[] dict, int N, int K) {
        // Write your code here
        List<List<Integer>> adjList = new ArrayList<>();
        // My adjacency list will have 4 nodes right
        // Think how graph will be connecting around those K nodes
        for (int i = 0; i < K; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            String s1 = dict[i];
            String s2 = dict[i + 1];
            // I will be going minLen of either of 2 string
            // eg. abc ab .I will only check upto ab
            int len = Math.min(s1.length(), s2.length());
            // Now compare both the strings with each other char by char
            for (int j = 0; j < len; j++) {

                // If characters are not equal.It means 1st comes before 2nd as alien dictionary is sorted
                if (s1.charAt(j) != s2.charAt(j)) {
                    // I will create a path b->a ie.s1.charAt(i)->s1.charAt(i+1)
                    // but I want numbers .so just subtract by 'a'
                    adjList.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
                    // If i find .I will break,no need to check further
                    break;
                }
            }

        }
        //System.out.println(adjList);
        // Now adjList is create go toposort
        List<Integer> toposort = toposort(adjList, K);
        // we need to return in form of string.currently we have numbers
        // eg. toposort=1 3 0 2 .To convert to char .I will need to add 65
        // 65+3=68 ie.d .1+65=66 ie b, 2+65=67 ie.c .I convert that to char
        String ans = "";
        for (int i : toposort) {
            ans = ans + (char) (i + (int) ('a'));
        }
        return ans;
    }

    public List<Integer> toposort(List<List<Integer>> adjList, int k) {
        List<Integer> toposort = new ArrayList<>();
        int[] indegree = new int[k];
        // create indegree array
        for (int i = 0; i < k; i++) {
            // check neighbour and increase their indegree
            for (int node : adjList.get(i)) {
                indegree[node]++;
            }
        }
        // Add indegree 0 in queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // process queue in toposort way
        while (!q.isEmpty()) {
            int node = q.poll();
            toposort.add(node);

            for (int nb : adjList.get(node)) {
                indegree[nb]--;
                if (indegree[nb] == 0) {
                    q.add(nb);
                }
            }
        }

        return toposort;
    }

    /**
     * APPROACH 2: DFS (Alternative)
     * Time Complexity: O(N * len + K)
     * Space Complexity: O(K)
     */
    public String findOrderDFS(String[] dict, int N, int K) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < K; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            String s1 = dict[i], s2 = dict[i + 1];
            int len = Math.min(s1.length(), s2.length());
            for (int j = 0; j < len; j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    adj.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
                    break;
                }
            }
        }

        Stack<Integer> st = new Stack<>();
        int[] vis = new int[K];
        for (int i = 0; i < K; i++) {
            if (vis[i] == 0) dfs(i, adj, vis, st);
        }

        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append((char) (st.pop() + 'a'));
        }
        return sb.toString();
    }

    private void dfs(int node, List<List<Integer>> adj, int[] vis, Stack<Integer> st) {
        vis[node] = 1;
        for (int it : adj.get(node)) {
            if (vis[it] == 0) dfs(it, adj, vis, st);
        }
        st.push(node);
    }

    public static void main(String[] args) {
        AlienDictionary ad = new AlienDictionary();
        String[] dict = {"baa", "abcd", "abca", "cab", "cada"};
        int N = 5, K = 4;



        System.out.println("--- BFS Output ---");
        System.out.println("Alphabet Order: " + ad.findOrder(dict, N, K));

        System.out.println("\n--- DFS Output ---");
        System.out.println("Alphabet Order: " + ad.findOrderDFS(dict, N, K));
    }
}