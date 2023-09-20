package com;


public class  test{
    public static void main(String[] args) {
        String input = "Aaba123B1";
        int[] charCountArray = countCharacters(input);
        
        for (int i = 0; i < charCountArray.length; i++) {
            if (charCountArray[i] > 0) {
                char c = (char) ('a' + i);
                System.out.println(c + "-" + charCountArray[i]);
            }
        }
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

