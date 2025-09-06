package SdeSheetRecursion.easy;

public class XToPowerN {
    // ===============================================
    // Approach 1: Brute Force
    // TC: O(n) where n = absolute value of exponent
    // SC: O(1)
    // ===============================================
    public static double myPowBrute(double x, int n) {
        /*
        Brute Force:
        Multiply x repeatedly n times
        Handle negative exponent by taking reciprocal
        */
        double ans = 1d;
        long m = n; // store in long to handle overflow
        if (m < 0) {
            m = -m; // work with positive exponent
        }

        for (long i = 0; i < m; i++) {
            ans = ans * x;
        }

        if (n < 0) return 1d / ans;
        return ans;
    }

    // ===============================================
    // Approach 2: Optimal (Binary Exponentiation)
    // TC: O(log n) where n = absolute value of exponent
    // SC: O(1)
    // ===============================================
    public static double myPowOptimal(double x, int n) {
        /*
        Key Idea (Binary Exponentiation):

        Even exponent:
        x^m = (x^2)^(m/2)

        Odd Exponent:
        x^m = x * x^(m-1)

        Reduce it to even by multiplying ans with x once.
        */
        double ans = 1d;
        long m = n; // store in long to handle overflow
        if (m < 0) {
            m = -m; // work with positive exponent
        }

        while (m > 0) {
            if (m % 2 == 1) {
                // Odd: multiply once and reduce exponent
                ans = ans * x;
                m = m - 1;
            } else {
                // Even: square x and halve exponent
                x = x * x;
                m = m / 2;
            }
        }

        if (n < 0) {
            ans = 1.0 / ans;
        }

        return ans;
    }

    // ===============================================
    // Main Method: Testing both approaches independently
    // ===============================================
    public static void main(String[] args) {
        System.out.println("=== Testing Brute Force Approach ===");
        double[][] testCases = {
                {2.0, 10},
                {2.1, 3},
                {2.0, -2},
                {0.5, 4},
                {3.0, 0},
                {-2.0, 3}
        };

        for (double[] tc : testCases) {
            double x = tc[0];
            int n = (int) tc[1];
            System.out.println("myPowBrute(" + x + ", " + n + ") = " + myPowBrute(x, n));
        }

        System.out.println("\n=== Testing Optimal Approach ===");
        for (double[] tc : testCases) {
            double x = tc[0];
            int n = (int) tc[1];
            System.out.println("myPowOptimal(" + x + ", " + n + ") = " + myPowOptimal(x, n));
        }
    }
}
