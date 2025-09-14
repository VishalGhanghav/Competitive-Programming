package SdeSheetRecursion.medium;

import java.util.*;

public class GenerateParenthesis {

    // ===============================================
    // Approach: Backtracking Recursion
    // TC: O(2^(2n)) in worst case, but effectively O(Cn)
    //     where Cn = n-th Catalan number ? 4^n / (n^(3/2))
    // SC: O(2n) recursion depth + output list
    // ===============================================
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        String op = "";
        return generate(res, op, n, n);
    }

    private static List<String> generate(List<String> res, String op, int open, int closed) {
        /*
         Original comments preserved:
         - If open == 0 && closed == 0 ? add to result
         - If open > 0 ? we can put '('
         - If closed > open ? we can put ')'
        */
        if (open == 0 && closed == 0) {
            res.add(op);
            return res;
        }

        if (open != 0) {
            generate(res, op + "(", open - 1, closed);
        }

        if (closed > open) {
            generate(res, op + ")", open, closed - 1);
        }

        return res;
    }

    // ===============================================
    // Main Method: Test cases
    // ===============================================
    public static void main(String[] args) {
        int[] testCases = {1, 2, 3};

        for (int n : testCases) {
            System.out.println("n = " + n + " ? " + generateParenthesis(n));
        }
    }
}

