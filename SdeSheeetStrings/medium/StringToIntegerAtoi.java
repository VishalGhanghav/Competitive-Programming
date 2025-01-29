package SdeSheeetStrings.medium;

public class StringToIntegerAtoi {

    public int myAtoi(String s) {
        // Skip white spaces, then check for sign and get it in variable isNegative.
        // Then do res = res * 10 + digit. Before this, check if out of bound.
        int len = s.length();
        int ind = 0;

        // Step 1: Skip whitespaces
        while (ind < len && s.charAt(ind) == ' ') {
            ind++;
        }

        // Step 2: Check for sign
        boolean isNegative = false;
        if (ind < len) {
            if (s.charAt(ind) == '-') { // If '-' is encountered, set isNegative to true.
                isNegative = true;
                ind++;
            } else if (s.charAt(ind) == '+') { // If '+' is encountered, simply move to the next character.
                ind++;
            }
        }

        // Step 3: Convert digit (in character form) to integer
        int res = 0;
        while (ind < len && Character.isDigit(s.charAt(ind))) {
            int digit = s.charAt(ind) - '0'; // Convert character to its integer value.

            // Step 4: To avoid Integer overflow
            // Check if adding the next digit will cause res to exceed Integer.MAX_VALUE.
            if (res > (Integer.MAX_VALUE / 10) || (res == (Integer.MAX_VALUE / 10) && digit > 7)) {
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            // Step 5: Add the digit to the result
            res = (res * 10) + digit;
            ind++;
        }

        // Step 6: Return the final result with the correct sign
        return isNegative ? -res : res;
    }

    public static void main(String[] args) {
        StringToIntegerAtoi solution = new StringToIntegerAtoi();

        // Test cases
        String input1 = "   -42";
        System.out.println("Input: \"" + input1 + "\"");
        System.out.println("Output: " + solution.myAtoi(input1)); // Expected: -42

        String input2 = "4193 with words";
        System.out.println("\nInput: \"" + input2 + "\"");
        System.out.println("Output: " + solution.myAtoi(input2)); // Expected: 4193

        String input3 = "words and 987";
        System.out.println("\nInput: \"" + input3 + "\"");
        System.out.println("Output: " + solution.myAtoi(input3)); // Expected: 0

        String input4 = "-91283472332";
        System.out.println("\nInput: \"" + input4 + "\"");
        System.out.println("Output: " + solution.myAtoi(input4)); // Expected: -2147483648 (Integer.MIN_VALUE)

        String input5 = "   +123";
        System.out.println("\nInput: \"" + input5 + "\"");
        System.out.println("Output: " + solution.myAtoi(input5)); // Expected: 123
    }
}
