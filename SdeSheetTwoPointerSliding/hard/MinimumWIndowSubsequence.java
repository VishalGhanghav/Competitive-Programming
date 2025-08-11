package SdeSheetTwoPointerSliding.hard;

public class MinimumWIndowSubsequence {
    // Two-pointer approach for Minimum Window Subsequence problem
    // TC: O(|s| * |t|) in worst case; SC: O(1)
    public static String minWindow(String s, String t) {
        // Write your code here
        //Following a diff two pointer approach:
        // 1. Move both ahead to find subsequence and get end point
        // 2. Move backward to get start point
        // 3. Calculate min(endPoint - startPoint + 1) to get minWindowSub
        // 4. We can get even smaller so move i ahead and repeat

        int m = s.length();
        int n = t.length();
        int i = 0, j = 0, minLen = Integer.MAX_VALUE;
        String res = "";

        while (i < m) {
            // Step 1: Move forward until we find the whole subsequence t in s
            while (i < m) {
                // if same found we can move j
                if (s.charAt(i) == t.charAt(j)) {
                    j++;
                    if (j == n) {
                        // j reached end so break or we get NPE in s.charAt(j)
                        break;
                    }
                }
                // i moves in both cases
                i++;
            }

            if (j < n) {
                // we didn't find elements in subsequence means
                // we won't find it in future as well so break
                break;
            }

            // Step 2: Move backward to get start where subsequence found
            // We have already got end as i
            int end = i;
            j = n - 1;
            while (i >= 0) {

                // if same found we can move j
                if (s.charAt(i) == t.charAt(j)) {
                    j--;
                    if (j < 0) {
                        // j reached start(-1) so break or we get NPE in s.charAt(j)
                        break;
                    }
                }
                // i moves in both cases
                i--;
            }

            // Step 3: We can get window by end - i + 1
            // get MinWindow
            if ((end - i + 1) < minLen) {
                minLen = end - i + 1;
                res = s.substring(i, end + 1);
                System.out.println("Updated min window: \"" + res + "\" (len=" + minLen + ")");
            }

            // Step 4: Move i to search for next possible window
            i++;
            j = 0; // reset j for next search
        }
        return res;
    }

    public static void main(String[] args) {
        // Test cases for the minWindow subsequence approach
        String[][] tests = {
                {"abcdebdde", "bde"},     // Example from Leetcode 727
                {"jmeqksfrsdcmsiwvaovztaqenprpvnbstl", "u"}, // No subsequence exists
                {"ADOBECODEBANC", "ABC"}, // Similar to minWindow substring but here t is subsequence
                {"fgrqsqsnodwmxzkzxwqegkndaa", "kzed"}, // Larger case
                {"abc", "abc"}            // Exact match
        };

        for (String[] test : tests) {
            String s = test[0];
            String t = test[1];
            System.out.println("\n=== Test s=\"" + s + "\", t=\"" + t + "\" ===");
            String ans = minWindow(s, t);
            System.out.println("Result: \"" + ans + "\"");
        }
    }
}
