package SdeSheetTwoPointerSliding.hard;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringAtMostKDistinctChars {
    // Brute: TC: O(N^2), SC: O(K)
    // Try all substrings and count distinct characters using frequency map
    public static int kDistinctCharsBrute(int k, String str) {
        int n = str.length();
        int maxWindow = 0;
        // I can think of having frequency and map, size() <= k gives us ans
        for (int i = 0; i < n; i++) {
            Map<Character, Integer> freqMap = new HashMap<>();
            for (int j = i; j < n; j++) {
                char currChar = str.charAt(j);
                freqMap.put(currChar, freqMap.getOrDefault(currChar, 0) + 1);
                // If freqMap.size() <= k then we can get longest substring
                if (freqMap.size() <= k) {
                    maxWindow = Math.max(maxWindow, j - i + 1);
                } else {
                    break;
                }
            }
        }
        return maxWindow;
    }

    // Optimal: TC: O(N), SC: O(K)
    // We can slide window based on map size
    public static int kDistinctCharsOptimal(int k, String str) {
        int n = str.length();
        int maxWindow = 0;
        int i = 0, j = 0;
        Map<Character, Integer> freqMap = new HashMap<>();
        while (j < n) {
            char currChar = str.charAt(j);
            freqMap.put(currChar, freqMap.getOrDefault(currChar, 0) + 1);
            // If freqMap.size() <= k I can get possible ans
            if (freqMap.size() <= k) {
                maxWindow = Math.max(maxWindow, j - i + 1);
                j++;
            } else {
                // Till freqMap.size is greater than k, I reduce window size from start
                while (freqMap.size() > k) {
                    char leftChar = str.charAt(i);
                    freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                    if (freqMap.get(leftChar) == 0) {
                        freqMap.remove(leftChar);
                    }
                    i++;
                }
                maxWindow = Math.max(maxWindow, j - i + 1);
                j++;
            }
        }
        return maxWindow;
    }

    public static void main(String[] args) {
        String str = "eceba";
        int k = 2;
        System.out.println("Brute: " + kDistinctCharsBrute(k, str));     // Expected: 3 ("ece")
        System.out.println("Optimal: " + kDistinctCharsOptimal(k, str)); // Expected: 3
    }
}
