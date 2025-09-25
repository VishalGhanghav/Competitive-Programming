package SdeSheetDp.src.DpOnSubsequences;

import java.util.Arrays;

import static java.lang.Integer.MAX_VALUE;
//Doesnt handle -ve test cases

public class PartitionForMinSubsetSumDifference {
	public static void main(String args[]) {
		int arr[]=new int[] {1,2,7};
		int n=arr.length;
		int ans1=minimumSubsetSumDifferenceRecursion(arr,n);
		System.out.println(ans1);
		//Time Comp:2 power n (tree) (exponential) space:O(n) stack
		//create dp array of n+1 target+1
		//get total sum of arr
		int totalSum=0;
		for(int i=0;i<n;i++) {
			totalSum+=arr[i];
		}
		int dp[][]=new int[n+1][totalSum+1];
		for(int[] row:dp) {
			Arrays.fill(row, -1);
		}
		int ans2=minimumSubsetSumDifferenceMemoization(arr,n,0,totalSum,dp);
		//Time Comp:O(mxn) space:O(mxn)+O(n) stack
		System.out.println(ans2);
		boolean dp2[][]=new boolean[n+1][totalSum+1];
		int ans3=minimumSubsetSumDifferenceTabulation(arr,n,totalSum,dp2);

		System.out.println(ans3);
		//Time Comp:O(mxn) space:O(mxn)
		

	}

	private static int minimumSubsetSumDifferenceTabulation(int[] arr, int n, int totalSum, boolean[][] dp) {
		for(int i=0;i<=n;i++) {
			dp[i][0] = true;
		}

		for(int i=1;i<=n;i++) {
			for(int j=1;j<=totalSum;j++) {
				boolean notTaken = dp[i-1][j];

				boolean taken = false;
				if(arr[i-1] <= j) {
					taken = dp[i-1][j-arr[i-1]];
				}
				dp[i][j] = taken || notTaken;
			}
		}

		//my dp array is ready.Last row ind == n-1 ie dp[n][0] dp[n][1] ... gives final ans
		// as all emts come {1,2,7}
		int mini = MAX_VALUE;
		for(int sum = 0;sum <= totalSum;sum++) {
			if(dp[n][sum] == true) {
				mini = Math.min(mini, Math.abs(totalSum - 2 * sum));
			}
		}
		return mini;

	}

	private static int minimumSubsetSumDifferenceSpace(int[] arr, int n, int totalSum) {
		boolean[] dp = new boolean[totalSum + 1];

		// Base case: sum=0 is always possible
		dp[0] = true;

		for (int num : arr) {
			boolean[] next = new boolean[totalSum + 1];
			next[0] = true;

			for (int j = 1; j <= totalSum; j++) {
				boolean notTaken = dp[j];
				boolean taken = false;
				if (num <= j) {
					taken = dp[j - num];
				}
				next[j] = taken || notTaken;
			}

			dp = next; // move forward
		}

		int mini = Integer.MAX_VALUE;
		for (int sum = 0; sum <= totalSum; sum++) {
			if (dp[sum]) {
				mini = Math.min(mini, Math.abs(totalSum - 2 * sum));
			}
		}

		return mini;
	}


	private static int minimumSubsetSumDifferenceMemoization(int[] arr, int n,int subsetSum, int totalSum, int[][] dp) {
		if(n==0) {
			return dp[n][subsetSum] = Math.abs(totalSum - 2 * subsetSum);
		}

		if(dp[n][subsetSum] != -1) {
			return dp[n][subsetSum];
		}

		int pick = minimumSubsetSumDifferenceMemoization(arr, n-1, subsetSum + arr[n-1], totalSum, dp);
		int notPick = minimumSubsetSumDifferenceMemoization(arr, n-1, subsetSum, totalSum, dp);

		return dp[n][totalSum] = Math.min(pick, notPick);

	}



	private static int minimumSubsetSumDifferenceRecursion(int[] arr, int n) {
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
