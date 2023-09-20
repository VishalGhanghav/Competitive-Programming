package dpMemoizationRecursion;

import java.util.Arrays;

public class CoinChangeProblem1 {
	public static void main(String args[]) {
		int arr[]=new int[] {1,2,3};
		int target=5;
		int n=arr.length;
		//no of ways
		int ans1=coinChangeProblem1Recursion(arr,target,n);
		System.out.println(ans1);
		//Time Comp:2 power n (tree) (exponential) space:O(n) stack
		//create dp array of n+1 target+1
		Boolean dp[][]=new Boolean[n+1][target+1];
		for(Boolean[] row:dp) {
			Arrays.fill(row, false);
		}
		/*Boolean ans2=subsetSumMemoization(arr,target,n,dp);
		//Time Comp:O(mxn) space:O(mxn)+O(n) stack
		System.out.println(ans2);
		
		Boolean ans3=subsetSumTabulation(arr,target,n);
		
		System.out.println(ans3);
		//Time Comp:O(mxn) space:O(mxn)*/
	}

	private static int coinChangeProblem1Recursion(int[] arr, int sum, int n) {
		if(sum>0 & n==0) {
			return 0;
		}
		if(sum==0) {
			return 1;
		}
		if(arr[n-1]<=sum) {
			return coinChangeProblem1Recursion(arr, sum-arr[n-1], n) +
					coinChangeProblem1Recursion(arr, sum, n-1);
		}else {
			return coinChangeProblem1Recursion(arr, sum, n-1);
		}
	}
}
