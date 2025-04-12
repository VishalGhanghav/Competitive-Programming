package SdeSheetBinarySearch.BinarySearchOnAnswer;

public class KokoEatingBananas {

        // Brute-force approach: O(maxElmt * n)
        // Tries all possible eating speeds from 1 to max in piles
        public static int minEatingSpeedBrute(int[] piles, int h) {
            int maxElmt = getMaxInArray(piles);

            for (int i = 1; i <= maxElmt; i++) {
                int reqTime = getTotalTime(piles, i);
                if (reqTime <= h) {
                    return i;
                }
            }
            return -1;
        }

        // Binary Search approach on the answer: O(n * log(maxElmt))
        public static int minEatingSpeedBinary(int[] piles, int h) {
            int start = 1, end = getMaxInArray(piles);
            int ans = 0;

            while (start <= end) {
                int mid = start + (end - start) / 2;
                int timeReq = getTotalTime(piles, mid);

                if (timeReq <= h) {
                    // Try to minimize eating speed
                    ans = mid;
                    end = mid - 1;
                } else {
                    // Need to eat faster
                    start = mid + 1;
                }
            }

            return ans;
        }

        // Calculates the total time needed to eat all piles at given hourly speed
        private static int getTotalTime(int[] arr, int hourly) {
            int totalTime = 0;
            for (int i = 0; i < arr.length; i++) {
                // Use Math.ceil with double to simulate ceiling division
                totalTime += Math.ceil((double) arr[i] / (double) hourly);
            }
            return totalTime;
        }

        // Finds the maximum number of bananas in any pile
        private static int getMaxInArray(int[] piles) {
            int max = piles[0];
            for (int i = 1; i < piles.length; i++) {
                max = Math.max(max, piles[i]);
            }
            return max;
        }

        public static void main(String[] args) {
            int[] piles = {3, 6, 7, 11};
            int h = 8;

            // Brute-force result
            int brute = minEatingSpeedBrute(piles, h);
            System.out.println("Minimum Eating Speed (Brute-force): " + brute);

            // Binary search result
            int binary = minEatingSpeedBinary(piles, h);
            System.out.println("Minimum Eating Speed (Binary Search): " + binary);
        }

}
