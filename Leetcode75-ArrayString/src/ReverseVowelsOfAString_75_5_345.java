/*
 345. Reverse Vowels of a String
Easy
3.6K
2.5K
Companies
Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

 

Example 1:

Input: s = "hello"
Output: "holle"
Example 2:

Input: s = "leetcode"
Output: "leotcede"
 

Constraints:

1 <= s.length <= 3 * 105
s consist of printable ASCII characters.
 */
public class ReverseVowelsOfAString_75_5_345 {
	 public String reverseVowels(String s) {
	        String a="";
	        String b="";
	        //
	        for(int i=0;i<s.length();i++){
	            char ch=s.charAt(i);
	            if(ch=='a' || ch=='e' || ch=='i'  || ch=='o'  || ch=='u' ||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U'){
	                 a+=ch;
	             }
	        }
	        //First add all vowels in a string
	        //Add other elements in new string normally and vowels in reverse of above string
	        System.out.println(a);
	        int j=a.length()-1;
	        for(int i=0;i<s.length();i++){
	            char ch=s.charAt(i);
	            if(ch=='a' || ch=='e' || ch=='i'  || ch=='o'  || ch=='u' ||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U'){
	                 b=b+a.charAt(j);
	                 j--;
	             }else{
	                 b=b+ch;
	             }
	        }
	        return b; 
	    }
}
