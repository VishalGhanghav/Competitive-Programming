package com;


public class  test{
    public static void main(String[] args) {
        String str = "aabbbc";
        int[] charCountArray = countCharacters(str);
        int maxCount=0;
        for (int i = 0; i < str.length(); i++) {
            char c=str.charAt(i);
            int cnt=1;
            while(i<str.length()-1 && c==str.charAt(i+1) ) {
            	i++;
            	cnt++;
            }
            maxCount=Math.max(maxCount, cnt);
        }
        System.out.println(maxCount);
    }
    
    public static int[] countCharacters(String input) {
        int[] charCountArray = new int[26];
        
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                char lowercaseChar = Character.toLowerCase(c);
                charCountArray[lowercaseChar - 'a']++;
            }
        }
        
        return charCountArray;
    }
}

