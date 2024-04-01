package arrays1;
/*
 A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.

 

Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]
Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]
Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100
 */
public class NextPermutation {
	 public void nextPermutation(int[] nums) {
	        int len=nums.length;
	        int ind=-1;
	        //Find the dip
	        for(int i=len-2;i>=0;i--){
	            if(nums[i]<nums[i+1]){
	                ind=i;
	                break;
	            }
	        }
	        System.out.println("ind:"+ind);
	        if(ind==-1){
	            //reverse array start=0 index
	            reverse(nums,0);
	        }else{
	            

	            //swap the ind element and smallest element in remaining array
	            for(int i=len-1;i>=ind;i--){
	                if(nums[ind]<nums[i]){
	                    swap(nums,ind,i);
	                    break;
	                }
	            }
	            for(int i=0;i<len;i++){
	                System.out.print(nums[i]+" ");
	            }
	            //reverse elements after index
	            reverse(nums,ind+1);
	        }
	    }

	    public void swap(int[] nums,int ind,int i){
	        int temp=nums[ind];
	        nums[ind]=nums[i];
	        nums[i]=temp;
	    }

	    public void reverse(int[] nums ,int start){
	        int i=start;
	        int j=nums.length-1;
	        while(i<j){
	            swap(nums,i,j);
	            i++;
	            j--;
	        }
	    } 
}
