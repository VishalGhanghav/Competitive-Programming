package SdeSheeetStrings.medium;

public class LongestPalindromeSubstring {
        public static void main(String[] args) {
            LongestPalindromeSubstring sol = new LongestPalindromeSubstring();

            String s1 = "babad";
            String s2 = "cbbd";
            String s3 = "racecar";
            String s4 = "a";
            String s5 = "banana";

            System.out.println("Brute Force:");
            System.out.println(sol.longestPalindromeBrute(s1)); // Output: "bab" or "aba"
            System.out.println(sol.longestPalindromeBrute(s2)); // Output: "bb"
            System.out.println(sol.longestPalindromeBrute(s3)); // Output: "racecar"
            System.out.println(sol.longestPalindromeBrute(s4)); // Output: "a"
            System.out.println(sol.longestPalindromeBrute(s5)); // Output: "anana"

            System.out.println("\nCenter Expansion:");
            System.out.println(sol.longestPalindrome(s1)); // Output: "bab" or "aba"
            System.out.println(sol.longestPalindrome(s2)); // Output: "bb"
            System.out.println(sol.longestPalindrome(s3)); // Output: "racecar"
            System.out.println(sol.longestPalindrome(s4)); // Output: "a"
            System.out.println(sol.longestPalindrome(s5)); // Output: "anana"
        }

        public String longestPalindromeBrute(String s) {
        /*
        Brute: check all substrings
        */
            int n = s.length();
            int maxLen = 1;
            String maxStr = s.substring(0, 1);
            for (int i = 0; i < n; i++) {
                // use j<=n because substring is substring(0, index+1)
                for (int j = i + maxLen; j <= n; j++) {
                    if ((j - i) > maxLen && isPalindrome(i, j - 1, s)) {
                        maxLen = j - i;
                        maxStr = s.substring(i, j);
                    }
                }
            }
            return maxStr;
        }

        public String longestPalindrome(String s) {
            // Optimal: Use center approach
            if (s.length() <= 1) {
                return s;
            }
            String maxStr = s.substring(0, 1);
            // No point in checking last element for center as length can never be greater than before
            for (int i = 0; i < s.length() - 1; i++) {
                // Odd length palindrome will have 1 center aba
                String oddStr = expandFromCenter(s, i, i);
                // Even length palindrome will have 2 center baab
                String evenStr = expandFromCenter(s, i, i + 1);

                if (oddStr.length() > maxStr.length()) {
                    maxStr = oddStr;
                }
                if (evenStr.length() > maxStr.length()) {
                    maxStr = evenStr;
                }
            }
            return maxStr;
        }

        private String expandFromCenter(String s, int left, int right) {
            // left goes left, right goes right
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            return s.substring(left + 1, right);
        }

        private boolean isPalindrome(int i, int j, String s) {
            int left = i;
            int right = j;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }

}
