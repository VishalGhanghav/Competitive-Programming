package SdeSheetBinarySearch.BinarySearchOn1dArray;

public class MinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        int n = nums.length;
        while(start<=end) {
            int mid = start+(end-start)/2;
            int next = (mid+1)%n;
            int prev = (mid -1 +n) % n;
            //if mid is smaller than both next and prev
            if(nums[mid]<= nums[next] && nums[mid]<=nums[prev]) {
                return nums[mid];
            }
            //we are in sorted go to unsorted
            //why not nums[start]<=nums[mid] .This scenario [4,5,6,7,0,1,2].
            //If we go into sorted array after first mid pass.in 0,1,2 we wil go to 1,2 which is not minimum
            //Now with nums[mid<=nums[end] .In 0,1,2 we will go to 0,1
            if(nums[mid]<=nums[end]) {
                end = mid-1;
            } else if(nums[start]<=nums[mid]) {
                start = mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int min = new MinimumInRotatedSortedArray().findMin(nums);

        System.out.println("The minimum element is: " + min);
    }
}
