package SdeSheetBinarySearch.BinarySearchOnAnswer;

public class SmallestDivisorSmallerThanThreshold {

        // Brute-force approach: O(maxi * n)
        public static int smallestDivisorBrute(int[] nums, int threshold) {
            int maxi = getMax(nums);

            for (int i = 1; i <= maxi; i++) {
                int res = getTotalWithDivisor(nums, i);
                if (res <= threshold) {
                    return i;
                }
            }

            return -1;
        }

        // Binary Search on answer: O(n * log(max))
        public static int smallestDivisorBinary(int[] nums, int threshold) {
            int maxi = getMax(nums);
            int start = 1;
            int end = maxi;
            int ans = 0;

            while (start <= end) {
                int mid = start + (end - start) / 2;
                int res = getTotalWithDivisor(nums, mid);

                if (res <= threshold) {
                    ans = mid;          // Try to minimize divisor
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            return ans;
        }

        // Helper to get the total after dividing each element with divisor and taking ceil
        private static int getTotalWithDivisor(int[] nums, int divisor) {
            int total = 0;
            for (int num : nums) {
                total += Math.ceil((double) num / divisor);
            }
            return total;
        }

        // Utility to get max value in array
        private static int getMax(int[] nums) {
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                max = Math.max(max, nums[i]);
            }
            return max;
        }

        public static void main(String[] args) {
            int[] nums = {1, 2, 5, 9};
            int threshold = 6;

            int brute = smallestDivisorBrute(nums, threshold);
            System.out.println("Smallest Divisor (Brute-force): " + brute);

            int binary = smallestDivisorBinary(nums, threshold);
            System.out.println("Smallest Divisor (Binary Search): " + binary);
        }
}
