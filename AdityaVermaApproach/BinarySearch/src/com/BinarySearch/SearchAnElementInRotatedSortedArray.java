package com.BinarySearch;

//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class SearchAnElementInRotatedSortedArray {
	// Driver code
	public static void main(String[] args) throws Exception {
		BufferedReader br =
				new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine().trim());
			String[] str = br.readLine().trim().split(" ");
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(str[i]);
			}
			int target=Integer.parseInt(br.readLine().trim());
			int ans = new SearchOnRotated().searchAnElement(a, n,target);
			System.out.println(ans);
		}
	}
}

 class SearchOnRotated {
	 
	int getMinElement(int arr[], int n) {
		// code here
		int low = 0, high = n - 1;
		while (low <= high) {

			int mid = low + (high - low) / 2;
			int prev = (mid - 1 + n) % n;
			int next = (mid + 1) % n;

			if (arr[mid] <= arr[prev]
					&& arr[mid] <= arr[next])
				return mid;
			else if (arr[mid] <= arr[high])
				high = mid - 1;
			else if (arr[mid] >= arr[low])
				low = mid + 1;
		}
		return 0;
	}

	public int searchAnElement(int[] a, int n, int target) {
		int minElementIndex=getMinElement(a, n);
		System.out.println(minElementIndex+"minElementIndex");
		int start = 0,end = 0;
		if(target<=minElementIndex) {
		 start=0;
		 end=minElementIndex-1;
		}else if(target>minElementIndex) {
			start=minElementIndex;
			end=a.length-1;
		}
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(a[mid]==target) {
				return a[mid];
			}else if(a[mid]>target) {
				end=mid-1;
			}else if(a[mid]<target) {
				start=mid+1;
			}
		}
		return 0;
	}
}






