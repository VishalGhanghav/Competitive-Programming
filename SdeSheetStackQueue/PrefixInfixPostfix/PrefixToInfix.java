package SdeSheetStackQueue.PrefixInfixPostfix;

import java.util.Stack;

public class PrefixToInfix {
    static String preToInfix(String pre_exp) {
        // Stack to store intermediate infix expressions
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

                // Create a new infix expression with parentheses
                String s = '(' + top1 + ch + top2 + ')';

                // Push the newly formed infix expression onto the stack
                st.push(s);
            }
        }

        // The final element in the stack is the complete infix expression
        return st.peek();
    }

    // Main method to test the preToInfix function
    public static void main(String[] args) {
        String prefix = "*+AB-CD";
        String infix = preToInfix(prefix);
        System.out.println("Infix expression: " + infix); // Expected output: ((A+B)*(C-D))
    }
}
