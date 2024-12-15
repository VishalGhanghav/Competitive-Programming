package SdeSheetMaths.easy;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int copy=x;
        int reverseNumber=0;
        while(copy>0){
            int lastDigit = copy%10;
            reverseNumber = reverseNumber*10 + lastDigit;
            copy = copy/10;
        }
        return x==reverseNumber;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromeNumber().isPalindrome(121));
        System.out.println(new PalindromeNumber().isPalindrome(-121));
    }
}
