package SdeSheetStackQueue.MonotonicStack.Problems;

import java.util.Stack;

public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        int n = num.length();
        StringBuilder res = new StringBuilder();
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            char ch = num.charAt(i);
            // Pop all the elements greater than st.top till k==0
            // character to number is char-'0'.'9'-'0'=9
            while (!st.isEmpty() && k > 0 && st.peek() > ch) {
                st.pop();
                k--;
            }
            st.push(ch);
        }

        // Now if k didn't get zero 1 2 3 4 5 6 k=3. Pop top 3
        while (!st.isEmpty() && k > 0) {
            st.pop();
            k--;
        }

        // Build the result string from the stack
        while (!st.isEmpty()) {
            res.append(st.pop());
        }

        // Reverse the string since we popped from the stack (LIFO order)
        res.reverse();

        // Remove leading zeros from the result
        // Skip leading zeros by finding the first non-zero character
        int i = 0;
        while (i < res.length() && res.charAt(i) == '0') {
            i++;
        }

        // Get the substring starting from the first non-zero character
        String result = res.substring(i);

        /*OR
        while (res.length() != 0 && res.charAt(res.length() - 1) == '0') {
            res.deleteCharAt(res.length() - 1);
        }
        // Reverse the string since we popped from the stack (LIFO order)
        res.reverse();
        */

        // If the result is empty, return "0"
        if (result.length() == 0) {
            return "0";
        }

        return result;
    }

    public static void main(String[] args) {
        RemoveKDigits solution = new RemoveKDigits();
        String num = "1432219";
        int k = 3;

        // Test the removeKdigits method with given input
        String result = solution.removeKdigits(num, k);
        System.out.println("Result after removing " + k + " digits: " + result);

        // Additional test cases
        System.out.println("Test Case 1: " + solution.removeKdigits("10200", 1)); // Expected output: "200"
        System.out.println("Test Case 2: " + solution.removeKdigits("10", 2));   // Expected output: "0"
    }
}
