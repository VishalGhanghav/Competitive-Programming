package SdeSheetBinarySearch.BinarySearchOn1dArray;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Given an increasing sorted rotated array arr of distinct integers. The array is right-rotated k times. Find the value of k.
Let's suppose we have an array arr = [2, 4, 6, 9], so if we rotate it by 2 times so that it will look like this:
After 1st Rotation : [9, 2, 4, 6]
After 2nd Rotation : [6, 9, 2, 4]

Examples:

Input: arr = [5, 1, 2, 3, 4]
Output: 1
Explanation: The given array is 5 1 2 3 4. The original sorted array is 1 2 3 4 5. We can see that the array was rotated 1 times to the right.
Input: arr = [1, 2, 3, 4, 5]
Output: 0
Explanation: The given array is not rotated.
Expected Time Complexity: O(log(n))
Expected Auxiliary Space: O(1)

Constraints:
1 <= n <=105
1 <= arri <= 107
 */
public class NumberOfTimesSortedArrayIsRotated {
    public int findKRotation(List<Integer> arr) {
        // Code here
        //The location of mid gives us the no of times array is rotated.
        return findMin(arr);
    }

    public int findMin(List<Integer> nums) {
        int start = 0;
        int end = nums.size()-1;
        int n = nums.size();
        while(start<=end) {
            int mid = start+(end-start)/2;
            int next = (mid+1)%n;
            int prev = (mid -1 +n) % n;
            //if mid is smaller than both next and prev
            if(nums.get(mid)<= nums.get(next) && nums.get(mid)<=nums.get(prev)) {
                return mid;
            }
            //we are in sorted go to unsorted
            //why not nums[start]<=nums.get(mid) .This scenario [4,5,6,7,0,1,2].
            //If we go into sorted array after first mid pass.in 0,1,2 we wil go to 1,2 which is not minimum
            //Now with nums[mid<=nums[end] .In 0,1,2 we will go to 0,1
            if(nums.get(mid)<=nums.get(end)) {
                end = mid-1;
            } else if(nums.get(start)<=nums.get(mid)) {
                start = mid+1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int min = new NumberOfTimesSortedArrayIsRotated()
                .findMin(Arrays.stream(nums).boxed().toList());

        System.out.println("The no of times sorted is: " + min);
    }
}
