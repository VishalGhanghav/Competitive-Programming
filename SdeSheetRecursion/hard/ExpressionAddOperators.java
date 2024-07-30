package SdeSheetRecursion.hard;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {

    // Method to generate all possible expressions
    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        if (num == null || num.length() == 0) return ans;
        // Start recursion with initial values
        solve(0, "", 0, 0, num, target, ans);
        return ans;
    }

    // Recursive method to explore all possible expressions
    public void solve(int ind, String path, long resSoFar, long prevNum, String s, int target, List<String> ans) {
        // Base case: when we've reached the end of the string
        if (ind == s.length()) {
            if (resSoFar == target) {
                ans.add(path);
            }
            return;
        }

        // Iterate through the string to consider all possible numbers
        for (int i = ind; i < s.length(); i++) {
            // Skip numbers with leading zeroes
            if (i > ind && s.charAt(ind) == '0') {
                break;
            }

            // Extract the current number
            long num = Long.parseLong(s.substring(ind, i + 1));

            if (ind == 0) {
                // For the first number, no operator to add
                solve(i + 1, path + num, num, num, s, target, ans);
            } else {
                // Addition operation
                solve(i + 1, path + "+" + num, resSoFar + num, num, s, target, ans);
                // Subtraction operation
                solve(i + 1, path + "-" + num, resSoFar - num, -num, s, target, ans);
                // Multiplication operation
                solve(i + 1, path + "*" + num, resSoFar - prevNum + prevNum * num, prevNum * num, s, target, ans);
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {

        // Test case 1
        String num1 = "123";
        int target1 = 6;
        List<String> result1 = new ExpressionAddOperators().addOperators(num1, target1);
        System.out.println("Test case 1: " + result1); // Expected: ["1*2*3", "1+2+3"]

        // Test case 2
        String num2 = "232";
        int target2 = 8;
        List<String> result2 = new ExpressionAddOperators().addOperators(num2, target2);
        System.out.println("Test case 2: " + result2); // Expected: ["2*3+2", "2+3*2"]

        // Test case 3
        String num3 = "105";
        int target3 = 5;
        List<String> result3 = new ExpressionAddOperators().addOperators(num3, target3);
        System.out.println("Test case 3: " + result3); // Expected: ["1*0+5", "10-5"]

        // Test case 4
        String num4 = "3456237490";
        int target4 = 9191;
        List<String> result4 = new ExpressionAddOperators().addOperators(num4, target4);
        System.out.println("Test case 4: " + result4); // Expected: []
    }
}
