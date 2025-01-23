package SdeSheeetStrings.easy;

import java.util.Arrays;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        //We can sort the string.SO they will be lexicographically sorted
        Arrays.sort(strs);

        String s1=strs[0];
        String s2=strs[strs.length-1];
        int ind=0;
        //Now till characters are same.We can move index ahead and later get the substring
        while(ind < s1.length() && ind < s2.length()){
            if(s1.charAt(ind) == s2.charAt(ind)){
                ind++;
            } else {
                break;
            }
        }
        return s1.substring(0, ind);
    }

    public static void main(String[] args) {
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        System.out.println(longestCommonPrefix.longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(longestCommonPrefix.longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }
}
