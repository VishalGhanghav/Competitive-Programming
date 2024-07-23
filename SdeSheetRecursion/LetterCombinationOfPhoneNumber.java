package SdeSheetRecursion;

import java.util.ArrayList;
import java.util.List;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.




Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]


Constraints:

0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
 */
public class LetterCombinationOfPhoneNumber {
    private static final String[] MAPPING = {
            "",    // 0
            "",    // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs",// 7
            "tuv", // 8
            "wxyz" // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }
        StringBuilder ds = new StringBuilder();

        solve(0, ds, ans, digits);
        return ans;
    }

    public void solve(int i,StringBuilder ds,List<String> ans,String digits){
        //when i=2 and digits.length=2//23
        if(i==digits.length()){
            //when we traverse for all digit in digits
            ans.add(ds.toString());
            return;
        }
        //eg:23 .for i=0,digit='2'-'0'ie.50-48 =2
        int digit=digits.charAt(i)-'0';
        //lets traverse all elements for digit 2 in phone
        //traverse for abc ie.length=3
        for(int j=0;j<MAPPING[digit].length();j++){
            //we have only pick condition
            ds.append(MAPPING[digit].charAt(j));
            solve(i + 1, ds, ans, digits);
            // backtrack
            ds.deleteCharAt(ds.length() - 1);
        }
    }
    public static void main(String args[]) {
        List<String> ls = new LetterCombinationOfPhoneNumber().letterCombinations("23");
        System.out.println("Combinations are: ");
        System.out.println(ls);
    }
}
