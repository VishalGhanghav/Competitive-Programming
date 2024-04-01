package Problems;

import java.util.*;
/*
 There are n cities. Some of them are connected, while some are not. If city a is 
 connected directly with city b, and city b is connected directly with city c, 
 then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no
 other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city
 and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

 

Example 1:


Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Example 2:


Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3
 */
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
        int [][] arr= {{1,1,0,0},
        				{1,1,0,0},
        				{0,0,1,1},
        				{0,0,1,1}};
        
        for(int i=0;i<=3;i++) {
			for(int j=0;j<=3;j++) {
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
