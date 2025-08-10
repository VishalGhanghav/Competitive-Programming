package SdeSheetTwoPointerSliding.medium;

import java.util.HashMap;
import java.util.Map;

public class NumberOfSubstringsWithAllChars {

    // Brute: TC: O(N^2), SC: O(K)
    public static int numberOfSubstringsBrute(String s) {
        int n = s.length();
        int k = 3; // as per question
        int noOfSubstrings = 0;

        // Brute compare map size and check all elements
        // question says k=3
        for (int i = 0; i < n; i++) {
            Map<Character, Integer> map = new HashMap<>();
            for (int j = i; j < n; j++) {
                char ch = s.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                if (map.size() == k) {
                    noOfSubstrings++;
                }
            }
        }
        return noOfSubstrings;
    }

    // Optimal: TC: O(N), SC: O(K)
    public static int numberOfSubstringsOptimal(String s) {
        int n = s.length();
        int k = 3; // as per question
        int noOfSubstrings = 0;
        Map<Character, Integer> map = new HashMap<>();

        // Optimal: We can slide the window
        // we maintain a window [i, j] that:
        // expands by moving j right
        // shrinks by moving i right when we already have all characters
        // Key insight:
        // When the window [i, j] contains all 3 characters:
        // ? Every substring starting at i and ending anywhere from j to n-1 is valid.
        // Why?
        // Because adding more characters to the right won’t remove the fact that all 3 are present.

        int i = 0, j = 0;
        while (j < n) {
            char ch = s.charAt(j);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            if (map.size() < k) {
                j++;
            } else {
                while (map.size() == k) {
                    noOfSubstrings += n - j; // count all substrings from current window
                    // Shrink from left
                    char leftChar = s.charAt(i);
                    map.put(leftChar, map.get(leftChar) - 1);
                    if (map.get(leftChar) == 0) {
                        map.remove(leftChar);
                    }
                    i++;
                }
                j++;
            }
        }
        return noOfSubstrings;
    }

    public static void main(String[] args) {
        String s = "abcabc";
        System.out.println("Brute: " + numberOfSubstringsBrute(s));     // Expected: 10
        System.out.println("Optimal: " + numberOfSubstringsOptimal(s)); // Expected: 10
    }
}