package SdeSheetTwoPointerSliding.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {
    // TC: O(N^2), SC: O(26) ~ O(1)
    public static int characterReplacementBrute(String s, int k) {
        int n = s.length();
        int maxWindow = 0;

        // Brute:
        // Put in map each time. Calculate maxFreqInCurrWindow character.
        // Each time check if no of replacement = windowSize - maxFreqInCurrWindow <= k then its valid
        // else invalid
        for (int i = 0; i < n; i++) {
            Map<Character, Integer> map = new HashMap<>();
            int maxFreqInCurrWindow = 0;

            for (int j = i; j < n; j++) {
                char currChar = s.charAt(j);
                map.put(currChar, map.getOrDefault(currChar, 0) + 1);
                maxFreqInCurrWindow = Math.max(maxFreqInCurrWindow, map.get(currChar));

                int window = j - i + 1;
                int noOfReplacement = window - maxFreqInCurrWindow;

                if (noOfReplacement > k) {
                    break;
                }

                // if no of replacement <= k .We check if its maxWindow
                maxWindow = Math.max(maxWindow, window);
            }
        }
        return maxWindow;
    }

    // TC: O(N), SC: O(26) ~ O(1)
    public static int characterReplacementOptimal(String s, int k) {
        int n = s.length();
        int maxWindow = 0;
        Map<Character, Integer> map = new HashMap<>();

        // Optimal: Since there are repetitions, I try to think how we can get within a window
        int maxFreqInCurrWindow = 0;
        int i = 0, j = 0;

        while (j < n) {
            char currChar = s.charAt(j);
            map.put(currChar, map.getOrDefault(currChar, 0) + 1);
            maxFreqInCurrWindow = Math.max(maxFreqInCurrWindow, map.get(currChar));

            int noOfReplacement = (j - i + 1) - maxFreqInCurrWindow;

            if (noOfReplacement <= k) {
                maxWindow = Math.max(maxWindow, j - i + 1);
                j++;
            } else {
                // Shrink from start if replacements exceed k
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                i++;
                j++;
            }
        }
        return maxWindow;
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;

        System.out.println("Brute: " + characterReplacementBrute(s, k));     // Expected: 4
        System.out.println("Optimal: " + characterReplacementOptimal(s, k)); // Expected: 4
    }
}
