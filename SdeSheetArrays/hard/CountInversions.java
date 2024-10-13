package SdeSheetArrays.hard;


import java.util.ArrayList;
import java.util.List;

public class CountInversions {

    // Method 1: Brute Force approach to count inversions
    static long inversionCountBruteForce(long arr[]) {
        int n = arr.length;
        long cnt = 0;

        // Two nested loops to check all pairs
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // If arr[i] > arr[j], we found an inversion
                if (arr[i] > arr[j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // Method 2: Merge Sort approach to count inversions
    static long inversionCountMergeSort(long arr[]) {
        int n = arr.length;
        return mergeSort(arr, 0, n - 1);  // Call to merge sort that also counts inversions
    }

    // Merge sort algorithm
    static long mergeSort(long[] arr, int low, int high) {
        long cnt = 0;  // Counter for inversions

        // Base case for recursion
        if (low >= high) {
            return cnt;
        }

        int mid = (low + high) / 2;

        // Sort left half and count inversions
        cnt += mergeSort(arr, low, mid);

        // Sort right half and count inversions
        cnt += mergeSort(arr, mid + 1, high);

        // Merge the two halves and count cross inversions
        cnt += merge(arr, low, mid, high);

        return cnt;
    }

    // Merging two sorted halves and counting inversions during merge
    static long merge(long[] arr, int low, int mid, int high) {
        List<Long> temp = new ArrayList<>();  // Temporary array for merging
        int left = low, right = mid + 1;
        long cnt = 0;  // Counter for inversions

        // Merging process with inversion counting
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left++]);
            } else {
                // arr[left] > arr[right], so we count inversions
                temp.add(arr[right++]);
                cnt += (mid - left + 1);  // All remaining elements in left half are inversions
            }
        }

        // Adding remaining elements from left half (if any)
        while (left <= mid) {
            temp.add(arr[left++]);
        }

        // Adding remaining elements from right half (if any)
        while (right <= high) {
            temp.add(arr[right++]);
        }

        // Copy the merged elements back into original array
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }

        return cnt;
    }

    // Method 3: Global variable approach to count inversions
    static long globalCnt = 0;  // Global variable to store inversion count

    // Wrapper method to reset and call the merge sort method using the global variable
    static long inversionCountGlobal(long arr[]) {
        globalCnt = 0;  // Reset global counter
        mergeSortGlobal(arr, 0, arr.length - 1);
        return globalCnt;  // Return the global inversion count
    }

    // Merge sort using global counter
    static void mergeSortGlobal(long[] arr, int low, int high) {
        if (low >= high) {
            return;  // Base case
        }

        int mid = (low + high) / 2;

        // Sort left and right halves
        mergeSortGlobal(arr, low, mid);
        mergeSortGlobal(arr, mid + 1, high);

        // Merge and count inversions using global variable
        mergeGlobal(arr, low, mid, high);
    }

    // Merge process that updates global counter
    static void mergeGlobal(long[] arr, int low, int mid, int high) {
        List<Long> temp = new ArrayList<>();
        int left = low, right = mid + 1;

        // Same merging logic as above but updating globalCnt
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left++]);
            } else {
                temp.add(arr[right++]);
                globalCnt += (mid - left + 1);  // Increment global inversion counter
            }
        }

        while (left <= mid) {
            temp.add(arr[left++]);
        }

        while (right <= high) {
            temp.add(arr[right++]);
        }

        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    // Main method to test the three approaches
    public static void main(String[] args) {
        long[] arr = {1, 20, 6, 4, 5};

        // Calling brute force method
        System.out.println("Inversion count (Brute Force): " + inversionCountBruteForce(arr.clone()));

        // Calling merge sort method
        System.out.println("Inversion count (Merge Sort): " + inversionCountMergeSort(arr.clone()));

        // Calling global variable method
        System.out.println("Inversion count (Global Variable): " + inversionCountGlobal(arr.clone()));
    }
}


