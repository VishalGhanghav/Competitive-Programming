package SdeSheetRecursion.easy;

public class StringToIntegerAtoi {
    // ===============================================
    // Optimal Approach: Single Pass with Overflow Check
    // TC: O(n) where n = length of string
    // SC: O(1)
    // ===============================================
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();
        // Constants for 32-bit signed integer range
        final int INT_MAX = Integer.MAX_VALUE;
        final int INT_MIN = Integer.MIN_VALUE;

        if (s == null || s.length() == 0) {
            return 0;
        }

        // Step 1: Skip leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        if (i == n) {
            return 0;
        }

        // Step 2: Check for sign
        int sign = 1;
        if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (s.charAt(i) == '+') {
            i++;
        }

        // Step 3: Read digits and convert
        long res = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            res = res * 10 + digit;

            // Overflow check
            if (sign * res <= INT_MIN) {
                return INT_MIN;
            }
            if (sign * res >= INT_MAX) {
                return INT_MAX;
            }

            i++;
        }

        // Step 4: Apply sign and return
        return (int) (res * sign);
    }

    // ===============================================
    // Main Method: Testing the optimal approach
    // ===============================================
    public static void main(String[] args) {
        StringToIntegerAtoi sol = new StringToIntegerAtoi();

        String[] testCases = {
                "42",
                "   -42",
                "4193 with words",
                "words and 987",
                "-91283472332",
                "+1",
                "   +0 123",
                "",
                "   "
        };

        System.out.println("=== Testing Optimal Approach ===");
        for (String tc : testCases) {
            System.out.println("Input: \"" + tc + "\" Output: " + sol.myAtoi(tc));
        }
    }
}
