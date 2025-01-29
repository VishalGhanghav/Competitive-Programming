package SdeSheeetStrings.medium;


import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    public int romanToInt(String s) {
        /*Plan: If current numeral less than next numeral.Subtract it and increment i twice
        as two numerals covered
        If greater add only that*/
        Map<Character, Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);

        int res = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (i < n - 1 && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                res += map.get(s.charAt(i + 1)) - map.get(s.charAt(i));
                i++;
            } else {
                res += map.get(s.charAt(i));
            }
        }
        return res;
    }
    public static void main(String[] args) {
        RomanToInteger converter = new RomanToInteger();

        // Test cases
        String roman1 = "III"; // Expected output: 3
        String roman2 = "IV";  // Expected output: 4
        String roman3 = "IX";  // Expected output: 9
        String roman4 = "LVIII"; // Expected output: 58
        String roman5 = "MCMXCIV"; // Expected output: 1994

        System.out.println("Roman numeral " + roman1 + " converts to: " + converter.romanToInt(roman1));
        System.out.println("Roman numeral " + roman2 + " converts to: " + converter.romanToInt(roman2));
        System.out.println("Roman numeral " + roman3 + " converts to: " + converter.romanToInt(roman3));
        System.out.println("Roman numeral " + roman4 + " converts to: " + converter.romanToInt(roman4));
        System.out.println("Roman numeral " + roman5 + " converts to: " + converter.romanToInt(roman5));
    }
}

