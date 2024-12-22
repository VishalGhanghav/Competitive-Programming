package SdeSheetMaths.easy;

public class SumOfAllDivisors {
    // Method using brute force
    public static int sumOfDivisorsBruteForce(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += getSumOfDivisorBruteForce(i);
        }
        return sum;
    }

    private static int getSumOfDivisorBruteForce(int no) {
        int sumOfDivisors = 0;
        for (int i = 1; i <= no; i++) {
            if (no % i == 0) {
                sumOfDivisors += i;
            }
        }
        return sumOfDivisors;
    }

    // Method using optimized approach
    public static int sumOfDivisorsOptimized(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += getSumOfDivisorOptimized(i);
        }
        return sum;
    }

    private static int getSumOfDivisorOptimized(int no) {
        int sumOfDivisors = 0;
        for (int i = 1; i * i <= no; i++) {
            if (no % i == 0) {
                sumOfDivisors += i;
                if (no / i != i) {
                    sumOfDivisors += (no / i);
                }
            }
        }
        return sumOfDivisors;
    }

    // Main method to test both approaches
    public static void main(String[] args) {
        int n = 10; // Example input

        // Testing brute force approach
        System.out.println("Brute Force Approach:");
        System.out.println("Sum of divisors of all numbers from 1 to " + n + " = " + sumOfDivisorsBruteForce(n));

        // Testing optimized approach
        System.out.println("Optimized Approach:");
        System.out.println("Sum of divisors of all numbers from 1 to " + n + " = " + sumOfDivisorsOptimized(n));
    }
}
