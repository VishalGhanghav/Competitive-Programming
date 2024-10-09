package SdeSheetArrays.hard;

import java.util.Arrays;

import java.util.Arrays;

public class MergeSortedArrays {

    public static void main(String[] args) {
        int[] nums1Brute = {1, 2, 3, 0, 0, 0}; // Array for brute force
        int[] nums2 = {2, 5, 6};
        int m = 3, n = 3;

        int[] nums1Optimal1 = {1, 2, 3, 0, 0, 0}; // Array for Optimal 1


        // 1. Brute Force Approach
        System.out.println("Brute Force Approach:");
        mergeBruteForce(nums1Brute, m, nums2, n);
        System.out.println(Arrays.toString(nums1Brute));

        // 2. Optimal 1: Two Pointers + Sorting
        System.out.println("\nOptimal 1: Two Pointers and Sorting:");
        mergeOptimal1(nums1Optimal1, m, nums2, n);
        System.out.println(Arrays.toString(nums1Optimal1));

        // 3. Optimal 2: Gap Method (Sort in both arrays independently)
        System.out.println("\nOptimal 2: Gap Method (Sorting in nums1 and nums2 separately):");
        int[] nums1Optimal2 = {1,3,5,7}; // Array for Optimal 2 (sorted independently)
        int[] nums2Optimal2 = {0,2,6,8,9}; // Array for Optimal 2 (sorted independently)
        mergeOptimal2(nums1Optimal2, 4, nums2Optimal2, 5);
        System.out.println("nums1: " + Arrays.toString(nums1Optimal2));
        System.out.println("nums2: " + Arrays.toString(nums2Optimal2)); // nums2 should be sorted separately
    }

    // 1. Brute Force Approach
    public static void mergeBruteForce(int[] nums1, int m, int[] nums2, int n) {
        int left = 0, right = 0;
        int[] nums3 = new int[m + n]; // Extra space
        int ind = 0;

        // Merge elements from both arrays
        while (left < m && right < n) {
            if (nums1[left] <= nums2[right]) {
                nums3[ind++] = nums1[left++];
            } else {
                nums3[ind++] = nums2[right++];
            }
        }

        // Copy remaining elements
        while (left < m) nums3[ind++] = nums1[left++];
        while (right < n) nums3[ind++] = nums2[right++];

        // Copy back to nums1
        for (int i = 0; i < (m + n); i++) {
            nums1[i] = nums3[i];
        }
    }

    // 2. Optimal 1: Two Pointers and Sorting
    public static void mergeOptimal1(int[] nums1, int m, int[] nums2, int n) {
        int left = m - 1; // Pointer at the end of nums1's valid elements
        int right = n - 1; // Pointer at the end of nums2
        int ind = m + n - 1; // Pointer at the last position of nums1 array

        // Merge from the back to the front
        while (left >= 0 && right >= 0) {
            if (nums1[left] > nums2[right]) {
                nums1[ind--] = nums1[left--];
            } else {
                nums1[ind--] = nums2[right--];
            }
        }

        // If there are any remaining elements in nums2, add them
        while (right >= 0) {
            nums1[ind--] = nums2[right--];
        }
    }

    // 3. Optimal 2: Gap Method (Sort in both arrays independently)
    public static void mergeOptimal2(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n;
        int gap = (len / 2) + (len % 2); // Initial gap

        while (gap > 0) {
            int left = 0;
            int right = left + gap;

            while (right < len) {
                // Case 1: left in nums1[] and right in nums2[]
                if (left < m && right >= m) {
                    if (nums1[left] > nums2[right - m]) {
                        swap(nums1, nums2, left, right - m);
                    }
                }
                // Case 2: both pointers in nums2[]
                else if (left >= m) {
                    if (nums2[left - m] > nums2[right - m]) {
                        swap(nums2, nums2, left - m, right - m);
                    }
                }
                // Case 3: both pointers in nums1[]
                else {
                    if (nums1[left] > nums1[right]) {
                        swap(nums1, nums1, left, right);
                    }
                }
                left++;
                right++;
            }

            // Reduce the gap for the next pass
            if (gap == 1) break;
            gap = (gap / 2) + (gap % 2);
        }
    }

    // Swap function to swap elements between two arrays
    public static void swap(int[] nums1, int[] nums2, int i, int j) {
        int temp = nums1[i];
        nums1[i] = nums2[j];
        nums2[j] = temp;
    }
}

