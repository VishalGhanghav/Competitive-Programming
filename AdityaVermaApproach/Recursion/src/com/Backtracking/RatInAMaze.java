package com.Backtracking;
import java.util.ArrayList;
public class RatInAMaze {
	 public static void main(String[] args) {
		 int n = 4;
		    int[][] a = {{1,0,0,0},{1,1,0,1},{1,1,0,0},{0,1,1,1}};
		    ArrayList < String > res = findPath(a, n);
		    if (res.size() > 0) {
		      for (int i = 0; i < res.size(); i++)
		        System.out.print(res.get(i) + " ");
		      System.out.println();
		    } else {
		      System.out.println(-1);
		    }
	    }
	 
	 public static ArrayList<String> findPath(int[][] m, int n) {
	        // Your code here
	        ArrayList<String> res=new ArrayList<String>();
	        int vis[][]=new int[n][n];
	        for(int i=0;i<n;i++) {
	        	for(int j=0;j<n;j++) {
	        		vis[i][j]=0;
	        	}
	        }
	        int di[]={+1,0,0,-1};
	        int dj[]={0,-1,+1,0};
	        if(m[0][0]==1){
	            backtrack(0,0,m,n,"",res,vis,di,dj);
	        }
	        System.out.println(res);
	        return res;
	        
	        
	    }
	    
	    public static void backtrack(int row,int col,int[][] arr,int n,String move,
	    		ArrayList<String> ans,int vis[][],int[] di,int[] dj){
	        //If i reach arr[n-1][n-1] ie last element .it is ans
	        
	        if(row==n-1 && col==n-1){
	            ans.add(move);
	            return;
	        }
	        //1,0 ==down ,-1,0==up
	        
	        String dir="DLRU";
	        for(int i=0;i<4;i++){
	            int delrow=row+di[i];
	            int delcol=col+dj[i];
	            System.out.println(delrow+delcol);
	            if(delrow<n && delrow>=0 && delcol<n && delcol>=0 &&
	                vis[delrow][delcol]==0 && arr[delrow][delcol]==1){
	                    vis[row][col]=1;
	                    backtrack(delrow,delcol,arr,n,move+dir.charAt(i),ans,vis,di,dj);
	                    vis[row][col]=0;
	            }
	            
	        }
	    }
}


