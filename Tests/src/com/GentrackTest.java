package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/*
 Given a string s, find the longest palindromic subsequence's length in s.
A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 
Example 1:
Input: s = "bbbab" Output: 4 Explanation: One possible longest palindromic subsequence is "bbbb".
Example 2:
Input: s = "cbbd" Output: 2 Explanation: One possible longest palindromic subsequence is "bb".
 */
public class GentrackTest {
	public static void main(String args[]) {
		String s1="bbbab";
		String s2="cbbd";
		int len1=getLongestPalindromicSubsequence(s1);
		int len2=getLongestPalindromicSubsequence(s2);
		System.out.println(len1);
		System.out.println(len2);
	}

	private static int getLongestPalindromicSubsequence(String s) {
		
		HashMap<Character,Integer> hm=new HashMap<>();
		for(int i=0;i<s.length();i++) {
			if(hm.containsKey(s.charAt(i))) {
				hm.put(s.charAt(i), hm.get(s.charAt(i))+1);
			}else {
				hm.put(s.charAt(i), 1);
			}
			
		}
		
		String res="";
		int max=0;
		Character key = null;
		int val=0;
		for( Entry<Character, Integer> a:hm.entrySet()) {
			if(a.getValue()>max) {
				max=a.getValue();
				key=a.getKey();
				val=a.getValue();
			}
		}
		for(int i=0;i<val;i++) {
			res=res+key.charValue();
		}
		
		return res.length();
	}
	
	
}
