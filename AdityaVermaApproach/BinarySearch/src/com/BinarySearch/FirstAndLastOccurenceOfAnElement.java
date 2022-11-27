package com.BinarySearch;
//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

class pair  {  
    long first, second;  
    public pair(long first, long second)  
    {  
        this.first = first;  
        this.second = second;  
    }  
}

class FirstAndLastOccurenceOfAnElement {
	public static void main(String[] args) throws IOException
	{
	        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        int t =
            Integer.parseInt(br.readLine().trim()); // Inputting the testcases
        while(t-->0)
        {
            long n = Long.parseLong(br.readLine().trim());
            long a[] = new long[(int)(n)];
            // long getAnswer[] = new long[(int)(n)];
            String inputLine[] = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(inputLine[i]);
            }
            long k = Long.parseLong(br.readLine().trim());
            
            First obj = new First();
            pair ans = obj.indexes(a, k);
            if (ans.first == -1 && ans.second == -1)
                System.out.println(-1);
            else
                System.out.println(ans.first+" "+ans.second);
            
        }
	}
}


// } Driver Code Ends


//User function Template for Java

/*
class pair  {  
    long first, second;  
    public pair(long first, long second)  
    {  
        this.first = first;  
        this.second = second;  
    }  
}
*/

class First {
    
    public pair indexes(long v[], long x)
    {
		
        // Your code goes here
    	long firstOccurence=getFirstOccurence(v,v.length,x);
    	long lastOccurence;
    	if(firstOccurence==-1) {
    	//NO need to check for lastOccurence
    		lastOccurence=-1;
    	}else {
    	lastOccurence=getLastOccurence(v,v.length,x);
    	}
    	return (new pair(firstOccurence, lastOccurence));
    	
    }

	private int getLastOccurence(long[] arr, int N, long K) {
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

	private int getFirstOccurence(long[] arr, int N, long K) {
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