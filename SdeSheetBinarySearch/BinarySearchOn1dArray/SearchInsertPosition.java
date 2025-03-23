package SdeSheetBinarySearch.BinarySearchOn1dArray;

/*
Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [1,3,5,6], target = 5
Output: 2
Example 2:

Input: nums = [1,3,5,6], target = 2
Output: 1
Example 3:

Input: nums = [1,3,5,6], target = 7
Output: 4


Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums contains distinct values sorted in ascending order.
-104 <= target <= 104
 */
public class SearchInsertPosition {
    public static int searchInsert(int[] nums, int target) {
        //Thinking :I just thought of pointers at the end of when my binary search
        // is completed and got that end+1 will give ny new position
        int start = 0;
        int end = nums.length -1;
        while(start <= end) {
            int mid = start + (end-start)/2;
            if(nums[mid]==target) {
                return mid;
            } else if(nums[mid] < target) {
                start = mid+1;
            } else {
                end = mid -1;
            }
        }
        return end+1;
    }

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6},5));
        System.out.println(searchInsert(new int[]{1,3,5,6},2));//last occurence of same elmt
        System.out.println(searchInsert(new int[]{1,3,5,6},7));
    }

}
