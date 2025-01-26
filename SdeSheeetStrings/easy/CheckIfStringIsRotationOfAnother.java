package SdeSheeetStrings.easy;

public class CheckIfStringIsRotationOfAnother {

    public boolean rotateStringBetter(String s, String goal) {
        // Better Implementation
        // Rotate the string. Time complexity is high because deleteCharAt and toString are O(N).
        // TC: O(n*n), SC: O(N)
        if (s.length() != goal.length()) {
            return false;
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            // Check if the current string equals the goal.
            // Never use == here as it will check for reference of the object, not the string values.
            if (sb.toString().equals(goal)) {
                return true;
            }
            // Get the char at the first position and move it to the end.
            char firstChar = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(firstChar);
        }
        // If we don't find the goal string after all rotations.
        return false;
    }

    public boolean rotateStringOptimal(String s, String goal) {
        // Optimal Implementation
        // TC: O(N), SC: O(2s)
        // Concatenate the string with itself and check if it contains the goal.
        if (s.length() != goal.length()) {
            return false;
        }
        String s2 = s + s;
        if (s2.contains(goal)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        CheckIfStringIsRotationOfAnother checkIfStringIsRotationOfAnother = new CheckIfStringIsRotationOfAnother();

        // Test cases
        String s = "abcde";
        String goal = "cdeab";

        // Better Implementation
        System.out.println("Better Implementation:");
        boolean resultBetter = checkIfStringIsRotationOfAnother.rotateStringBetter(s, goal);
        System.out.println("Input: s = \"" + s + "\", goal = \"" + goal + "\"");
        System.out.println("Result: " + resultBetter); // Expected: true

        // Optimal Implementation
        System.out.println("\nOptimal Implementation:");
        boolean resultOptimal = checkIfStringIsRotationOfAnother.rotateStringOptimal(s, goal);
        System.out.println("Input: s = \"" + s + "\", goal = \"" + goal + "\"");
        System.out.println("Result: " + resultOptimal); // Expected: true

        // Another test case
        s = "abcde";
        goal = "abced";

        // Better Implementation
        System.out.println("\nBetter Implementation (Mismatch):");
        resultBetter = checkIfStringIsRotationOfAnother.rotateStringBetter(s, goal);
        System.out.println("Input: s = \"" + s + "\", goal = \"" + goal + "\"");
        System.out.println("Result: " + resultBetter); // Expected: false

        // Optimal Implementation
        System.out.println("\nOptimal Implementation (Mismatch):");
        resultOptimal = checkIfStringIsRotationOfAnother.rotateStringOptimal(s, goal);
        System.out.println("Input: s = \"" + s + "\", goal = \"" + goal + "\"");
        System.out.println("Result: " + resultOptimal); // Expected: false
    }
}
