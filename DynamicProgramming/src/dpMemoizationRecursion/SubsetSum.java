package dpMemoizationRecursion;

import java.util.Arrays;

public class SubsetSum {
	public static void main(String args[]) {
		int arr[]=new int[] {2,3,7,8,10};
		int target=5;
		int n=arr.length;
		Boolean ans1=subsetSumRecursion(arr,target,n);
		System.out.println(ans1);
		//Time Comp:2 power n (tree) (exponential) space:O(n) stack
		//create dp array of n+1 target+1
		Boolean dp[][]=new Boolean[n+1][target+1];
		for(Boolean[] row:dp) {
			Arrays.fill(row, false);
		}
		Boolean ans2=subsetSumMemoization(arr,target,n,dp);
		//Time Comp:O(mxn) space:O(mxn)+O(n) stack
		System.out.println(ans2);
		
		Boolean ans3=subsetSumTabulation(arr,target,n);
		
		System.out.println(ans3);
		//Time Comp:O(mxn) space:O(mxn)
	}

	private static Boolean subsetSumTabulation(int[] arr, int sum, int n) {
		//n in memo->i in tabulation sum in memo->j in tabulation
		//create dp array
		Boolean dp[][]=new Boolean[n+1][sum+1];
		for(int i=0;i<n+1;i++) {
			for(int j=0;j<sum+1;j++) {
				if(i==0 && j>0) {
					dp[i][j]=false;
				}
				if(j==0) {
					dp[i][j]=true;
				}
			}
		}
		
		/*Test your base case
		 * for(int i=0;i<n+1;i++) {
			for(int j=0;j<sum+1;j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}*/
		
		for(int i=1;i<n+1;i++) {
			for(int j=1;j<sum+1;j++) {
				if(arr[i-1]<=j) {
					dp[i][j]=dp[i-1][j-arr[i-1]] || dp[i-1][j];
				}else {
					dp[i][j]=dp[i-1][j];
				}
			}
		}	
		
		
		return dp[n][sum];
	}

	private static Boolean subsetSumMemoization(int[] arr, int sum, int n,Boolean[][] dp) {
		//If sum=0 then always we can have an empty array.SO return true
				if(sum==0) {
					return true;
				}
				//if sum >0 and array is empty then not possible
				if(sum>0 && n==0) {
					return false;
				}
				
				//memoization 
				if(dp[n][sum]!=false) {
					return dp[n][sum];
				}
				if(arr[n-1]<=sum) {
					return dp[n][sum]=subsetSumMemoization(arr, sum-arr[n-1], n-1,dp) 
					|| subsetSumRecursion(arr, sum, n-1);
				}else {
					return dp[n][sum]=subsetSumMemoization(arr, sum, n-1,dp);
				}
	}

	private static Boolean subsetSumRecursion(int[] arr, int sum, int n) {
		//If sum=0 then always we can have an empty array.SO return true
		if(sum==0) {
			return true;
		}
		//if sum >0 and array is empty then not possible
		if(sum>0 && n==0) {
			return false;
		}
		
		if(arr[n-1]<=sum) {
			return subsetSumRecursion(arr, sum-arr[n-1], n-1) 
			|| subsetSumRecursion(arr, sum, n-1);
		}else {
			return subsetSumRecursion(arr, sum, n-1);
		}
	}
}
