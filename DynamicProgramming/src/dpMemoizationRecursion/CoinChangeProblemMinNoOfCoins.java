package dpMemoizationRecursion;

import java.util.Arrays;

public class CoinChangeProblemMinNoOfCoins {
	public static void main(String args[]) {
		int arr[]=new int[] {1,2,3};
		int target=5;
		int n=arr.length;
		//no of ways
		int ans1=coinChangeProblemMinNoOfCoinsRecursion(arr,target,n);
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

	

	private static int coinChangeProblemMinNoOfCoinsRecursion(int[] arr, int sum, int n) {
		if(sum>=0 & n==0) {
			return Integer.MAX_VALUE-1;
		}
		if(sum==0 && n>0) {
			return 0;
		}
		if(arr[n-1]<=sum) {
			return Math.min(coinChangeProblemMinNoOfCoinsRecursion(arr, sum-arr[n-1], n)+1 ,
					coinChangeProblemMinNoOfCoinsRecursion(arr, sum, n-1));
		}else {
			return coinChangeProblemMinNoOfCoinsRecursion(arr, sum, n-1);
		}
	}
}
