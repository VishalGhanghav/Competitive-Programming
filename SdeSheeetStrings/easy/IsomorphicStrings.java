package SdeSheeetStrings.easy;

import java.util.HashMap;

public class IsomorphicStrings {
    public static boolean isIsomorphic(String s, String t) {
        //We will have a character to character map.If mapping doesnt match then surely strings are not isomorphic
        //if mapping matches till end its ismorphic
        if(s.length()!=t.length()){
            return false;
        }
        //For two side mapping we need two maps:
        /*
        Case with s = "ab", t = "aa":
        Using only mapS: 'a' -> 'a' and 'b' -> 'a' would pass, but this violates the bijective
         property (both 'a' and 'b' map to 'a').
        Using mapT: Ensures 'a' -> 'a' but does not validate that 'b' in s maps correctly.
        Case with s = "foo", t = "bar":
        Using only mapT: 'b' -> 'f', 'a' -> 'o', but we can't ensure both 'o' -> 'a' mappings are correct.
        */
        HashMap<Character, Character> mapS = new HashMap<>();
        HashMap<Character, Character> mapT = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            if(mapS.containsKey(ch1)){
                if(mapS.get(ch1)!=ch2){
                    return false;
                }
            }else{
                mapS.put(ch1,ch2);
            }
            if(mapT.containsKey(ch2)){
                if(mapT.get(ch2)!=ch1){
                    return false;
                }
            }else{
                mapT.put(ch2,ch1);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(isIsomorphic("egg", "add")); // Output: true
        System.out.println(isIsomorphic("foo", "bar")); // Output: false
        System.out.println(isIsomorphic("paper", "title")); // Output: true
        System.out.println(isIsomorphic("ab", "aa")); // Output: false
        System.out.println(isIsomorphic("abc", "xyz")); // Output: true
        System.out.println(isIsomorphic("abc", "xxy")); // Output: false
    }
}
