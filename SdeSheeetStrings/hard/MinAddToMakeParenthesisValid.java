package SdeSheeetStrings.hard;

public class MinAddToMakeParenthesisValid {
    public static void main(String[] args) {
        MinAddToMakeParenthesisValid sol = new MinAddToMakeParenthesisValid();

        // Test cases
        String s1 = "())";         // Output: 1
        String s2 = "(((";         // Output: 3
        String s3 = "()";          // Output: 0
        String s4 = "()))((";      // Output: 4
        String s5 = "((()))";      // Output: 0

        System.out.println("Test Cases:");
        System.out.println("Input: " + s1 + " | Output: " + sol.minAddToMakeValid(s1));
        System.out.println("Input: " + s2 + " | Output: " + sol.minAddToMakeValid(s2));
        System.out.println("Input: " + s3 + " | Output: " + sol.minAddToMakeValid(s3));
        System.out.println("Input: " + s4 + " | Output: " + sol.minAddToMakeValid(s4));
        System.out.println("Input: " + s5 + " | Output: " + sol.minAddToMakeValid(s5));
    }

    public int minAddToMakeValid(String s) {
        int len = s.length();
        int cnt = 0;
        int ans = 0;
        // If opening bracket increase cnt, closing decrease. But whenever we go negative in cnt
        // it means we need opening bracket for that, so ans++.
        // If only opening brackets, they will get added in cnt and in the end in ans.
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                cnt++;
            } else if (ch == ')') {
                cnt--;
            }
            if (cnt < 0) {
                ans++;
                cnt = 0;
            }
        }
        ans += cnt;
        return ans;
    }
}
