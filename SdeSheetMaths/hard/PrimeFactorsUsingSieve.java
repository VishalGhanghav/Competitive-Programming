package SdeSheetMaths.hard;

import java.util.*;

class PrimeFactorsUsingSieve {
    // Maximum number up to which the smallest prime factor (SPF) will be computed
    static int maxN = (int) (2 * Math.pow(10, 5)); // 2 * 10^5
    // Array to store the smallest prime factor for every number from 1 to maxN
    static int[] spf = new int[maxN + 1];

    // You must implement this function
    static void sieve() {
        // Here we will implement it upto some max value.
        // So this can be later used for all. It's computed only once

        // Initialize each number's smallest prime factor to itself
        for (int i = 1; i <= maxN; i++) {
            spf[i] = i;
        }

        // Start the sieve process: find the smallest prime factor for each number
        for (int i = 2; i * i <= maxN; i++) {
            // eg. spf(2) == 2
            if (spf[i] == i) {
                // j = i * i to avoid repetitive calculation
                for (int j = i * i; j <= maxN; j += i) {
                    // If even at multiples same element present at j, then replace with i
                    // If 4 has 4, then replace with 2. eg. i = 2, j = 4
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
    }

    // Function to find the prime factors of a number 'n' using the precomputed smallest prime factor (SPF) array
    static List<Integer> findPrimeFactors(int n) {
        List<Integer> primeFactors = new ArrayList<>();

        // Use the SPF array to decompose n into its prime factors
        while (n != 1) {
            primeFactors.add(spf[n]);  // Add the smallest prime factor of n to the list
            n = n / spf[n];  // Divide n by its smallest prime factor to reduce it
        }

        return primeFactors;
    }

    // Main function to test the sieve and prime factorization
    public static void main(String[] args) {
        // Step 1: Precompute the smallest prime factors for all numbers up to maxN
        sieve();

        int[] testNumbers = {120, 42, 60, 81, 100, 37};

        // Step 2: Loop through each test number and find its prime factors
        for (int testNumber : testNumbers) {
            List<Integer> primeFactors = findPrimeFactors(testNumber);

            // Step 3: Print the prime factors of the test number
            System.out.println("Prime factors of " + testNumber + ": " + primeFactors);
        }
    }
}
