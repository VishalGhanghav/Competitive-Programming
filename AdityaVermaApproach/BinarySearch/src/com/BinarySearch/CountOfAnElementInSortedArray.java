package com.BinarySearch;
//{ Driver Code Starts
//Initial Template for Java



import java.util.*;
import java.io.*;

public class CountOfAnElementInSortedArray {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());
        while (tc-- > 0) {
            String[] inputLine;
            inputLine = br.readLine().trim().split(" ");
            int n = Integer.parseInt(inputLine[0]);
            int x = Integer.parseInt(inputLine[1]);
            int[] arr = new int[n];
            inputLine = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(inputLine[i]);
            }

            int ans = new Count().count(arr, n, x);
            System.out.println(ans);
        }
    }
}

// } Driver Code Ends


//User function Template for Java



class Count {
    int count(int[] arr, int n, int x) {

        // code here
    	int firstOccurence=getFirstOccurence(arr,n,x);
    	int lastOccurence=getLastOccurence(arr,n,x);
    	System.out.println(firstOccurence+" "+lastOccurence);
    	if(firstOccurence==-1) {
    		return 0;
    	}else {
    	return lastOccurence-firstOccurence+1;
    	}
    	
    }	
    	

    	

	private int getLastOccurence(int[] arr, int N, int K) {
		int start=0;
        int end=N-1;
        int res=-1;
        while(start<=end){
            int mid=start+((end-start)/2);
            if(arr[mid]==K){
            	res=mid;
            	start=mid+1;
            }else if(arr[mid]<K){
                start=mid+1;
            }else if(arr[mid]>K){
                end=mid-1;
            }
        }
        return res;
	}





	private int getFirstOccurence(int[] arr, int N, int K) {
		int start=0;
        int end=N-1;
        int res=-1;
        while(start<=end){
            int mid=start+((end-start)/2);
            if(arr[mid]==K){
            	res=mid;
            	end=mid-1;
            }else if(arr[mid]<K){
                start=mid+1;
            }else if(arr[mid]>K){
                end=mid-1;
            }
        }
        return res;
	}
}