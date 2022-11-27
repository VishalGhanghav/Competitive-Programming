package com.BinarySearch;
//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class SearchAnElementInNearlySortedArray
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
            
            
            NearlySOrtedSearch obj = new NearlySOrtedSearch();
            
            System.out.println(obj.searchInSorted(arr, n, k));
        }
    }
}
// } Driver Code Ends
class NearlySOrtedSearch{
    static int searchInSorted(int arr[], int N, int K)
    {
        
        // Your code here
        int start=0;
        int end=N-1;
        while(start<=end){
            int mid=start+((end-start)/2);
            int next=(mid+1)%N;
            int prev=(mid-1+N)%N;
            if(arr[mid]==K){
                return mid;
            }else if(arr[next]==K){
            	return mid;
            }else if(arr[prev]==K){
            	return mid;
            }else if(arr[mid]<K){
                start=mid+2;
            }else if(arr[mid]>K){
                end=mid-2;
            }
        }
        return -1;
    }
}