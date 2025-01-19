package SdeSheetMaths.hard;

public class PowerExponentiation {
    public double myPow(double x, int n) {
        double ans = 1d;
        // n = -2147483648. This is the min value for an integer. Negating it is not possible.
        // So, we used long to avoid test case failure.
        long m = n;

        // If n is negative, we will pass -m (i.e., positive) for computation.
        if (m < 0) {
            x = 1 / x;
            m = -m;
        }

        // Exponentiation by squaring method
        while (m > 0) {
            // If m is odd, multiply the result by x
            if (m % 2 == 1) {
                ans *= x;
            }
            // Square x and reduce m by half
            x *= x;
            m /= 2;
        }

        return ans;
    }

    public static void main(String[] args) {
        PowerExponentiation solution = new PowerExponentiation();

        // Test cases
        System.out.println(solution.myPow(2.00000, 10));  // Expected: 1024.00000
        System.out.println(solution.myPow(2.10000, 3));   // Expected: 9.26100
        System.out.println(solution.myPow(2.00000, -2));  // Expected: 0.25000
        System.out.println(solution.myPow(2.00000, -2147483648)); // Handling edge case of Integer.MIN_VALUE
        System.out.println(solution.myPow(0.00001, 2147483647)); // Large exponent test
        System.out.println(solution.myPow(1.00000, 2147483647)); // x = 1 test
        System.out.println(solution.myPow(2.00000, -1));  // Expected: 0.50000
    }
}
