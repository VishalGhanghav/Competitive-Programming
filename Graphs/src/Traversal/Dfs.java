package Traversal;


import java.util.*;
class Dfs {
    
    
    // Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        //boolean array to keep track of visited vertices
        boolean[] visited=new boolean[V];
        ArrayList<Integer> dfsList=new ArrayList<>();
        int startNode=0;
        visited[startNode]=true;
        dfs(startNode,dfsList,visited,adj);
        return dfsList;
    }
    
    private void dfs(int node, ArrayList<Integer> dfsList, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
    	//marking current node visited
    	visited[node]=true;
    	dfsList.add(node);
    	//traversing neighbours
    	for(Integer i:adj.get(node)) {
			if(visited[i]==false) {
				dfs(i,dfsList,visited,adj);
			}
    	}
		
	}
	public static void main(String args[]) {

        ArrayList < ArrayList < Integer >> adj = new ArrayList < > ();
        for (int i = 0; i < 5; i++) {
            adj.add(new ArrayList < > ());
        }
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(2).add(4);
        adj.get(4).add(2);
        
        Dfs sl = new Dfs(); 
        ArrayList < Integer > ans = sl.dfsOfGraph(5, adj);
        int n = ans.size(); 
        for(int i = 0;i<n;i++) {
            System.out.print(ans.get(i)+" "); 
        }
    }
}