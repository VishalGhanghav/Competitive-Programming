package SdeSheetBinarySearch.BinarySearchOn1dArray;

/*
There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1


Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-104 <= target <= 104
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int start = 0;
        int end = nums.length - 1;

        // Optimal approach
        return searchInSorted(nums, start, end, n, target);
    }

    public int searchBetter(int[] nums, int target) {
        int n = nums.length;
        int start = 0;
        int end = nums.length - 1;

        // Better approach: Find minimum index and search separately
        int minIndex = getMinimum(nums, start, end);
        System.out.println("Minimum at index: " + minIndex);

        int leftSearch = search(nums, target, 0, minIndex - 1);
        if (leftSearch == -1) {
            return search(nums, target, minIndex, n - 1);
        }
        return leftSearch;
    }

    private int searchInSorted(int[] nums, int start, int end, int n, int target) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // Left part is sorted
            if (nums[mid] >= nums[start]) {
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                // Right part is sorted
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    private int search(int[] nums, int target, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    private int getMinimum(int[] nums, int start, int end) {
        int n = nums.length;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int next = (mid + 1) % n;
            int prev = (mid + n - 1) % n;
            if (nums[mid] <= nums[next] && nums[mid] <= nums[prev]) {
                return mid;
            }
            if (nums[mid] <= nums[end]) {
                end = mid - 1;
            } else if (nums[mid] >= nums[start]) {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        SearchInRotatedSortedArray obj = new SearchInRotatedSortedArray();

        // Execute the optimal approach
        int optimalResult = obj.search(nums, target);
        System.out.println("Optimal approach result: " + optimalResult);

        // Execute the better approach separately
        int betterResult = obj.searchBetter(nums, target);
        System.out.println("Better approach result: " + betterResult);
    }
}

