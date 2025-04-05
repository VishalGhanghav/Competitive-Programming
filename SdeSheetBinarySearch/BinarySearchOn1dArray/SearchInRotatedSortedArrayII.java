package SdeSheetBinarySearch.BinarySearchOn1dArray;
/*
There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

You must decrease the overall operation steps as much as possible.



Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false


Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
nums is guaranteed to be rotated at some pivot.
-104 <= target <= 104


Follow up: This problem is similar to Search in Rotated Sorted Array,
but nums may contain duplicates. Would this affect the runtime complexity? How and why?
 */
public class SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int start = 0;
        int end = nums.length-1;
        //Optima;
        boolean result = searchInSorted(nums,start,end,n,target);
        return result;
    }

    private boolean searchInSorted(int[] nums,int start,int end,int n,int target) {

        //I will check if nums[mid]== target return mid.
        //But if arr[mid]<=target.It means its sorted.SO search here
        //if arr[mid]<=end.
        while(start<=end) {
            int mid = start +(end-start)/2;
            if(nums[mid]== target) {
                return true;
            }
            // Handle duplicates: shrink search space
            if (nums[start] == nums[mid] && nums[mid] == nums[end]) {
                start++;
                end--;
                continue;
            }
            //left parted is sorted
            if(nums[mid] >= nums[start]) {
                if(nums[start]<=target && target<=nums[mid]) {
                    //element exist so keep searching here
                    end=mid-1;
                } else {
                    //element not in this part go right side
                    start = mid+1;
                }
            } else {
                //right part is sorted
                if(nums[mid]<=target && target<=nums[end]) {
                    //element exist so keep searching here
                    start=mid+1;
                } else {
                    //element not in this part go left side
                    end = mid-1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        int[] nums2 = {1,0,1,1,1};

        SearchInRotatedSortedArrayII obj = new SearchInRotatedSortedArrayII();

        // Execute the optimal approach
        boolean optimalResult = obj.search(nums1, target);
        System.out.println("res 1 : " + optimalResult);

        // Execute the better approach separately
        boolean betterResult = obj.search(nums2, target);
        System.out.println("res 2: " + betterResult);
    }
}
