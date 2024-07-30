package SdeSheetDp.src.OneDimensionDp;

import java.util.Arrays;

/*
 You are a professional robber planning to rob houses along a street. 
 Each house has a certain amount of money stashed, the only constraint stopping you from 
 robbing each of them is that adjacent houses have security systems connected and 
 it will automatically contact the police if two adjacent houses were broken into on 
 the same night.

Given an integer array nums representing the amount of money of each house, 
return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
 */
public class HouseRobber {
	public static int rob(int[] nums) {

	       int n=nums.length;
	       //int res=recursive(nums,n-1); 
	       int[] dp=new int[n];
	       Arrays.fill(dp, -1);
	       int res=momoization(nums,n-1,dp); 
	       return res;
	    }

	    private static int momoization(int[] nums, int ind, int[] dp) {
	    	//if only one element in array return its value
	        if(ind==0){
	            return nums[ind];
	        }
	        if(ind<0){
	            return 0;
	        }
	        if(dp[ind]!=-1) {
	        	return dp[ind];
	        }

	        int pick=nums[ind]+momoization(nums,ind-2,dp);
	        int notPick=0+momoization(nums,ind-1,dp);
	        return dp[ind]=Math.max(pick,notPick);
	}

		public int recursive(int[] nums,int ind){
			//if only one element in array return its value
	        if(ind==0){
	            return nums[ind];
	        }
	        if(ind<0){
	            return 0;
	        }

	        int pick=nums[ind]+recursive(nums,ind-2);
	        int notPick=0+recursive(nums,ind-1);
	        return Math.max(pick,notPick);
	    }


	    public static void main(String args[]) {
	        // Input array with elements.
	        int arr[] = {2, 1, 4, 9};
	        
	        // Get the length of the array.
	        int n = arr.length;
	        
	     
	        int result = rob(arr);
	        
	        // Print the result.
	        System.out.println(result);
	    }

}
