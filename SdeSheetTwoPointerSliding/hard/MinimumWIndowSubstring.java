package SdeSheetTwoPointerSliding.hard;

import java.util.HashMap;
import java.util.Map;

public class MinimumWIndowSubstring {

        // Brute Force: TC: O(|s|^3 * |t|), SC: O(|t|)
        // Generate all substrings of s, check if they contain all characters of t using frequency map
        public String minWindowBruteForce(String s, String t) {
            int n = s.length();
            String res = "";
            int minLength = Integer.MAX_VALUE;

            for (int start = 0; start < n; start++) {
                for (int end = start; end < n; end++) {
                    String sub = s.substring(start, end + 1);
                    if (containsAllChars(sub, t)) {
                        if (sub.length() < minLength) {
                            minLength = sub.length();
                            res = sub;
                        }
                    }
                }
            }
            return res;
        }

        // Helper method to check if substring contains all characters of t
        private boolean containsAllChars(String sub, String t) {
            Map<Character, Integer> freqMap = new HashMap<>();
            for (char ch : t.toCharArray()) {
                freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
            }
            for (char ch : sub.toCharArray()) {
                if (freqMap.containsKey(ch)) {
                    freqMap.put(ch, freqMap.get(ch) - 1);
                }
            }
            for (int val : freqMap.values()) {
                if (val > 0) return false;
            }
            return true;
        }

        // Optimal: TC: O(|s| + |t|), SC: O(|t|)
        // Sliding window with frequency map to track required characters.
        // Expand window with j, shrink from i when all characters are matched to find minimum window.
        public String minWindow(String s, String t) {
            //Observation:
            //We can put all elements from t in map and reduce from map every time we find it in s.
            // This way, 'cnt' will track how many unique characters from t still need to be matched.
            Map<Character, Integer> freqMap = new HashMap<>();
            for (char ch : t.toCharArray()) {
                freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
            }

            int n = s.length();
            int i = 0, j = 0;
            int cnt = freqMap.size(); // Number of unique characters in t that need to be matched
            String res = "";
            int minLength = Integer.MAX_VALUE;

            while (j < n) {
                char currChar = s.charAt(j);
                // If current char is part of t, decrease its required frequency in the map
                if (freqMap.containsKey(currChar)) {
                    freqMap.put(currChar, freqMap.get(currChar) - 1);
                    // When frequency hits exactly 0, it means all occurrences of this char are matched
                    if (freqMap.get(currChar) == 0) {
                        cnt--;
                    }
                }

                // Debug: Print current state
                System.out.println("Window: [" + i + "," + j + "] => \"" + s.substring(i, j + 1) + "\" | Map: " + freqMap + " | cnt=" + cnt);

                // If not all required chars are matched, expand the window
                if (cnt > 0) {
                    j++;
                }
                // When all chars matched (cnt == 0), try shrinking window from left
                else if (cnt == 0) {
                    //I need to get minWindow Substring
                    while (cnt == 0) {
                        //j-i+1 gives the window
                        //We should store the minWindow substring to get the minimum possible length
                        if (j - i + 1 < minLength) {
                            res = s.substring(i, j + 1);
                            minLength = j - i + 1;
                        }

                        // Shrink the window now
                        char leftChar = s.charAt(i);
                        // If the left char was part of t, restore its frequency
                        // If its frequency becomes positive, it means we're missing this char again
                        if (freqMap.containsKey(leftChar)) {
                            freqMap.put(leftChar, freqMap.get(leftChar) + 1);
                            if (freqMap.get(leftChar) > 0) {
                                cnt++;
                            }
                        }
                        i++;
                    }
                    j++;
                }
            }
            return res;
        }

        // Main method to test both versions
        public static void main(String[] args) {
            MinimumWIndowSubstring obj = new MinimumWIndowSubstring();

            String s = "ADOBECODEBANC";
            String t = "ABC";

            System.out.println("=== Brute Force ===");
            String bruteAns = obj.minWindowBruteForce(s, t);
            System.out.println("Brute Force Result: " + bruteAns);

            System.out.println("\n=== Optimal (with debug) ===");
            String optAns = obj.minWindow(s, t);
            System.out.println("Optimal Result: " + optAns);
        }
}
