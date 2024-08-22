package SdeSheetStackQueue.PrefixInfixPostfix;

import java.util.Stack;

public class PostfixToInfix {
    static String postToInfix(String exp) {
        // Stack to store intermediate infix expressions
        Stack<String> st = new Stack<>();

        // Iterate through each character in the postfix expression
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            // If the character is an operand (letter or digit)
            if (Character.isLetterOrDigit(ch)) {
                // Push the operand as a string onto the stack
                st.push(Character.toString(ch));
            } else {
                // The character is an operator
                // Pop the top two elements from the stack
                String top1 = st.pop();
                String top2 = st.pop();

                // Create a new infix expression with parentheses
                String s = '(' + top2 + ch + top1 + ')';

                // Push the newly formed infix expression onto the stack
                st.push(s);
            }
        }

        // The final element in the stack is the complete infix expression
        return st.peek();
    }

    // Main method to test the postToInfix function
    public static void main(String[] args) {
        String postfix = "ab+c*";
        String infix = postToInfix(postfix);
        System.out.println("Infix expression: " + infix); // Expected output: ((a+b)*c)
    }
}
