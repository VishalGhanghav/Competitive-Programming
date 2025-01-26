package SdeSheeetStrings.easy;

import java.util.HashMap;
import java.util.Map;

public class AnagramCheck {

    // First approach using Map
    public boolean isAnagramUsingMap(String s, String t) {
        Map<Character,Integer> map = new HashMap<>();
        if(s.length()!=t.length()){
            return false;
        }
        //Put data into map.
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            int value = map.getOrDefault(ch,0)+1;
            map.put(ch,value);
        }
        System.out.println(map);  // Printing the map for debugging purposes
        //pop data from map
        for(int i=0;i<t.length();i++){
            char ch=t.charAt(i);
            if(map.containsKey(ch)){
                map.put(ch,map.get(ch)-1);
                if(map.get(ch)==0){
                    map.remove(ch);
                }
            }else{
                return false;
            }
        }

        return map.size()==0;
    }

    // Second approach using array
    /*
    example:
    /*Let's take an example:

Example 1:

s = "anagram"
t = "nagaram"
Step 1: Initialize count array
int[] count = new int[26];  // Initialize all values to 0
Step 2: Count the frequencies in s ("anagram")
We loop through each character in s and update the count array.

s.charAt(0) = 'a': count['a' - 'a'] = count[0]++ → count[0] = 1
s.charAt(1) = 'n': count['n' - 'a'] = count[13]++ → count[13] = 1
s.charAt(2) = 'a': count['a' - 'a'] = count[0]++ → count[0] = 2
s.charAt(3) = 'g': count['g' - 'a'] = count[6]++ → count[6] = 1
s.charAt(4) = 'r': count['r' - 'a'] = count[17]++ → count[17] = 1
s.charAt(5) = 'a': count['a' - 'a'] = count[0]++ → count[0] = 3
s.charAt(6) = 'm': count['m' - 'a'] = count[12]++ → count[12] = 1
Now, the count array looks like this:
[3, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0]
The relevant counts for s are:

'a' = 3, 'n' = 1, 'g' = 1, 'r' = 1, 'm' = 1
Step 3: Decrement the frequencies based on t ("nagaram")
Now, we decrement the frequencies in the count array for each character in t:

t.charAt(0) = 'n': count['n' - 'a'] = count[13]-- → count[13] = 0
t.charAt(1) = 'a': count['a' - 'a'] = count[0]-- → count[0] = 2
t.charAt(2) = 'g': count['g' - 'a'] = count[6]-- → count[6] = 0
t.charAt(3) = 'a': count['a' - 'a'] = count[0]-- → count[0] = 1
t.charAt(4) = 'r': count['r' - 'a'] = count[17]-- → count[17] = 0
t.charAt(5) = 'a': count['a' - 'a'] = count[0]-- → count[0] = 0
t.charAt(6) = 'm': count['m' - 'a'] = count[12]-- → count[12] = 0
Now, the count array looks like this:
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
All values are zero, meaning that the characters in s and t match in both type and frequency.

Step 4: Final check
Since all values in count[] are zero, we return true, indicating that s and t are indeed anagrams.
     */
    public boolean isAnagramUsingArray(String s, String t) {
        int[] count = new int[26];  // To count the frequency of characters (for 'a' to 'z')

        // Count the frequency of characters in string s
        for (char x : s.toCharArray()) {
            count[x - 'a']++;
        }

        // Decrement the frequency of characters in string t
        for (char x : t.toCharArray()) {
            count[x - 'a']--;
        }

        // Check if any character has a non-zero frequency
        for (int val : count) {
            if (val != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        AnagramCheck checker = new AnagramCheck();

        // Test cases for both approaches
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println("Using Map approach: " + checker.isAnagramUsingMap(s1, t1));  // Expected: true
        System.out.println("Using Array approach: " + checker.isAnagramUsingArray(s1, t1));  // Expected: true

        String s2 = "rat";
        String t2 = "car";
        System.out.println("Using Map approach: " + checker.isAnagramUsingMap(s2, t2));  // Expected: false
        System.out.println("Using Array approach: " + checker.isAnagramUsingArray(s2, t2));  // Expected: false
    }
}
