package SdeSheetTwoPointerSliding.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    // Brute
    // TC: O(N^2) SC: O(N)
    public int lengthOfLongestSubstringBrute(String s) {
        int n = s.length();
        //Brute:
        //Add elements till freqMap.size() == window size
        int maxWindow = 0;
        for (int start = 0; start < n; start++) {
            Map<Character, Integer> freqMap = new HashMap<>();
            for (int end = start; end < n; end++) {
                char currChar = s.charAt(end);
                int val = freqMap.getOrDefault(currChar, 0);
                freqMap.put(currChar, val + 1);
                if (freqMap.size() == end - start + 1) {
                    maxWindow = Math.max(maxWindow, end - start + 1);
                    //For printing:System.out.println(s.substring(start,end+1));
                } else {
                    break;
                }
            }
        }
        return maxWindow;
    }

    // Optimal
    // TC: O(N) SC: O(N)
    public int lengthOfLongestSubstringOptimal(String s) {
        int n = s.length();
        //Optimal
        //Observation:We see that we are repeatedly checking same.
        //Also have substring and max/min to find so sliding window
        //We cannot have max size < window size
        int start = 0, end = 0, maxWindow = 0;
        Map<Character, Integer> freqMap = new HashMap<>();
        while (end < n) {
            char currChar = s.charAt(end);
            freqMap.put(currChar, freqMap.getOrDefault(currChar, 0) + 1);
            if (freqMap.size() == end - start + 1) {
                maxWindow = Math.max(maxWindow, end - start + 1);
                end++;
            } else if (freqMap.size() < end - start + 1) {
                //Till freqMap.size() is smaller .Remove from start
                while (freqMap.size() < end - start + 1) {
                    freqMap.put(s.charAt(start), freqMap.get(s.charAt(start)) - 1);
                    if (freqMap.get(s.charAt(start)) == 0) {
                        freqMap.remove(s.charAt(start));
                    }
                    if (freqMap.size() == end - start + 1) {
                        maxWindow = Math.max(maxWindow, end - start + 1);
                    }
                    start++;
                }
                end++;
            }
        }
        return maxWindow;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters sol = new LongestSubstringWithoutRepeatingCharacters();
        String input = "abcabcbb";

        System.out.println("Brute: " + sol.lengthOfLongestSubstringBrute(input));
        System.out.println("Optimal: " + sol.lengthOfLongestSubstringOptimal(input));
    }
}
