package SdeSheetMaths.easy;

public class CountDigitsInANumber {

    public int getDigitCount(int no){
        int cnt=0;
        while(no>0) {
            int lastDigit = no % 10;
            cnt++;
            no = no/10;
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new CountDigitsInANumber().getDigitCount(7158));
    }
}
