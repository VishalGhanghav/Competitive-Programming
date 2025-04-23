package SdeSheetBinarySearch.BinarySearchOnAnswer;

public class KthElementInTwoSortedArrays {
    // ? Brute Force: Merge arrays, return k-th element
    public int findKthElementBrute(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] merged = new int[n1 + n2];
        int i = 0, j = 0, idx = 0;

        while (i < n1 && j < n2) {
            if (nums1[i] <= nums2[j]) {
                merged[idx++] = nums1[i++];
            } else {
                merged[idx++] = nums2[j++];
            }
        }

        while (i < n1) merged[idx++] = nums1[i++];
        while (j < n2) merged[idx++] = nums2[j++];

        return merged[k - 1]; // 1-based index
    }

    // ? Better Approach: Two pointers without extra space
    public int findKthElementBetter(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;
        int i = 0, j = 0, cnt = 0, ans = -1;

        while (i < n1 && j < n2) {
            if (nums1[i] <= nums2[j]) {
                ans = nums1[i++];
            } else {
                ans = nums2[j++];
            }
            cnt++;
            if (cnt == k) return ans;
        }

        while (i < n1) {
            ans = nums1[i++];
            cnt++;
            if (cnt == k) return ans;
        }

        while (j < n2) {
            ans = nums2[j++];
            cnt++;
            if (cnt == k) return ans;
        }

        return ans;
    }

    // ? Optimal Approach: Binary Search on smaller array
    public int findKthElementOptimal(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;

        // Always binary search on the smaller array
        if (n1 > n2) return findKthElementOptimal(nums2, nums1, k);

        int low = Math.max(0, k - n2);      // At least this many from nums1
        int high = Math.min(k, n1);         // At most this many from nums1

        while (low <= high) {
            int cut1 = (low + high) / 2;
            int cut2 = k - cut1;

            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int r1 = (cut1 == n1) ? Integer.MAX_VALUE : nums1[cut1];
            int r2 = (cut2 == n2) ? Integer.MAX_VALUE : nums2[cut2];

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }

        return -1;
    }

    // ? Main method to test all three
    public static void main(String[] args) {
        KthElementInTwoSortedArrays sol = new KthElementInTwoSortedArrays();

        int[] nums1 = {2, 3, 6, 7, 9};
        int[] nums2 = {1, 4, 8, 10};

        int k = 5;

        System.out.println("Brute Kth: " + sol.findKthElementBrute(nums1, nums2, k));
        System.out.println("Better Kth: " + sol.findKthElementBetter(nums1, nums2, k));
        System.out.println("Optimal Kth: " + sol.findKthElementOptimal(nums1, nums2, k));
    }
}
