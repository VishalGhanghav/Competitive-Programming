package SdeSheetMaths.easy;
/*
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).



Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
Example 3:

Input: x = 120
Output: 21


Constraints:

-231 <= x <= 231 - 1
 */
public class ReverseDigitsInANumber {
    public int reverse(int x) {
        long reverseNumber = 0;
        while(x!=0){
            int lastDigit = x%10;
            //2347 ->0*10+7  7*10+4=74 similaryly further
            reverseNumber = reverseNumber*10+lastDigit;
            x=x/10;
        }
        //Check for condition asked
        if(reverseNumber>Integer.MAX_VALUE || reverseNumber<Integer.MIN_VALUE){
            return 0;
        }
        return (int)reverseNumber;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseDigitsInANumber().reverse(-321));
    }
}
