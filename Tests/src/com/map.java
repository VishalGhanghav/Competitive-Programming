package com;



import java.util.HashMap;
import java.util.Map;

public class map {
    public static void main(String[] args) {
        String input = "Aaba123B1";
        Map<Character, Integer> charCountMap = countCharacters(input);
        
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }
    
    public static Map<Character, Integer> countCharacters(String input) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        
        for (char c : input.toCharArray()) {
            // Convert the character to lowercase before counting
            char lowercaseChar = Character.toLowerCase(c);
            
            // Check if the character is a letter or digit
            if (charCountMap.containsKey(lowercaseChar)) {
                charCountMap.put(lowercaseChar, charCountMap.get(lowercaseChar) + 1);
            }else {
            	charCountMap.put(lowercaseChar,1);
            }
        }
        
        return charCountMap;
    }
}
