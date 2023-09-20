package Problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchldArray {
	
	public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int n = numCourses;
        
        //First creating adjacency list to get neighbours during topo sort.
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0; i<prerequisites.length; i++){
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        
        //to calculate indegree
        int[] indegree = new int[n];   
        for(int i=0 ; i<n ; i++){
            for(int it : adj.get(i)){
                indegree[it]++;
            }
        }
        
        //I am using BFS to implement topo.
        Queue<Integer> q = new LinkedList<>();
        //adding in q the nodes which have indegree = 0
        for(int i=0; i<n; i++){
            if(indegree[i] == 0) 
                q.add(i);
        }
        
        int[] ans = new int[n];
        int index=0;
      
        int count = 0;
        while(!q.isEmpty()){
           int node =  q.poll();
            count++;
            //pop from queue and add in our ans array.
            ans[index] = node;
            index++;
            //Check neighbours of node popped from queue and reduce their indegree in indegree array
            //If indegree gets 0, we 
            for(int it : adj.get(node)){
                indegree[it]--;
                
                if(indegree[it] == 0){
                    q.add(it);
                }
            }
        }          
		//if count not equal to n, denotes few nodes are not covered. Because there was cycle
		//thus if count != n, cycle present,thus  not possible to finish all courses.
        if(count != n) return new int[0];        
        return ans;
	}
	
	public static void main (String[] args) {
        int n = 4;
        int[][] course = {{1,0}, {2,0}, {3,1}, {3,2}};
        
        int[] result = findOrder(n, course);
        
        System.out.print("[");
        for(int i=0; i<result.length; i++){
        	if(i==result.length-1) {
            	System.out.print(result[i]);
            }else {
            System.out.print(result[i]+" ");
            }
        }
        System.out.print("]");
    }
	
	
}
