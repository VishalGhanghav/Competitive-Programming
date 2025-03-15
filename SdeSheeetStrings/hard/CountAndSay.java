package SdeSheeetStrings.hard;

public class CountAndSay {
    public String countAndSay(int n) {
        // I will recurse up to n = 1 and from there start creating the series.
        if (n == 1) {
            return "1";  // Base case: countAndSay(1) = "1"
        }

        // Get the previous sequence from recursion
        String s = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();

        int cnt = 0; // Counter to track occurrences of each digit

        // Iterate through the previous sequence to form the current sequence
        for (int i = 0; i < s.length(); i++) {
            cnt++;  // Count occurrences of current digit

            // If it's the last character OR the next character is different, append result
            if (i == s.length() - 1 || s.charAt(i) != s.charAt(i + 1)) {
                sb.append(cnt).append(s.charAt(i)); // Append "count + digit"
                cnt = 0;  // Reset count for next group
            }
        }

        return sb.toString();  // Return the generated sequence
    }

    public String countAndSayIterative(int n) {
        String s = "1"; // Base case

        for (int i = 2; i <= n; i++) { // Build sequence iteratively up to n
            StringBuilder sb = new StringBuilder();
            int count = 1;

            // Process the previous sequence character by character
            for (int j = 0; j < s.length(); j++) {
                if (j < s.length() - 1 && s.charAt(j) == s.charAt(j + 1)) {
                    count++; // Increment count for repeated characters
                } else {
                    sb.append(count).append(s.charAt(j)); // Append "count + char"
                    count = 1; // Reset count for the next character group
                }
            }

            s = sb.toString(); // Move to the next sequence
        }

        return s;
    }

    // Main method to test the function
    public static void main(String[] args) {
        CountAndSay sol = new CountAndSay();

        // Example test cases
        int n = 4;
        System.out.println("countAndSay(" + n + ") = " + sol.countAndSay(n));

        n = 5;
        System.out.println("countAndSay(" + n + ") = " + sol.countAndSay(n));

        n = 6;
        System.out.println("countAndSay(" + n + ") = " + sol.countAndSay(n));
    }
}
