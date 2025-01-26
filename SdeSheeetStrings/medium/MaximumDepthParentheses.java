package SdeSheeetStrings.medium;

public class MaximumDepthParentheses {

    public int maxDepth(String s) {
        // Plan: Use a counter to track the current depth and a variable to store the maximum depth
        // If an opening bracket is encountered, increase the counter
        // If a closing bracket is encountered, decrease the counter
        // Update the maximum depth whenever an opening bracket increases the count

        int maxCnt = 0; // To store the maximum depth
        int cnt = 0;    // To track the current depth

        // Iterate through the string
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') { // Opening bracket
                cnt++; // Increase current depth
                maxCnt = Math.max(cnt, maxCnt); // Update maximum depth if needed
            } else if (ch == ')') { // Closing bracket
                cnt--; // Decrease current depth
            }
        }

        return maxCnt; // Return the maximum depth found
    }

    // Main method to test the function
    public static void main(String[] args) {
        MaximumDepthParentheses mdp = new MaximumDepthParentheses();

        // Test case 1
        String input1 = "(1+(2*3)+((8)/4))+1";
        System.out.println("Input: " + input1);
        System.out.println("Max Depth: " + mdp.maxDepth(input1));

        // Test case 2
        String input2 = "(1)+((2))+(((3)))";
        System.out.println("Input: " + input2);
        System.out.println("Max Depth: " + mdp.maxDepth(input2));

        // Test case 3
        String input3 = "1+(2*3)/(2-1)";
        System.out.println("Input: " + input3);
        System.out.println("Max Depth: " + mdp.maxDepth(input3));

        // Test case 4
        String input4 = "1";
        System.out.println("Input: " + input4);
        System.out.println("Max Depth: " + mdp.maxDepth(input4));
    }
}
