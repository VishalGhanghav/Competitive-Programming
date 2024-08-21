package SdeSheetStackQueue.learning;

import java.util.Stack;

public class BalancedParenthesis {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            // If the current character is an opening bracket, push it onto the stack
            if (currentChar == '(' || currentChar == '{' || currentChar == '[') {
                st.push(currentChar);
            } else {
                // If stack is empty and we encounter a closing bracket, the string is invalid
                if (st.isEmpty()) {
                    return false;
                }

                char topChar = st.peek();
                // Check if the top of the stack matches the corresponding opening bracket
                if ((currentChar == ')' && topChar == '(') ||
                        (currentChar == '}' && topChar == '{') ||
                        (currentChar == ']' && topChar == '[')) {
                    st.pop(); // Pop the matching opening bracket
                } else {
                    return false; // Mismatched brackets
                }
            }
        }
        // The stack should be empty if all brackets were matched correctly
        return st.isEmpty();
    }

    // Main method to test the isValid method
    public static void main(String[] args) {
        BalancedParenthesis validator = new BalancedParenthesis();

        // Test cases
        String test1 = "()[]{}";
        String test2 = "([)]";
        String test3 = "((()))";
        String test4 = "(((";

        // Running the test cases
        System.out.println("Test 1: " + validator.isValid(test1)); // Expected output: true
        System.out.println("Test 2: " + validator.isValid(test2)); // Expected output: false
        System.out.println("Test 3: " + validator.isValid(test3)); // Expected output: true
        System.out.println("Test 4: " + validator.isValid(test4)); // Expected output: false
    }
}
