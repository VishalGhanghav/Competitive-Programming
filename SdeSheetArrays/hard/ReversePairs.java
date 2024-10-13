package SdeSheetArrays.hard;

import java.util.*;

public class ReversePairs {

    // Approach 1: Brute Force to count reverse pairs
    public int reversePairsBruteForce(int[] nums) {
        int cnt = 0;
        int n = nums.length;

        // Nested loops to check all pairs (i, j) where i < j
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // Check if nums[i] > 2 * nums[j] (using long to prevent overflow)
                if ((long) nums[i] > 2 * (long) nums[j]) {
                    cnt++;  // Increment count if a valid pair is found
                }
            }
        }

        return cnt;  // Return the total count of reverse pairs
    }

    // Approach 2: Merge Sort with global variable
    static int globalCnt = 0;  // Global counter for reverse pairs

    public int reversePairsGlobal(int[] nums) {
        globalCnt = 0;  // Reset global counter
        mergeSortGlobal(nums, 0, nums.length - 1);
        return globalCnt;  // Return the count from the global variable
    }

    private void mergeSortGlobal(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) / 2;
        mergeSortGlobal(arr, low, mid);
        mergeSortGlobal(arr, mid + 1, high);
        countPairsGlobal(arr, low, mid, high);
        mergeGlobal(arr, low, mid, high);
    }

    private void mergeGlobal(int[] arr, int low, int mid, int high) {
        List<Integer> temp = new ArrayList<>();
        int left = low, right = mid + 1;

        // Merging two sorted subarrays
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left++]);
            } else {
                temp.add(arr[right++]);
            }
        }

        while (left <= mid) {
            temp.add(arr[left++]);
        }

        while (right <= high) {
            temp.add(arr[right++]);
        }

        // Copy sorted elements back to the original array
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    private void countPairsGlobal(int[] arr, int low, int mid, int high) {
        int right = mid + 1;
        for (int i = low; i <= mid; i++) {
            while (right <= high && (long) arr[i] > 2 * (long) arr[right]) {
                right++;
            }
            globalCnt += right - (mid + 1);  // Count valid pairs
        }
    }

    // Approach 3: Merge Sort without using a global variable
    public int reversePairsMergeSort(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] arr, int low, int high) {
        int cnt = 0;
        if (low >= high) {
            return cnt;
        }
        int mid = (low + high) / 2;
        cnt += mergeSort(arr, low, mid);
        cnt += mergeSort(arr, mid + 1, high);
        cnt += countPairs(arr, low, mid, high);
        merge(arr, low, mid, high);
        return cnt;
    }

    private void merge(int[] arr, int low, int mid, int high) {
        List<Integer> temp = new ArrayList<>();
        int left = low, right = mid + 1;

        // Merging two sorted subarrays
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left++]);
            } else {
                temp.add(arr[right++]);
            }
        }

        while (left <= mid) {
            temp.add(arr[left++]);
        }

        while (right <= high) {
            temp.add(arr[right++]);
        }

        // Copy sorted elements back to the original array
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    private int countPairs(int[] arr, int low, int mid, int high) {
        int cnt = 0;
        int right = mid + 1;

        // Counting valid pairs where arr[i] > 2 * arr[right]
        for (int i = low; i <= mid; i++) {
            while (right <= high && (long) arr[i] > 2 * (long) arr[right]) {
                right++;
            }
            cnt += right - (mid + 1);
        }

        return cnt;
    }

    // Main method to test all three approaches
    public static void main(String[] args) {
        ReversePairs rp = new ReversePairs();
        int[] nums = {1, 3, 2, 3, 1};

        // Calling the Brute Force approach
        System.out.println("Reverse Pairs (Brute Force): " + rp.reversePairsBruteForce(nums.clone()));

        // Calling the Merge Sort with Global variable approach
        System.out.println("Reverse Pairs (Global Variable): " + rp.reversePairsGlobal(nums.clone()));

        // Calling the Merge Sort without using global variable approach
        System.out.println("Reverse Pairs (Merge Sort): " + rp.reversePairsMergeSort(nums.clone()));
    }
}

