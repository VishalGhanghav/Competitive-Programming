package com.BinarySearch;
//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class FindCeilOfAnElement
{
  public static void main (String[] args)throws IOException {

      BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
      int t = Integer.parseInt(read.readLine());
      
      while(t-- > 0)
      {
         String a[] = read.readLine().trim().split("\\s+");
         int n = Integer.parseInt(a[0]);
         long x = Long.parseLong(a[(int)1]);
         String st[] = read.readLine().trim().split("\\s+");
      
         long arr[] = new long[n];
         for(int i = 0; i < n; i++)
         {
             arr[i] = Long.parseLong(st[i]);
         }
         
         System.out.println(new FindCeil().findCeil(arr, n, x));
        
      }
  }
  
}
//} Driver Code Ends


class FindCeil{
  
  // Function to find floor of x
  // arr: input array
  // n is the size of array
  static int findCeil(long arr[], int n, long x)
  {
	  int start=0;
	  int end=n-1;
	  //If one element in array
	  if(n==1) {
		  return 0;
	  }
	  int res=0;
	  while(start<=end) {
		  int mid=start+(end-start)/2;
		  
		  if(arr[mid]==x) {
			  return mid;
		  }else if(arr[mid]<x){
			  start=mid+1;
			  
			  
		  }else if(arr[mid]>x) {
			  end=mid-1;
			//Every time you find an element greater than x ,store in res
			  res=mid;
		  }
	  }
	  if(res==0) {
		  return -1;
	  }else {
		  return res;
	  }
      
  }
  
}

