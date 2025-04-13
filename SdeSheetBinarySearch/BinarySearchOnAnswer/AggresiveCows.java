package SdeSheetBinarySearch.BinarySearchOnAnswer;

import java.util.Arrays;

public class AggresiveCows {

        // Brute-force method
        public static int aggressiveCowsBrute(int[] stalls, int k) {
            int n = stalls.length;
            // Sort the array to place cows in increasing order of stall positions
            Arrays.sort(stalls);
            int mini = stalls[0];
            int maxi = stalls[n - 1];

            // Brute-force:
            // Try every minimum distance from 1 to (max - min), and check if cows can be placed
            for (int i = 1; i <= maxi - mini; i++) {
                if (!canPlaceCows(stalls, k, i)) {
                    // As soon as we find a distance where cows can't be placed, return (i - 1)
                    return i - 1;
                }
            }
            // If all distances up to max-min are possible, return max-min
            return maxi - mini;
        }

        // Optimal: Binary Search on Answer (maximum minimum distance)
        public static int aggressiveCows(int[] stalls, int k) {
            int n = stalls.length;
            // Sort the array to work with increasing stall positions
            Arrays.sort(stalls);
            int mini = stalls[0];
            int maxi = stalls[n - 1];

            // Possible minimum distance can range from 1 to (max - min)
            int start = 1;
            int end = maxi - mini;

            while (start <= end) {
                int mid = start + (end - start) / 2;

                if (canPlaceCows(stalls, k, mid)) {
                    // If we can place cows with this minimum distance, try for a bigger one
                    //ans=mid
                    start = mid + 1;
                } else {
                    // Otherwise, try smaller distances
                    end = mid - 1;
                }
            }
            // After the loop, 'end' will be the largest minimum distance we can achieve
            return end;//return ans
        }

        // Helper method to check if cows can be placed at given minimum distance
        private static boolean canPlaceCows(int[] stalls, int k, int dist) {
            int cowsCount = 1; // Place first cow in the first stall
            int lastCow = stalls[0];

            for (int i = 1; i < stalls.length; i++) {
                if (stalls[i] - lastCow >= dist) {
                    cowsCount++;
                    lastCow = stalls[i]; // Update position of last placed cow
                }
                if (cowsCount >= k) {
                    return true; // Able to place all cows
                }
            }
            return false;
        }

        // Main method to test both approaches
        public static void main(String[] args) {
            int[] stalls = {1, 2, 8, 4, 9};
            int cows = 3;

            int bruteResult = aggressiveCowsBrute(stalls.clone(), cows);
            System.out.println("Brute-force Result: " + bruteResult);

            int optimalResult = aggressiveCows(stalls.clone(), cows);
            System.out.println("Binary Search Result: " + optimalResult);
        }

}
