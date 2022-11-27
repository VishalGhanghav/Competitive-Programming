package com.BinarySearch.OnAnswer;

//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class PainterPartitionProblem
{
  public static void main(String args[])throws IOException
  {
      BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
      int t = Integer.parseInt(read.readLine());
      while(t-- > 0)
      {
          String input_line1[] = read.readLine().trim().split("\\s+");
          int k = Integer.parseInt(input_line1[0]);
          int n = Integer.parseInt(input_line1[1]);
          String input_line[] = read.readLine().trim().split("\\s+");
          int arr[]= new int[n];
          for(int i = 0; i < n; i++)
              arr[i] = Integer.parseInt(input_line[i]);
          
          PainterPartitionProblem2 ob = new PainterPartitionProblem2();
          System.out.println(ob.minTime(arr,n,k));
      }
  }
}


//} Driver Code Ends


//User function Template for Java

class PainterPartitionProblem2{
  static long minTime(int[] a,int n,int m){
      //code here
		//Your code here
		
		//FInd max of array as start
		long start=a[0];
		for(int i=1;i<n;i++) {
			start=Math.max(start,a[i]);
		}
		if(n<m){
		    return start;
		}
		//FInd sum of array as min
		long end=0;
		for(int i=0;i<n;i++) {
		    
			end+=a[i];
		}
		long res=-1;
		while(start<=end) {
			long mid=start+(end-start)/2;
			if(isValid(a,n,m,mid)) {
				res=mid;
				end=mid-1;
			}else {
				start=mid+1;
			}

		}
		return res;
  }
  
   static boolean isValid(int[] a, int n, int noOfStudents, long maxNoOfPages) {
		long pageSum=0;
		int studentCount=1;
		for(int i=0;i<n;i++) {
			if((pageSum+a[i])>maxNoOfPages) {
				studentCount++;
				pageSum=a[i];
			}else {
				pageSum+=a[i];
			}
		}
		if(studentCount>noOfStudents) {
			return false;
		}else {
			return true;
		}
   }
}



