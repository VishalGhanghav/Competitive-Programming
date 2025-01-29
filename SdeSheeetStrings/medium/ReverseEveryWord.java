package SdeSheeetStrings.medium;

public class ReverseEveryWord {

    // Main method to test both versions
    public static void main(String[] args) {
        ReverseEveryWord reverseWords = new ReverseEveryWord();

        // Test the brute reverse version
        String testStr = "Hello World!";
        System.out.println("Original String: " + testStr);
        System.out.println("Reversed Words (Brute Reverse): " + reverseWords.reverseWordsBrute(testStr));

        // Test the better reverse version
        System.out.println("Reversed Words (Optimized Reverse): " + reverseWords.reverseWordsBetter(testStr));
    }

    // Brute reverse: using String.charAt() to reverse each word
    public String reverseWordsBrute(String s) {
        String[] words = s.split(" ");
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            reverse(words[i], res);  // Using brute reverse
            if (i != words.length - 1) {
                res.append(" ");
            }
        }
        return res.toString();
    }

    // Reverse a word using brute force (charAt)
    public void reverse(String word, StringBuilder res) {
        for (int i = word.length() - 1; i >= 0; i--) {
            res.append(word.charAt(i));  // Appending each char in reverse order
        }
    }

    // Optimized reverse: using char[] array and in-place swap
    public String reverseWordsBetter(String s) {
        String[] words = s.split(" ");
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            reverse(words[i].toCharArray(), res);  // Using optimized reverse with char array
            if (i != words.length - 1) {
                res.append(" ");
            }
        }
        return res.toString();
    }

    // Reverse a char array in place and append it to res
    public void reverse(char[] arr, StringBuilder res) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }

        res.append(arr);  // Directly append the modified array
    }
}
