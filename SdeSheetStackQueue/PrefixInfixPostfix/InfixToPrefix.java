package SdeSheetStackQueue.PrefixInfixPostfix;

import java.util.Stack;

public class InfixToPrefix {
    public static String infixToPrefix(String exp) {
        int n = exp.length();

        // Step 1: Reverse the input string
        String revInput = new StringBuilder(exp).reverse().toString();
        StringBuilder ans = new StringBuilder();
        Stack<Character> st = new Stack<>();

        // Step 2: Perform infix to postfix on the reversed input
        for (int i = 0; i < n; i++) {
            char ch = revInput.charAt(i);

            // If the character is an operand, add it to the result
            if (Character.isLetterOrDigit(ch)) {
                ans.append(ch);
            } else if (ch == ')') {
                // If closing bracket (considered as opening bracket in the reversed input), push to stack
                st.push(ch);
            } else if (ch == '(') {
                // If opening bracket (considered as closing bracket in the reversed input), pop until ')' is found
                while (!st.isEmpty() && st.peek() != ')') {
                    ans.append(st.pop());
                }
                st.pop(); // Pop the closing bracket as well
            } else {
                // Operator encountered
                // '^' is right associative, handle it separately
                if (ch == '^') {
                    while (!st.isEmpty() && priority(ch) <= priority(st.peek())) {
                        ans.append(st.pop());
                    }
                } else {
                    while (!st.isEmpty() && priority(ch) < priority(st.peek())) {
                        ans.append(st.pop());
                    }
                }
                st.push(ch); // Push the current operator onto the stack
            }
        }

        // Pop all the remaining operators from the stack
        while (!st.isEmpty()) {
            ans.append(st.pop());
        }

        // Step 3: Reverse the final result to get the prefix expression
        return ans.reverse().toString();
    }

    private static int priority(char ch) {
        switch (ch) {
            case '^': return 3;
            case '*':
            case '/': return 2;
            case '+':
            case '-': return 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        // Test cases for infix to prefix conversion
        String exp1 = "a+b*(c^d-e)^(f+g*h)-i";
        String exp2 = "(a+b)*c";
        String exp3 = "(a+b)*c-d+f";

        // Display the results
        System.out.println("Infix: " + exp1);
        System.out.println("Prefix: " + infixToPrefix(exp1));

        System.out.println("Infix: " + exp2);
        System.out.println("Prefix: " + infixToPrefix(exp2));

        System.out.println("Infix: " + exp3);
        System.out.println("Prefix: " + infixToPrefix(exp3));
    }
}
