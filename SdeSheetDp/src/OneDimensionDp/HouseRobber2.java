package SdeSheetDp.src.OneDimensionDp;

import java.util.Arrays;

/*
 You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
Example 2:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 3:

Input: nums = [1,2,3]
Output: 3
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
 */
public class HouseRobber2 {

		public static int rob(int[] nums) {
	        int[] nums1=new int[nums.length-1];
	        int[] nums2=new int[nums.length-1];
	        int j=0,k=0;
	        if(nums.length==1){
	            return nums[0];
	        }
	        for(int i=0;i<nums.length;i++){
	            if(i!=nums.length-1){
	                nums1[j]=nums[i];
	                j++;
	            }
	            if(i!=0){
	                nums2[k]=nums[i];
	                k++;
	            }
	            
	        }
	        
	        int n=nums.length;
		       //int res=recursive(nums,n-1); 
		       int[] dp1=new int[n];
		       Arrays.fill(dp1, -1);
		       int res1=momoization(nums1,n-2,dp1); 
	            int[] dp2=new int[n];
		       Arrays.fill(dp2, -1);
	           int res2=momoization(nums2,n-2,dp2); 
	           int res=Math.max(res1,res2);
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

		/*private int tbSolve(int[] nums,int n,int[] dp) {
        dp[0] = nums[0];
        int res = 0;
        for(int i=1;i<n;i++) {
            int notPick = dp[i-1];
            //I can only pick i>1.As dp[i-2] for i=1 will be -ve.
            //Also why not use MIN_VALUE.Because for ip:[1,2].We will get 1 as ans
            int pick = nums[i];
            if(i>1) {
                pick = dp[i-2] + nums[i];
            }
            dp[i] = Math.max(pick, notPick);
        }
        return dp[n-1];
    }*/

	/*private int spaceSolve(int[] nums,int n) {
		//Observation: we only need dp[ind] dp[ind-1] and dp[ind-2] back.
		//Why not have variables

		int prev = nums[0];
		int prev2 = 0;
		for(int i=1;i<n;i++) {
			//not pick
			int notPick = 0+ nums[i-1];
			//pick
			int pick = nums[i];
			if(i>1) {
				pick = pick + nums[i-2];
			}

			nums[i] = Math.max(pick,notPick);
			prev2 = prev;
			prev = nums[i];
		}
		return nums[n-1];

	}*/

	    public static void main(String args[]) {
	        // Input array with elements.
	        int arr[] = {1,2,3,1};
	        
	        // Get the length of the array.
	        int n = arr.length;
	        
	     
	        int result = rob(arr);
	        
	        // Print the result.
	        System.out.println(result);
	    }

}
