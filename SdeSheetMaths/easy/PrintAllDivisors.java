package SdeSheetMaths.easy;

import java.util.ArrayList;
import java.util.Collections;

public class PrintAllDivisors {
    // Brute force approach to print divisors
    public static void printDivisorsBruteForce(int n) {
        System.out.print("Divisors using Brute Force: ");
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // Optimized approach to print divisors (unsorted)
    public static void printDivisorsOptimizedUnsorted(int n) {
        System.out.print("Divisors using Optimized (Unsorted): ");
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
                if ((n / i) != i) { // Avoid printing duplicates for perfect squares
                    System.out.print((n / i) + " ");
                }
            }
        }
        System.out.println();
    }

    // Optimized approach to print divisors (sorted)
    public static void printDivisorsOptimizedSorted(int n) {
        System.out.print("Divisors using Optimized (Sorted): ");
        ArrayList<Integer> divisors = new ArrayList<>();

        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if ((n / i) != i) { // Avoid duplicates for perfect squares
                    divisors.add(n / i);
                }
            }
        }

        // Sort the divisors
        Collections.sort(divisors);
        for (int divisor : divisors) {
            System.out.print(divisor + " ");
        }
        System.out.println();
    }

    // Main method to test both approaches
    public static void main(String[] args) {
        int n = 36; // Example input

        // Test brute force approach
        printDivisorsBruteForce(n);

        // Test optimized approach (unsorted)
        printDivisorsOptimizedUnsorted(n);

        // Test optimized approach (sorted)
        printDivisorsOptimizedSorted(n);
    }
}
