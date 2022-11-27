package com.BinarySearch.OnAnswer;

//{ Driver Code Starts
//Initial Template for Java

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
class AllocateMinimumNumberOfPages {
	public static void main (String[] args) {
		Scanner sc=new Scanner(System.in);

		int t=sc.nextInt();

		while(t-->0)
		{
			int n=sc.nextInt();
			int a[]=new int[n];

			for(int i=0;i<n;i++)
			{
				a[i]=sc.nextInt();
			}
			int m=sc.nextInt();
			AllocateMinimumNumberOfPages2 ob = new AllocateMinimumNumberOfPages2();
			System.out.println(ob.findPages(a,n,m));
		}
	}
}
//} Driver Code Ends


//User function Template for Java

class AllocateMinimumNumberOfPages2 
{
	//Function to find minimum number of pages.
	public static int findPages(int[]a,int n,int m)
	{
		if(n<m) {
			return -1;
		}
		//Your code here
		//FInd max of array as start
		int start=a[0];
		for(int i=1;i<n;i++) {
			start=Math.max(start,a[i]);
		}
		//FInd sum of array as min
		int end=0;
		for(int i=0;i<n;i++) {
			end+=a[i];
		}
		int res=-1;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(isValid(a,n,m,mid)) {
				res=mid;
				end=mid-1;
			}else {
				start=mid+1;
			}

		}
		return res;
	}

	private static boolean isValid(int[] a, int n, int noOfStudents, int maxNoOfPages) {
		int pageSum=0;
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
		
		//return studentCount<=noOfStudents
	}
}
