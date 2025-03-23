package SdeSheetBinarySearch.BinarySearchOn1dArray;

public class CeilInSortedArray {
    static int findFloor(int[] nums, int target) {
        // write code here
        int start=0;
        int end=nums.length-1;
        int res=-1;
        while(start<=end) {
            int mid = start + (end-start)/2;
            if (nums[mid] >= target)  {
                //this is potential result
                res = mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findFloor(new int[]{1, 2, 8, 10, 10, 12, 19},9));
        System.out.println(findFloor(new int[]{1, 2, 8, 10, 10, 12, 19},10));
        System.out.println(findFloor(new int[]{1, 2, 8, 10, 10, 12, 19},11));

    }
}
