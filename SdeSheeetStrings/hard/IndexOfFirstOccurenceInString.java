package SdeSheeetStrings.hard;

public class IndexOfFirstOccurenceInString {

        // 1. Brute Force Approach (Character Comparison)
        public static int bruteForceSearch(String text, String pattern) {
            int m = text.length();
            int n = pattern.length();

            for (int i = 0; i <= m - n; i++) { // Iterate over all possible starting positions
                int j;
                for (j = 0; j < n; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break; // Mismatch found, move to next position
                    }
                }
                if (j == n) return i; // Pattern found
            }
            return -1; // Pattern not found
        }

        // 2. Sliding Window Approach (Using substring comparison)
        public static int slidingWindowSearch(String text, String pattern) {
            int m = text.length();
            int n = pattern.length();

            for (int i = 0, j = n; j <= m; i++, j++) { // Iterate over window
                if (text.substring(i, j).equals(pattern)) { // Compare substrings
                    return i; // Pattern found
                }
            }
            return -1; // Pattern not found
        }

        // 3. KMP Algorithm for Pattern Searching (Optimized)
        public static int kmpSearch(String text, String pattern) {
            int m = text.length();
            int n = pattern.length();
            int[] lps = computeLps(pattern, n); // Compute LPS array

            int i = 0; // Pointer for text
            int j = 0; // Pointer for pattern

            while (i < m) {
                while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                    j = lps[j - 1]; // Move j using LPS array
                }
                if (text.charAt(i) == pattern.charAt(j)) {
                    j++; // Move pattern pointer
                }
                i++; // Move text pointer

                if (j == n) {
                    return i - n; // Pattern found, return start index
                }
            }
            return -1; // Pattern not found
        }

        // Compute LPS (Longest Prefix Suffix) array for KMP
        private static int[] computeLps(String pattern, int n) {
            int[] lps = new int[n];
            int i = 0, j = 1;

            while (j < n) {
                while (i > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                    i = lps[i - 1]; // Reset i using LPS values
                }
                if (pattern.charAt(i) == pattern.charAt(j)) {
                    i++; // Increase matching prefix length
                }
                lps[j] = i; // Store prefix length
                j++;
            }
            return lps;
        }

        public static void main(String[] args) {
            String text = "mississippi";
            String pattern = "issip";

            System.out.println("Brute Force Index: " + bruteForceSearch(text, pattern));
            System.out.println("Sliding Window Index: " + slidingWindowSearch(text, pattern));
            System.out.println("KMP Index: " + kmpSearch(text, pattern));
        }

}
