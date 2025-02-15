package SdeSheeetStrings.medium;

public class SumOfBeautyOfAllSubstrings {
    public static void main(String[] args) {
        // Example test case
        String s = "aabcb";
        System.out.println("Beauty Sum: " + beautySum(s)); // Expected output: 5
    }

    public static int beautySum(String s) {
        // Plan: Go brute force. Store freq of characters per traversal and
        // later find min and max in them and return in ans
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // This array will store freq of each character in this iteration
            int[] freq = new int[26];
            for (int j = i; j < n; j++) {
                char ch = s.charAt(j);
                freq[ch - 'a']++;
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                // Now traverse through all characters in the freq array and find min and max and add to ans
                for (int k = 0; k < 26; k++) {
                    if (freq[k] > 0) {
                        min = Math.min(min, freq[k]);
                        max = Math.max(max, freq[k]);
                    }
                }
                ans += max - min;
            }
        }
        return ans;
    }
}
