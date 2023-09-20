package dpMemoizationRecursion;

import java.util.Arrays;

public class MinimumSubsetSumDifference {
	public static void main(String args[]) {
		int arr[]=new int[] {1,2,7};
		int target=5;
		int n=arr.length;
		int ans1=minimumSubsetSumDifferenceRecursion(arr,target,n);
		System.out.println(ans1);
		//Time Comp:2 power n (tree) (exponential) space:O(n) stack
		//create dp array of n+1 target+1
		Boolean dp[][]=new Boolean[n+1][target+1];
		for(Boolean[] row:dp) {
			Arrays.fill(row, false);
		}
		Boolean ans2=minimumSubsetSumDifferenceMemoization(arr,target,n,dp);
		//Time Comp:O(mxn) space:O(mxn)+O(n) stack
		System.out.println(ans2);

		Boolean ans3=minimumSubsetSumDifferenceTabulation(arr,target,n);

		System.out.println(ans3);
		//Time Comp:O(mxn) space:O(mxn)
		

	}

	private static Boolean minimumSubsetSumDifferenceTabulation(int[] arr, int target, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Boolean minimumSubsetSumDifferenceMemoization(int[] arr, int target, int n, Boolean[][] dp) {
		// TODO Auto-generated method stub
		return null;
	}

	private static int minimumSubsetSumDifferenceRecursion(int[] arr, int target, int n) {
		//get total sum of arr
		int totalSum=0;
		for(int i=0;i<n;i++) {
			totalSum+=arr[i];
		}
		//s1+s2=sum 
		//s2=sum-s1
		//s2-s1=min is ans 
		//sum-2s1=min as ans(get min of all subsets at end)
		int min=minSubsetSum(arr,n,0,totalSum);
		return min;
	}

	private static int minSubsetSum(int[] arr, int n, int subsetSum, int totalSum) {
		//If we reached last element(ie n==0).return totalSum-2*subsetSum
		if(n==0) {
			return Math.abs(totalSum-2*subsetSum);
		}
		
		//pick ,not pick
		return Math.min(minSubsetSum(arr, n-1, subsetSum+arr[n-1], totalSum),
				minSubsetSum(arr, n-1, subsetSum, totalSum));
		
		
	}
}
