package com.BinarySearch.OnAnswer;

import java.util.Scanner;

public class FindElementinBitonicArray {
	public static void main(String[] args) throws Exception {

		Scanner keyboard = new Scanner(System.in);
		int n = keyboard.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = keyboard.nextInt();
		}
		int k=keyboard.nextInt();
		keyboard.close();

		System.out.printf("Element at Index = %d", findElementInBitonicArray(a,a.length,k));

	}

	private static int findElementInBitonicArray(int[] a, int length, int target) {
		int peak=findPeakElement(a, length);
		
		if(a[peak]==target) {
			return peak;
		}
		
		int res=orderAgnosticSearch(a,length,0,peak-1,target);
		if(res==-1) {
			res=orderAgnosticSearch(a,length,peak+1,length-1,target);
		}
		return res;
	}

	private static int orderAgnosticSearch(int[] a, int n, int start, int end, int target) {
		boolean isAsc=a[start]<a[end];
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(a[mid]==target) {
				return mid;
			}
			if(isAsc) {
				if(a[mid]<target) {
					start=mid+1;
				}else if(a[mid]>target) {
					end=mid-1;
				}
			}else {
				if(a[mid]<target) {
					end=mid-1;
				}else if(a[mid]>target) {
					start=mid+1;
				}
			}
		}
		return -1;
		
	}

	private static int findPeakElement(int[] a,int n) {
		if(n==1) {
			return 0;
		}
		int start=0;
		int end=n-1;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(mid>0 && mid<n-1) {
				if(a[mid]>a[mid-1] && a[mid]>a[mid+1]) {
					return mid;
				}else if(a[mid-1]>a[mid]) {
					end=mid-1;
				}else if(a[mid+1]>a[mid]) {
					start=mid+1;
				}
			}else if(mid==0) {
				if(a[0]>a[1]) {
					return 0;
				}else {
					return 1;
				}
			}else if(mid==n-1) {
				if(a[n-1]>a[n-2]) {
					return n-1;
				}else {
					return n-2;
				}
			}
		}
		return -1;
	}
}
/*if(n==1) {
return 0;
}else if(a[0]>a[1]) {
return 0;
}else if(a[n-1]>a[n-2]) {
return n-1;
}
int start=1;
int end=n-2;
while(start<=end) {
int mid=start+(end-start)/2;
if(a[mid]>a[mid-1] && a[mid]>a[mid+1]) {
	return mid;
}else if(a[mid-1]>a[mid]) {
	end=mid-1;
}else if(a[mid+1]>a[mid]) {
	start=mid+1;
}
}
return -1;*/
/*
 * int mid = 0;
       
        while (l <= r) {
 
            // finding mid by binary right shifting.
            mid = (l + r) >> 1;
 
            // first case if mid is the answer
            if ((mid == 0
                 || arr[mid - 1] <= arr[mid])
                        && (mid == n - 1
                            || arr[mid + 1] <= arr[mid]))
                break;
 
            // move the right pointer
            if (mid > 0 && arr[mid - 1] > arr[mid])
                r = mid - 1;
 
            // move the left pointer
            else
                l = mid + 1;
        }
       
        return mid;
 */
