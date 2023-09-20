package Problems;

import java.util.*;

class NumberOfProvinces {
    // dfs traversal function 
    private  void dfs(int node,  ArrayList<ArrayList<Integer>> adjList , 
       int visited[]) {
       
    	//mark that node as visited
    	visited[node]=1;
    	for(Integer i:adjList.get(node)) {
    		if(visited[i]==0) {
    		dfs(i,adjList,visited);
    		}
    	}
    }
    
    public static void main(String[] args)
    {

        // adjacency matrix 
        int [][] arr= {{1,1,0},
        				{1,1,0},
        				{0,0,1}};
        
        for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				//not a self node
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}     
        NumberOfProvinces ob = new NumberOfProvinces();
        System.out.println(ob.numProvinces(arr));
    }
	private int numProvinces(int[][] arr) {
		ArrayList<ArrayList<Integer>> adjList=new ArrayList<>();
		int row=arr.length;
		for(int i = 0;i<row;i++) {
            adjList.add(new ArrayList<Integer>()); 
        }
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<row;j++) {
				//not a self node
				if(arr[i][j]==1 && i!=j) {
					adjList.get(i).add(j);
				}
			}
		}
		
		System.out.println(adjList);
		int count=0;
		int visited[]=new int[row];
		
		for(int i=0;i<row;i++) {
			if(visited[i]==0) {
				count++;
				dfs(i,adjList,visited);
			}
		}
		return count;
	}
}
