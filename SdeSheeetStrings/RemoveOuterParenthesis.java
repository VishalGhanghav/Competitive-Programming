package SdeSheeetStrings;

import java.util.Stack;

public class RemoveOuterParenthesis {
    public static void main(String[] args) {
        // Test cases to validate the solution
        RemoveOuterParenthesis obj = new RemoveOuterParenthesis();

        String test1 = "(()())(())";
        System.out.println("Input: " + test1);
        System.out.println("Output (Better - Stack): " + obj.removeOuterParenthesesStack(test1));
        System.out.println("Output (Optimal - Counter): " + obj.removeOuterParenthesesOptimal(test1));

        String test2 = "(()())(())(()(()))";
        System.out.println("Input: " + test2);
        System.out.println("Output (Better - Stack): " + obj.removeOuterParenthesesStack(test2));
        System.out.println("Output (Optimal - Counter): " + obj.removeOuterParenthesesOptimal(test2));

        String test3 = "()()";
        System.out.println("Input: " + test3);
        System.out.println("Output (Better - Stack): " + obj.removeOuterParenthesesStack(test3));
        System.out.println("Output (Optimal - Counter): " + obj.removeOuterParenthesesOptimal(test3));
    }

    // Better Approach using a Stack
    public String removeOuterParenthesesStack(String s) {
        Stack<Character> st = new Stack<>(); // Stack to track parentheses
        StringBuilder sb = new StringBuilder(); // StringBuilder to construct the result string

        // Iterate through the characters of the string
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i); // Get the current character

            if (ch == '(') {
                if (st.size() > 0) {
                    // Append the inner '('
                    sb.append(ch);
                }
                st.push(ch); // Push '(' onto the stack
            } else {
                st.pop(); // Pop a '(' from the stack
                if (st.size() > 0) {
                    // Append the inner ')'
                    sb.append(ch);
                }
            }
        }

        return sb.toString(); // Return the result string
    }

    // Optimal Approach using a Counter
    public String removeOuterParenthesesOptimal(String s) {
        StringBuilder sb = new StringBuilder(); // StringBuilder to construct the result string
        int opened = 0; // Counter to track the number of opened parentheses

        // Iterate through the characters of the string
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i); // Get the current character

            if (ch == '(') {
                if (opened > 0) {
                    // Append the inner '('
                    sb.append(ch);
                }
                opened++; // Increment the count of opened parentheses
            } else {
                opened--; // Decrement the count of opened parentheses
                if (opened > 0) {
                    // Append the inner ')'
                    sb.append(ch);
                }
            }
        }

        return sb.toString(); // Return the result string
    }
}

