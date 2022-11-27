package fixedSizeWindow;

import java.util.ArrayList;

public class MaxSumSubarray {

	public static void main(String[] args) {
		int arr[]=new int[] {14,11,41,2,5,55,9,28,10,12};
		int k=3;
		int res=solve(arr,arr.length,k);
		System.out.println(res);
	}

	private static int solve(int[] arr, int n, int k) {
		ArrayList<Integer> al=new ArrayList<Integer>();
		int start=0;
		int end=0;
		int maxSum=0;
		int currMax=0;
		while(end<n) {
			currMax+=arr[end];
			if(end-start+1<k) {
				end++;
			}else if(end-start+1==k) {
				maxSum=Math.max(maxSum, currMax);
				currMax-=arr[start];
				start++;
				end++;
			}
			
		}
		return maxSum;
	}
/* public static int findMaxSumSubArrayUsing For(int k, int[] arr) {
    int maxSum = 0, windowSum = 0;
    int windowStart = 0;

    for (int windowEnd=0; windowEnd < arr.length; windowEnd++) {
      windowSum += arr[windowEnd];
      if(windowEnd >= k-1) {
        maxSum = Math.max(maxSum, windowSum);
        windowSum -= arr[windowStart];
        windowStart++;
      }
    }
    return maxSum;
  }*/
	/* gfg
	 * class Solution{
    static long maximumSumSubarray(int k, ArrayList<Integer> arr,int n){
        // code here
        int start=0;
		int end=0;
		long maxSum=0;
		long currMax=0;
		while(end<n) {
			currMax+=arr.get(end);
			if(end-start+1<k) {
				end++;
			}else if(end-start+1==k) {
				maxSum=Math.max(maxSum, currMax);
				currMax-=arr.get(start);
				start++;
				end++;
			}
			
		}
		return maxSum;
    }
}*/
	 
}
