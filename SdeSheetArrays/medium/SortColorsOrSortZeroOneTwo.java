package SdeSheetArrays.medium;

import java.util.Arrays;

public class SortColorsOrSortZeroOneTwo {

    // Brute Force: Simply sort the array
    public void sortColorsBrute(int[] nums) {
        // Brute Force approach: Using built-in sort function
        Arrays.sort(nums);
    }

    // Better Approach: Count the number of 0s, 1s, and 2s, then overwrite the array
    public void sortColorsBetter(int[] nums) {
        int n = nums.length;
        int cnt0 = 0, cnt1 = 0, cnt2 = 0;

        // Count the occurrences of 0s, 1s, and 2s
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                cnt0++;
            } else if (nums[i] == 1) {
                cnt1++;
            } else {
                cnt2++;
            }
        }

        // Overwrite the array based on the counts
        for (int i = 0; i < cnt0; i++) {
            nums[i] = 0;
        }
        for (int i = cnt0; i < cnt0 + cnt1; i++) {
            nums[i] = 1;
        }
        for (int i = cnt0 + cnt1; i < n; i++) {
            nums[i] = 2;
        }
    }

    // Optimal Approach: Dutch National Flag algorithm
    public void sortColorsOptimal(int[] nums) {
        int n = nums.length;
        int low = 0, mid = 0, high = n - 1;

        // Dutch National Flag Algorithm
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    // Helper function to swap elements
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        SortColorsOrSortZeroOneTwo sorter = new SortColorsOrSortZeroOneTwo();

        int[] numsBrute = {2, 0, 2, 1, 1, 0};
        int[] numsBetter = numsBrute.clone();
        int[] numsOptimal = numsBrute.clone();

        // Run Brute Force approach
        sorter.sortColorsBrute(numsBrute);
        System.out.println("Sorted (Brute Force): " + Arrays.toString(numsBrute));

        // Run Better approach
        sorter.sortColorsBetter(numsBetter);
        System.out.println("Sorted (Better): " + Arrays.toString(numsBetter));

        // Run Optimal approach
        sorter.sortColorsOptimal(numsOptimal);
        System.out.println("Sorted (Optimal): " + Arrays.toString(numsOptimal));
    }
}
