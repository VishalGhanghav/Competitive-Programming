package SdeSheetBinarySearch.BinarySearchOn1dArray;

/*
Given a sorted array arr[] and an integer x, find the index (0-based) of the largest element in arr[] that is less than or equal to x. This element is called the floor of x. If such an element does not exist, return -1.

Note: In case of multiple occurrences of ceil of x, return the index of the last occurrence.

Examples

Input: arr[] = [1, 2, 8, 10, 10, 12, 19], x = 5
Output: 1
Explanation: Largest number less than or equal to 5 is 2, whose index is 1.
Input: arr[] = [1, 2, 8, 10, 10, 12, 19], x = 11
Output: 4
Explanation: Largest Number less than or equal to 11 is 10, whose indices are 3 and 4. The index of last occurrence is 4.
Input: arr[] = [1, 2, 8, 10, 10, 12, 19], x = 0
Output: -1
Explanation: No element less than or equal to 0 is found. So, output is -1.
Constraints:
1 ? arr.size() ? 106
1 ? arr[i] ? 106
0 ? x ? arr[n-1]
 */
public class FloorInSortedArray {
    static int findFloor(int[] nums, int target) {
        // write code here
        int start=0;
        int end=nums.length-1;
        int res=-1;
        while(start<=end) {
            int mid = start + (end-start)/2;
            if (nums[mid] <= target) {
                //this is potential result
                res = mid;
                start = mid+1;
            } else {
                //check left
                end = mid-1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findFloor(new int[]{1, 2, 8, 10, 10, 12, 19},9));
        System.out.println(findFloor(new int[]{1, 2, 8, 10, 10, 12, 19},10));//last occurence of same elmt
        System.out.println(findFloor(new int[]{1, 2, 8, 10, 10, 12, 19},11));

    }
}
