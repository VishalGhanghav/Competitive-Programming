package dpMemoizationRecursion;

import java.util.Arrays;

public class EqualSumPartition {
	public static void main(String args[]) {
		int arr[]=new int[] {1,5,11,5};
		int n=arr.length;
		//Equal sum possible if sum of all elements of array is even
		int sum=0;
		for(int i=0;i<n;i++) {
			sum+=arr[i];
		}
		Boolean ans1;
		if(sum%2!=0) {
			 ans1= false;
		}else {
			 ans1=equalSumRecursion(arr,sum/2,n);
		}
		
		System.out.println(ans1);
		//Time Comp:2 power n (tree) (exponential) space:O(n) stack
		//create dp array of n+1 target+1
		Boolean dp[][]=new Boolean[n+1][(sum/2)+1];
		for(Boolean[] row:dp) {
			Arrays.fill(row, false);
		}
		Boolean ans2=equalSumMemoization(arr,(sum/2),n,dp);
		//Time Comp:O(mxn) space:O(mxn)+O(n) stack
		System.out.println(ans2);
		
		Boolean ans3=equalSumTabulation(arr,(sum/2),n);
		
		System.out.println(ans3);
		//Time Comp:O(mxn) space:O(mxn)
	}

	private static Boolean equalSumTabulation(int[] arr, int sum, int n) {
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

	private static Boolean equalSumMemoization(int[] arr, int sum, int n, Boolean[][] dp) {
		if(n==0 && sum>0) {
			return dp[n][sum]=false;
		}
		if(sum==0) {
			return dp[n][sum]=true;
			
		}
		
		if(arr[n-1]<=sum) {
			return dp[n][sum]=equalSumMemoization(arr, sum-arr[n-1], n-1,dp) || 
					equalSumMemoization(arr, sum, n-1,dp);
		}else {
			return dp[n][sum]=equalSumMemoization(arr, sum, n-1,dp);
		}
	}

	private static Boolean equalSumRecursion(int[] arr, int sum, int n) {
		if(n==0 && sum>0) {
			return false;
		}
		if(sum==0) {
			return true;
			
		}
		
		if(arr[n-1]<=sum) {
			return equalSumRecursion(arr, sum-arr[n-1], n-1) || 
					equalSumRecursion(arr, sum, n-1);
		}else {
			return equalSumRecursion(arr, sum, n-1);
		}
	
	}
}
