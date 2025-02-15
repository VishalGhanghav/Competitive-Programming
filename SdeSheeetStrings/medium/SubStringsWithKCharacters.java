package SdeSheeetStrings.medium;

import java.util.HashMap;
import java.util.Map;

public class SubStringsWithKCharacters {
    /*
        atMostK(str, k): This function counts all substrings that contain at most k
        distinct characters.
        atMostK(str, k - 1): This function counts all substrings that contain at most k - 1
        distinct characters.
        The key observation here is:

        Substrings with exactly k distinct characters can be found by subtracting those
        with at most k - 1 from those with at most k.
        Formula:
        exactlyK(str,k)=atMostK(str,k)?atMostK(str,k?1)
    */
    int countSubstr(String str, int k) {
        // your code here
        //Plan: We put in the map.And whenever map size is greater than k
        //we will remove from start.DO this till map.size>k
        return atMostK(str, k) - atMostK(str, k - 1);
    }

    private int atMostK(String str, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        int i = 0, j = 0;

        while (j < str.length()) {
            int value = map.getOrDefault(str.charAt(j), 0);
            map.put(str.charAt(j), value + 1);

            // Now whenever map size > k we will remove from start and adjust the map
            while (map.size() > k) {
                Character leftChar = str.charAt(i);
                map.put(leftChar, map.get(leftChar) - 1);

                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                i++;
            }

            // To get the substrings ending at j, we can just check the size of the window
            count += (j - i + 1);
            j++;
        }
        return count;
    }

    // Main method to test the function
    public static void main(String[] args) {
        SubStringsWithKCharacters sc = new SubStringsWithKCharacters();

        // Example test cases
        System.out.println(sc.countSubstr("aba", 2)); // Expected Output: 3
        System.out.println(sc.countSubstr("aabcbc", 2)); // Example case
        System.out.println(sc.countSubstr("abcabc", 3)); // Example case
    }
}
