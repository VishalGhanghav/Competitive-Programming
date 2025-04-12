package SdeSheetBinarySearch.BinarySearchOnAnswer;

public class MinDaysToMakeBouqets {

        // Brute-force approach: O(n * (max - min + 1))
        public static int minDaysBruteForce(int[] bloomDay, int m, int k) {
            long val = (long) m * k;
            int n = bloomDay.length;
            if (n < val) return -1;

            int mini = Integer.MAX_VALUE;
            int maxi = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                mini = Math.min(mini, bloomDay[i]);
                maxi = Math.max(maxi, bloomDay[i]);
            }

            for (int day = mini; day <= maxi; day++) {
                if (isPossibleBrute(bloomDay, day, m, k)) {
                    return day;
                }
            }
            return -1;
        }

        // Binary Search approach: O(n * log(max - min))
        public static int minDaysBinarySearch(int[] bloomDay, int m, int k) {
            long val = (long) m * k;
            int n = bloomDay.length;
            if (n < val) return -1;

            int mini = Integer.MAX_VALUE;
            int maxi = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                mini = Math.min(mini, bloomDay[i]);
                maxi = Math.max(maxi, bloomDay[i]);
            }

            int start = mini;
            int end = maxi;

            while (start <= end) {
                int mid = start + (end - start) / 2;

                if (canMakeBouquets(bloomDay, mid, m, k)) {
                    end = mid - 1;  // Try for smaller day
                } else {
                    start = mid + 1;
                }
            }

            return start;
        }

        // Brute version of isPossible, works with any general implementation
        private static boolean isPossibleBrute(int[] bloomDay, int day, int m, int k) {
            int cnt = 0;
            int bouquets = 0;

            for (int i = 0; i < bloomDay.length; i++) {
                if (bloomDay[i] <= day) {
                    cnt++;
                } else {
                    bouquets += cnt / k;
                    cnt = 0;
                }
            }
            bouquets += cnt / k; // Add remaining
            return bouquets >= m;
        }

        // Optimal isPossible used in Binary Search
        private static boolean canMakeBouquets(int[] days, int currentDay, int requiredBouquets, int flowersPerBouquet) {
            int bouquets = 0;
            int adjacent = 0;

            for (int day : days) {
                if (day <= currentDay) {
                    adjacent++;
                    if (adjacent == flowersPerBouquet) {
                        bouquets++;
                        adjacent = 0;
                        if (bouquets >= requiredBouquets) {
                            return true;
                        }
                    }
                } else {
                    adjacent = 0;
                }
            }

            return bouquets >= requiredBouquets;
        }

        public static void main(String[] args) {
            int[] bloomDay = {1, 10, 3, 10, 2};
            int m = 3;
            int k = 1;

            int resultBrute = minDaysBruteForce(bloomDay, m, k);
            System.out.println("Minimum Days (Brute-force): " + resultBrute);

            int resultBinary = minDaysBinarySearch(bloomDay, m, k);
            System.out.println("Minimum Days (Binary Search): " + resultBinary);
        }

}
