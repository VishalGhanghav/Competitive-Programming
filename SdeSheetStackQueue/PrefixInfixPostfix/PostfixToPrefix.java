package SdeSheetStackQueue.PrefixInfixPostfix;

import java.util.Stack;

public class PostfixToPrefix {
    static String postToPre(String post_exp) {
        // Stack to store intermediate prefix expressions
        Stack<String> st = new Stack<>();

        // Iterate through each character in the postfix expression
        for (int i = 0; i < post_exp.length(); i++) {
            char ch = post_exp.charAt(i);

            // If the character is an operand (letter or digit)
            if (Character.isLetterOrDigit(ch)) {
                // Push the operand as a string onto the stack
                st.push(Character.toString(ch));
            } else {
                // The character is an operator
                // Pop the top two elements from the stack
                String top1 = st.pop();
                String top2 = st.pop();

                // Form the new prefix expression by prepending the operator
                String s = ch + top2 + top1;

                // Push the newly formed prefix expression onto the stack
                st.push(s);
            }
        }

        // The final element in the stack is the complete prefix expression
        return st.peek();
    }

    // Main method to test the postToPre function
    public static void main(String[] args) {
        String postfix = "ABC/-AK/L-*";
        String prefix = postToPre(postfix);
        System.out.println("Prefix expression: " + prefix); // Expected output: "*-A/BC-/AKL"
    }
}
