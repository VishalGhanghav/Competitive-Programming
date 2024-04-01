package DynamicProgramming.src.dpMemoizationRecursion;

import java.util.Arrays;

public class CountOfSubsetsWithGivenDIfference {
	public static void main(String args[]) {
		int arr[]=new int[] {1,1,2,3};
		int difference=1;
		int n=arr.length;
		int ans1=CountOfSubsetsWithGivenDIfferenceRecursion(arr,difference,n);
		System.out.println(ans1);
		
		//CHange later
		int target=0;
		//Time Comp:2 power n (tree) (exponential) space:O(n) stack
		//create dp array of n+1 target+1
		Boolean dp[][]=new Boolean[n+1][target+1];
		for(Boolean[] row:dp) {
			Arrays.fill(row, false);
		}
		Boolean ans2=CountOfSubsetsWithGivenDIfferenceMemoization(arr,target,n,dp);
		//Time Comp:O(mxn) space:O(mxn)+O(n) stack
		System.out.println(ans2);

		Boolean ans3=CountOfSubsetsWithGivenDIfferenceTabulation(arr,target,n);

		System.out.println(ans3);
		//Time Comp:O(mxn) space:O(mxn)
		

	}

	private static Boolean CountOfSubsetsWithGivenDIfferenceTabulation(int[] arr, int target, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Boolean CountOfSubsetsWithGivenDIfferenceMemoization(int[] arr, int target, int n, Boolean[][] dp) {
		// TODO Auto-generated method stub
		return null;
	}

	private static int CountOfSubsetsWithGivenDIfferenceRecursion(int[] arr, int difference, int n) {
		int sum=0;
		for(int i=0;i<n;i++) {
			sum+=arr[i];
		}
		System.out.println(sum);
		int subsetSum=(sum+difference)/2;
		int countOfSUbsets=subsetSumDiff(arr,n,subsetSum);
		return countOfSUbsets;
	}

	private static int subsetSumDiff(int[] arr, int n, int subsetSum) {
		if(n==0 && subsetSum>0) {
			return 0;
		}
		if(subsetSum==0) {
			return 1;
		}
		if(arr[n-1]<=subsetSum) {
			return subsetSumDiff(arr,n-1,subsetSum-arr[n-1]) +
				subsetSumDiff(arr,n-1,subsetSum);
		}else {
			return subsetSumDiff(arr,n-1,subsetSum);
		}
		
	}
}
