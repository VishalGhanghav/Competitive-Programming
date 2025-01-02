package SdeSheetMaths.hard;

import java.util.ArrayList;
import java.util.List;
public class PrintAllPrimeFactors {
    // Brute Force Approach
    public int[] bruteForcePrimeFactors(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                if (isPrime(i)) {
                    res.add(i);
                }
            }
        }
        return convertListToArray(res);
    }

    // Optimal 1 Approach
    public int[] optimalPrimeFactors1(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                res.add(i);
                while (n % i == 0) {
                    n = n / i;
                }
            }
        }
        return convertListToArray(res);
    }

    // Optimal 2 Approach (using sqrt(n))
    public int[] optimalPrimeFactors2(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                res.add(i);
                while (n % i == 0) {
                    n = n / i;
                }
            }
        }
        if (n != 1) {
            res.add(n);  // If n is still greater than 1, it's prime
        }
        return convertListToArray(res);
    }

    // Helper function to convert a list to an array
    private int[] convertListToArray(List<Integer> res) {
        int[] primes = new int[res.size()];
        int ind = 0;
        for (int no : res) {
            primes[ind++] = no;
        }
        return primes;
    }

    // Helper function to check if a number is prime (used in brute force approach)
    private boolean isPrime(int n) {
        int cnt = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                cnt++;
                if ((n / i) != i) {
                    cnt++;
                    if (cnt > 2) {
                        return false;
                    }
                }
            }
        }
        return cnt == 2;
    }

    public static void main(String[] args) {
        PrintAllPrimeFactors pf = new PrintAllPrimeFactors();

        // Test with different values of n
        int n = 36;
        System.out.println("Prime factors of " + n + " using Brute Force:");
        int[] bruteForceFactors = pf.bruteForcePrimeFactors(n);
        for (int factor : bruteForceFactors) {
            System.out.print(factor + " ");
        }
        System.out.println();

        System.out.println("Prime factors of " + n + " using Optimal Approach 1:");
        int[] optimalFactors1 = pf.optimalPrimeFactors1(n);
        for (int factor : optimalFactors1) {
            System.out.print(factor + " ");
        }
        System.out.println();

        System.out.println("Prime factors of " + n + " using Optimal Approach 2:");
        int[] optimalFactors2 = pf.optimalPrimeFactors2(n);
        for (int factor : optimalFactors2) {
            System.out.print(factor + " ");
        }
        System.out.println();

        // You can add more test cases below
        int n2 = 1000;
        System.out.println("Prime factors of " + n2 + " using Brute Force:");
        int[] bruteForceFactors2 = pf.bruteForcePrimeFactors(n2);
        for (int factor : bruteForceFactors2) {
            System.out.print(factor + " ");
        }
        System.out.println();
    }
}
