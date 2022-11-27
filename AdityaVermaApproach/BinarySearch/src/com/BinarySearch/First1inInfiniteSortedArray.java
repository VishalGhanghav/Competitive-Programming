package com.BinarySearch;

//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class First1inInfiniteSortedArray {
	// Driver code
	public static void main(String[] args) throws Exception {

        int arr[] = { 0, 0,0,0,0,0,1, 1, 1, 1 };
        int target=1;
        System.out.println("Index = " +
                          posOfFirstOne(arr,target));
		}
	

	private static int posOfFirstOne(int[] a, int target) {
		int start=0;
		int end=1;
		while(a[end]<target) {
			start=end;
			
			//check that 2*h doesn't exceeds array
            //length to prevent ArrayOutOfBoundException
			//In practical scenario ,we won't get array out of bound as array is infinite
            if(2*end < a.length-1)
               end = 2*end;            
            else
               end =a.length-1;
		}
		System.out.println(start);
		System.out.println(end);
		return bs(a,target,start,end);
	}

	private static int bs(int[] a,int target, int start, int end) {
		int res=0;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(a[mid]==target) {
				res=mid;
				end=mid-1;
			}else if(a[mid]<target) {
				start=mid+1;
			}else if(a[mid]>target) {
				end=mid-1;
			}
		}
		return res;
	}
}







