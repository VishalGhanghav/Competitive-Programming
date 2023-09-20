package Traversal;

import java.util.*;
class Bfs {
    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V, 
    ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> bfs=new ArrayList<Integer>();
        Queue<Integer> q=new LinkedList<>();
        boolean[] visited=new boolean[V];
        
        //put first element in queue
        q.add(0);
        visited[0]=true;
        
        while(!q.isEmpty()) {
        	Integer node=q.poll();
        	bfs.add(node);
        	for(Integer i:adj.get(node)) {
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
        		if(visited[i]==false) {
        			visited[i]=true;
        			q.add(i);
        		}
        	}
        	
        }
      return bfs;
    }
    
    public static void main(String args[]) {

        ArrayList < ArrayList < Integer >> adj = new ArrayList < > ();
        for (int i = 0; i < 5; i++) {
            adj.add(new ArrayList < > ());
        }
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(4);
        adj.get(4).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(1).add(3);
        adj.get(3).add(1);
        /*
         0-1-2
         | |
         4 3
         */
        Bfs sl = new Bfs(); 
        ArrayList < Integer > ans = sl.bfsOfGraph(5, adj);
        int n = ans.size(); 
        for(int i = 0;i<n;i++) {
            System.out.print(ans.get(i)+" "); 
        }
    }
}