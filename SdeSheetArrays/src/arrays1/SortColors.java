package arrays1;
/*
 Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.

 

Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]
 

Constraints:

n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.
 

Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */
public class SortColors {
	public void sortColors(int[] a) {
        //Brute Use merge sort
       // Arrays.sort(nums);
       /*Better:
       Count of 0's',1 and 2
       /*int cnt0=0;
       int cnt1=0;
       int cnt2=0;
       for(int i=0;i<a.length;i++){
           if(a[i]==0){
               cnt0++;
           }else if(a[i]==1){
               cnt1++;
           }else{
               cnt2++;
           }
       }
       for(int i=0;i<cnt0;i++){
           a[i]=0;
       }
       for(int i=cnt0;i<cnt0+cnt1;i++){
           a[i]=1;
       }
       for(int i=cnt0+cnt1;i<a.length;i++){
           a[i]=2;
       }
    }*/
    /*Optimal:
    Dutch National Flag Algorithm
    4 rules for algo:
    0->low-1: all 0
    low-> mid-1 : all 1
    high->n-1 :all 2
    mid->high-1 :all unsorted(0/1/2)
    

    */
    int low=0,mid=0;
    int high=a.length-1;
    //till we have unsorted part
    while(mid<=high){
        if(a[mid]==0){
            swap(a,mid,low);
            low++;
            mid++;
        }else if(a[mid]==1){
            mid++;
        }else{
            swap(a,mid,high);
            high--;
        }
     }
    }

    public void swap(int[] nums,int mid,int val){
        int temp=nums[mid];
        nums[mid]=nums[val];
        nums[val]=temp;
    }
}
