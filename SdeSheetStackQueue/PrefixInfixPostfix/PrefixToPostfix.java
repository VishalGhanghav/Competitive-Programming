package SdeSheetStackQueue.PrefixInfixPostfix;

import java.util.Stack;

public class PrefixToPostfix {
    static String preToPost(String pre_exp) {
        // Stack to store intermediate postfix expressions
        Stack<String> st = new Stack<>();

        // Iterate through each character in the prefix expression from right to left
        for (int i = pre_exp.length() - 1; i >= 0; i--) {
            char ch = pre_exp.charAt(i);

            // If the character is an operand (letter or digit)
            if (Character.isLetterOrDigit(ch)) {
                // Push the operand as a string onto the stack
                st.push(Character.toString(ch));
            } else {
                // The character is an operator
                // Pop the top two elements from the stack
                String top1 = st.pop();
                String top2 = st.pop();

                // Form the new postfix expression by appending the operator to the operands
                String temp = top1 + top2 + ch;

                // Push the newly formed postfix expression onto the stack
                st.push(temp);
            }
        }

        // The final element in the stack is the complete postfix expression
        return st.peek();
    }

    // Main method to test the preToPost function
    public static void main(String[] args) {
        String prefix = "*-A/BC-/AKL";
        String postfix = preToPost(prefix);
        System.out.println("Postfix expression: " + postfix); // Expected output: "ABC/-AK/L-*"
    }
}
