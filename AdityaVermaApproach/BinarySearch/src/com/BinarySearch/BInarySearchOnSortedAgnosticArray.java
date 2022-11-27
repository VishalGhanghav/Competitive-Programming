package com.BinarySearch;
//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class BInarySearchOnSortedAgnosticArray
{
    
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        //2 is the size as we pass n and k
        int t = Integer.parseInt(read.readLine());
        
        while(t-- > 0)
        {
        	//enter n and k
            String s[] = read.readLine().trim().split("\\s+");
            
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            int arr[] = new int[n];
            //enter the array
            String st[] = read.readLine().trim().split("\\s+");
            
            for(int i = 0; i < n; i++)
            {
                arr[i] = Integer.parseInt(st[i]);
            }
            
            Agnostic obj = new Agnostic();
            
            System.out.println(obj.searchInSorted(arr, n, k));
        }
    }
}
// } Driver Code Ends
class Agnostic{
    static int searchInSorted(int arr[], int N, int K)
    {
    	int result=0;
      if(arr[0]>arr[N-1]) {
    	  result=callDescendingSort(arr,N,K);
      }else {
    	  result=callAscendingSort(arr,N,K);
      }
       return result;
    }

	private static int callAscendingSort(int[] arr, int N, int K) {
		 // Your code here
        int start=0;
        int end=N-1;
        while(start<=end){
            int mid=start+((end-start)/2);
            if(arr[mid]==K){
                return 1;
            }else if(arr[mid]<K){
                start=mid+1;
            }else if(arr[mid]>K){
                end=mid-1;
            }
        }
        return -1;
		
	}

	private static int callDescendingSort(int[] arr, int N, int K) {
		 // Your code here
        int start=0;
        int end=N-1;
        while(start<=end){
            int mid=start+((end-start)/2);
            if(arr[mid]==K){
                return 1;
            }else if(arr[mid]<K){
            	end=mid-1;
                
            }else if(arr[mid]>K){
            	start=mid+1;
            }
        }
        return -1;
		
	}
}