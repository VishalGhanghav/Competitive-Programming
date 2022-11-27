package com.BinarySearch;

//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class SearchAnElementInInfiniteSortedArray {
	// Driver code
	public static void main(String[] args) throws Exception {
		int arr[] = new int[]{3, 5, 7, 9, 10, 90,
                100, 130, 140, 160, 170};
int ans = findElement(arr,10);

if (ans==-1)
System.out.println("Element not found");
else
System.out.println("Element found at index " + ans);
		}
	

	private static int findElement(int[] a, int target) {
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
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(a[mid]==target) {
				return mid;
			}else if(a[mid]<target) {
				start=mid+1;
			}else if(a[mid]>target) {
				end=mid-1;
			}
		}
		return -1;
	}
}







