package SdeSheetBinarySearch.BinarySearchOn1dArray;

import java.util.Arrays;

public class PeakElement {
    public int findPeakElement(int[] nums) {
        int size = nums.length;
        int start = 0;
        int end = size -1;
        //if array length 1
        if(size ==1) {
            return 0;
        }
        while(start <= end) {
            int mid = start+(end-start)/2;
            //we are not processing first and last element
            if(mid >0 && mid<size -1) {
                if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) {
                    return mid;
                } else if(nums[mid-1] > nums[mid]) {
                    end =  mid-1;
                } else {
                    start = mid+1;
                }
            } else if (mid ==0) {
                if(nums[start] > nums[end]) {
                    return start;
                } else {
                    return end;
                }
            } else if (end == size-1) {
                if(nums[size-1] > nums[size -2]) {
                    return size -1;
                } else {
                    return size -2;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int peak = new PeakElement()
                .findPeakElement(nums);

        System.out.println("The peak element index is: " + peak);
    }
}
