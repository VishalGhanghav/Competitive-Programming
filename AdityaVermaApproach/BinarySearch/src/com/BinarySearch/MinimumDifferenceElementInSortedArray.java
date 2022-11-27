package com.BinarySearch;

//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class MinimumDifferenceElementInSortedArray {
	// Driver code
	public static void main(String[] args) throws Exception {

		 Scanner keyboard = new Scanner(System.in);
	        int n = keyboard.nextInt();
	        int[] a = new int[n];
	        for (int i = 0; i < n; i++) {
	            a[i] = keyboard.nextInt();
	        }
	        int target = keyboard.nextInt();
	        keyboard.close();

	        System.out.printf("MinimumDifferenceElementWith(%d) = %d%n", target, binarySearchMinDifference(a, target));
	    
		}

	private static int binarySearchMinDifference(int[] a, int target) {
		int start=0;
		int end=a.length-1;
		//eg.10 12 13 target =9 here end=-1 out of bound so->
		if(target<a[start]) {
			return a[0];
		}else if(target>a[end]) {
			return a[end];
		}
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(a[mid]==target) {
				return a[mid];
			}else if(a[mid]<target) {
				start=mid+1;
			}else if(a[mid]>target) {
				end=mid-1;
			}
		}
		if(Math.abs(a[start]-target)<Math.abs(a[end]-target)) {
			return a[start];
		}else {
			return a[end];
		}
		
	}
	

	
}







