package SdeSheetBinarySearch.BinarySearchOnAnswer;

public class SquareRootOfNumber {
    // Brute-force approach: O(sqrt(n))
    // Incrementally try values until the square exceeds 'n'
    public static int squareRootBruteForce(int n) {
        int res = 1;
        while (res * res <= n) {
            res++;
        }
        // The last value before exceeding square root
        return res - 1;
    }

    // Binary search approach: O(log n)
    // Search for the square root in the range [0, n]
    public static int squareRootBinarySearch(int n) {
        int start = 0, end = n;
        int ans = -1;

        while (start <= end) {
            // Avoid overflow by computing mid this way
            int mid = start + (end - start) / 2;
            int val = mid * mid;

            if (val <= n) {
                // Mid could be a potential answer; try for a larger one
                ans = mid;
                start = mid + 1;
            } else {
                // Mid is too large; reduce the search range
                end = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int n = 26;

        // Run brute force approach
        int bruteForceResult = squareRootBruteForce(n);
        System.out.println("Brute Force Square Root of " + n + ": " + bruteForceResult);

        // Run binary search approach
        int binarySearchResult = squareRootBinarySearch(n);
        System.out.println("Binary Search Square Root of " + n + ": " + binarySearchResult);
    }
}
