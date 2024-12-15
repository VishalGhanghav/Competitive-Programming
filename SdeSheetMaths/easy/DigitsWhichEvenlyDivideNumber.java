package SdeSheetMaths.easy;
/*
Given a positive integer n, count the number of digits in n that divide n evenly (i.e., without leaving a remainder). Return the total number of such digits.

A digit d of n divides n evenly if the remainder when n is divided by d is 0 (n % d == 0).
Digits of n should be checked individually. If a digit is 0, it should be ignored because division by 0 is undefined.

Examples :

Input: n = 12
Output: 2
Explanation: 1, 2 when both divide 12 leaves remainder 0.
Input: n = 2446
Output: 1
Explanation: Here among 2, 4, 6 only 2 divides 2446 evenly while 4 and 6 do not.
Input: n = 23
Output: 0
Explanation: 2 and 3, none of them divide 23 evenly.
Constraints:
1<= n <=105
 */
public class DigitsWhichEvenlyDivideNumber {
    // Function to count digits of n that divide n evenly
    static int evenlyDivides(int n) {
        // code here
        int no = n;
        int cnt = 0;
        while (no > 0) {
            int lastDigit = no % 10;
            // Prevent divide by zero error and check if n is divisible by lastDigit
            if (lastDigit != 0 && n % lastDigit == 0) {
                cnt++;
            }
            no = no / 10;
        }
        return cnt;
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        int n = 2446; // You can replace this with any test input
        int result = evenlyDivides(n);//only 2 divides:2446%2==0
        System.out.println("Count of digits in " + n + " that evenly divide it: " + result);
    }
}
