package SdeSheetMaths.easy;

public class LcmAndGcd {
    public static int[] lcmAndGcdBrute(int a, int b) {
        // Brute Force Approach
        int min = Math.min(a, b);
        int gcd = 1; // GCD starts at 1
        for (int i = 1; i <= min; i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i; // Update GCD if `i` divides both numbers
            }
        }

        // Calculate LCM using the formula: LCM = (a * b) / GCD
        int lcm = (a * b) / gcd;

        return new int[]{lcm, gcd};
    }

    public static int[] lcmAndGcdOptimal(int a, int b) {
        // Optimal Approach using the Euclidean Algorithm
        int gcd = 1;
        int originalA = a, originalB = b; // Save original values for LCM calculation
        while (a > 0 && b > 0) {
            if (a > b) {
                a = a % b; // Reduce `a`
            } else {
                b = b % a; // Reduce `b`
            }
        }
        if (a == 0) {
            gcd = b;
        } else {
            gcd = a;
        }

        // Calculate LCM using the formula: LCM = (originalA * originalB) / GCD
        int lcm = (originalA * originalB) / gcd;

        return new int[]{lcm, gcd};
    }

    public static void main(String[] args) {
        int a = 12, b = 18; // Example inputs

        // Brute Force Approach
        int[] resultBrute = lcmAndGcdBrute(a, b);
        System.out.println("Brute Force - LCM: " + resultBrute[0] + ", GCD: " + resultBrute[1]);

        // Optimal Approach
        int[] resultOptimal = lcmAndGcdOptimal(a, b);
        System.out.println("Optimal - LCM: " + resultOptimal[0] + ", GCD: " + resultOptimal[1]);
    }
}
