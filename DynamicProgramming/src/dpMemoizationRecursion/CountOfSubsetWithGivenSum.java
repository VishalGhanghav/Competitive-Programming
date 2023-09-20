package dpMemoizationRecursion;

import java.util.Arrays;

public class CountOfSubsetWithGivenSum {
	public static void main(String args[]) {
		int arr[]=new int[] {2,3,5,7,8,10,5};
		int target=5;
		int n=arr.length;
		int ans1=countOfSubsetWithGivenSumRecursion(arr,target,n);
		System.out.println(ans1);
		//Time Comp:2 power n (tree) (exponential) space:O(n) stack
		//create dp array of n+1 target+1
		Boolean dp[][]=new Boolean[n+1][target+1];
		for(Boolean[] row:dp) {
			Arrays.fill(row, false);
		}
		Boolean ans2=countOfSubsetWithGivenSumMemoization(arr,target,n,dp);
		//Time Comp:O(mxn) space:O(mxn)+O(n) stack
		System.out.println(ans2);

		Boolean ans3=countOfSubsetWithGivenSumTabulation(arr,target,n);

		System.out.println(ans3);
		//Time Comp:O(mxn) space:O(mxn)
		

	}

	private static Boolean countOfSubsetWithGivenSumTabulation(int[] arr, int sum, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Boolean countOfSubsetWithGivenSumMemoization(int[] arr, int sum, int n, Boolean[][] dp) {
		// TODO Auto-generated method stub
		return null;
	}

	private static int countOfSubsetWithGivenSumRecursion(int[] arr, int sum, int n) {
		if(sum==0) {
			return 1;
		}
		if(sum>0 && n==0) {
			return 0;
		}
		if(arr[n-1]<=sum) {
			return countOfSubsetWithGivenSumRecursion(arr, sum-arr[n-1], n-1) +
			countOfSubsetWithGivenSumRecursion(arr, sum, n-1);
		}else {
			return countOfSubsetWithGivenSumRecursion(arr, sum, n-1);
		}
	}
	
	
}
