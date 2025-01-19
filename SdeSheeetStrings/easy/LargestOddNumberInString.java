package SdeSheeetStrings.easy;

public class LargestOddNumberInString {
    public String largestOddNumber(String num) {
        //We will go from last to first so that we check largest number first
        String res="";
        for(int i=num.length()-1;i>=0;i--){
            //Ascii value of 0 is 48.So if (char)num=52.int value=52-0 =52
            int revisedNo = num.charAt(i) - 48;
            //or nums.charAt(i)-'0';.Here we pass 0 as char
            if(revisedNo%2!=0){
                res= num.substring(0,i+1);
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LargestOddNumberInString largestOddNumberInString = new LargestOddNumberInString();
        System.out.println(largestOddNumberInString.largestOddNumber("52"));
        System.out.println(largestOddNumberInString.largestOddNumber("4206"));
        System.out.println(largestOddNumberInString.largestOddNumber("35427"));
    }
}
