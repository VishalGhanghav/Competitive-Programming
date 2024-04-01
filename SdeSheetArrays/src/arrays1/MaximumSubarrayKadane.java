package arrays1;
/*
 Given an integer array nums, find the 
subarray
 with the largest sum, and return its sum.

 

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
Example 2:

Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
 

Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarrayKadane {
	public int maxSubArray(int[] nums) {
        int n=nums.length;
        /*
        Brute:
        Check all subarrays
        Time:O(n^3)
        Space:O(1)
        */
       /* int maxSum=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                //Calculate sum each time and find maxSum
                int sum=0;
                for(int k=i;k<=j;k++){
                    sum+=nums[k];
                }
                maxSum=Math.max(maxSum,sum);
            }
        }
        return maxSum;*/
        /*Better:
        We are calculating sum again and again
        k=i->j
        -2
        -2 1
        -2 2 -3
        So we just use older sum and and add just one new element
        */
        /*int maxSum=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            int sum=0;
            for(int j=i;j<n;j++){
                sum+=nums[j];
                maxSum=Math.max(maxSum,sum);
            }
            
        }
        return maxSum;
    }*/
    /*Optimal:Kadane Algorithm
    if sum<0 set sum=0,calulate maxSum and keep changing it according to sum calcullation*/
    int maxSum=Integer.MIN_VALUE;
    int sum=0;
    /*for(int i=0;i<n;i++){
        sum+=nums[i];
        if(sum<0){
            maxSum=Math.max(sum,maxSum);
            sum=0;
        }else{
            maxSum=Math.max(sum,maxSum);
        }
        
        
    }*/
    /*We are asked to print max Sub array*/
    int ansStart=-1;
    int ansEnd=-1;
    int start=-1;
    //We start whenever we get sum=0 //-2 1 -3 4
    //-2 sum=0  thus 1->start 
    //1-3=-2 sum=0 thus 4->start
    for(int i=0;i<n;i++){
        if(sum==0){
            //it is a start
            start=i;
        }
        sum+=nums[i];
        
        if(sum>maxSum){
            maxSum=sum;
            ansStart=start;
            ansEnd=i;
        }
        if(sum<0){
            sum=0;
        }
    }
    for(int i=ansStart;i<=ansEnd;i++){
        System.out.print(nums[i]+" ");
    }
    return maxSum;
    }
}
