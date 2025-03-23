package SdeSheeetStrings.hard;

public class IsPatternPresentInStringBruteKmp {
    public static void main(String[] args) {
        String text ="abxabc";
        String pattern = "abc";
        String t = "hello world";
        String p = "world";
        System.out.println(isPatternPresentBrute(text,pattern));
        System.out.println(isPatternPresentKmp(t,p));
    }

    private static boolean isPatternPresentKmp(String text, String pattern) {
        int []lps = computeLpsArray(pattern);
        int m =text.length();
        int n =pattern.length();
        int i=0;
        int j=0;

        while(i<m){
            while (j>0 && text.charAt(i) != pattern.charAt(j)) {
                j = lps[j-1];//Avoid redundant cal using lps
            }
            if(text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            i++;

            if(j==n) {
                return true;
            }
        }
        //till end we dont find pattern
        return false;
    }

    private static int[] computeLpsArray(String pattern) {
        int i=0;
        int j=1;
        int n=pattern.length();
        int[] lps = new int[n];
        //I will traverse pattern till last elmt
        while (j<n) {

            //If char not matching we check lps of prev i in loop to see if there was prefix before
            while(i>0 && pattern.charAt(i) != pattern.charAt(j)){
                i = lps[i-1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                i++;
            }
            lps[j]=i;
            j++;
        }
        return lps;
    }

    static boolean isPatternPresentBrute(String text, String pattern) {
        // code here
        int m = text.length();
        int n =  pattern.length();
        //We will go through the text and compare each char with pattern.
        //If pattern not matching start comparing pattern from start
        //and one step ahead in text
        for(int i=0;i<=(m-n) ;i++) {
            int j=0;
            for(j=0 ;j<n;j++) {
                //I is constant ,j incrementing.SO compare
                if (text.charAt(i+j) != pattern.charAt(j)) {
                    break;
                }
            }
            //If j loop over and all chars matching then pattern found
            if(j==n){
                return true;//pattern found
            }
        }
        return false;
    }
}
