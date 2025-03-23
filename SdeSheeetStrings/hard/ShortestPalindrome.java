package SdeSheeetStrings.hard;

public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        // Reverse the string to compare suffix with prefix
        String reversed = new StringBuilder(s).reverse().toString();

        // Create a combined string with a separator (*) to compute LPS properly
        String combinedPattern = s + "*" + reversed;

        // Compute LPS array for the combined string
        int[] lps = computeLps(combinedPattern);

        // The last value in LPS array gives the length of the longest palindromic prefix
        int longestPalindromicPrefix = lps[lps.length - 1];

        // Extract the non-palindromic suffix and reverse it
        String prefixToAdd = new StringBuilder(s.substring(longestPalindromicPrefix)).reverse().toString();

        // Construct the shortest palindrome by adding the reversed suffix in front
        return prefixToAdd + s;
    }

    private int[] computeLps(String pattern) {
        int n = pattern.length();
        int[] lps = new int[n];
        int i = 0, j = 1;

        while (j < n) {
            while (i > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                i = lps[i - 1]; // Use previously computed LPS to avoid redundant comparisons
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                i++; // Extend matching prefix
            }

            lps[j] = i; // Store length of longest proper prefix which is also a suffix
            j++;
        }
        return lps;
    }

    public static void main(String[] args) {
        ShortestPalindrome sp = new ShortestPalindrome();

        // Test cases
        System.out.println(sp.shortestPalindrome("aacecaaa")); // Expected: "aaacecaaa"
        System.out.println(sp.shortestPalindrome("abcd"));    // Expected: "dcbabcd"
        System.out.println(sp.shortestPalindrome("pbabcd"));  // Expected: "dcbabpbabcd"
    }
}
