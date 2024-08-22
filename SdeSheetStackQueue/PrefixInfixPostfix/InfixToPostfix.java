package SdeSheetStackQueue.PrefixInfixPostfix;

import java.util.Stack;

public class InfixToPostfix {
    public static String infixToPostfix(String exp) {
        int n = exp.length();
        StringBuilder ans = new StringBuilder();
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            char ch = exp.charAt(i);

            // If operand, add it to the result
            if (Character.isLetterOrDigit(ch)) {
                ans.append(ch);
            } else if (ch == '(') {
                // If opening bracket, push to stack
                st.push(ch);
            } else if (ch == ')') {
                // If closing bracket, pop until opening bracket is found
                while (!st.isEmpty() && st.peek() != '(') {
                    ans.append(st.pop());
                }
                st.pop(); // Pop the opening bracket as well
            } else {
                // Operator encountered
                while (!st.isEmpty() && priority(ch) <= priority(st.peek())) {
                    ans.append(st.pop());
                }
                st.push(ch); // Push the current operator onto the stack
            }
        }

        // Pop all the remaining operators from the stack
        while (!st.isEmpty()) {
            ans.append(st.pop());
        }

        return ans.toString();
    }

    private static int priority(char ch) {
        switch(ch) {
            case '^' : return 3;
            case '*' :
            case '/' : return 2;
            case '+' :
            case '-' : return 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        String exp1 = "a+b*(c^d-e)^(f+g*h)-i";
        String exp2 = "(a+b)*c";
        String exp3 = "a+b*c";

        System.out.println("Infix: " + exp1);
        System.out.println("Postfix: " + infixToPostfix(exp1));

        System.out.println("Infix: " + exp2);
        System.out.println("Postfix: " + infixToPostfix(exp2));

        System.out.println("Infix: " + exp3);
        System.out.println("Postfix: " + infixToPostfix(exp3));
    }
}
