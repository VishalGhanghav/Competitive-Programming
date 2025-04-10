package SdeSheetBinarySearch.BinarySearchOn1dArray;

public class SearchSingleElementInSortedArray {
    public int singleNonDuplicate(int[] nums) {
        //brute:find in array directly O(n)
        //Better:use xor O(N)
        //Optimal :use Binary Search
        int n = nums.length;

        if(n==1) {
            return nums[0];
        }
        //1st elmt is single elmt
        if(nums[0]!=nums[1]) {
            return nums[0];
        }
        //last elmt is single elmt
        if(nums[n-1]!=nums[n-2]) {
            return nums[n-1];
        }

        int start=1,end=n-2;
        while(start<=end) {
            int mid=start + (end-start)/2;
            //if mid not equal to prev and next.is single elmt
            if(nums[mid]!=nums[mid+1] && nums[mid]!=nums[mid-1]) {
                return nums[mid];
            }

            //if in left half.Duplicate sequence is even,odd
            //if i am at odd index prev is same else if i am at even next is same
            if((mid%2 == 0 && nums[mid] == nums[mid+1]) || (mid%2 == 1 && nums[mid] == nums[mid-1] )) {
                //I am left half as per above checks.So single element will be in right half
                start = mid+1;
            } else {
                end =mid-1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,2,3,3,4,5,5,6,6};
        System.out.println(new SearchSingleElementInSortedArray().singleNonDuplicate(nums));
    }
}
