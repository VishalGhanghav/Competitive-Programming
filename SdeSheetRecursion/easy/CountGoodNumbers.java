package SdeSheetRecursion.easy;

public class CountGoodNumbers {
    // ===============================================
    // Approach 1: Recursive Brute Force
    // TC: O(5^(n/2) * 4^(n/2)) ? Exponential, works only for small n
    // SC: O(n) recursion stack
    // ===============================================
    public static final int MOD = 1000000007;

    public static long countGoodNumbersRecursiveHelper(long n, int idx) {
        /*
        Original Comments Preserved:
        - At idx=0 (even), we have 5 choices ? branch into 5 recursive calls
        - At idx=1 (odd), we have 4 choices ? branch into 4 recursive calls
        - Base case: idx == n ? return 1
        */
        if (idx == n) {
            return 1;
        }

        long count = 0;
        if (idx % 2 == 0) {
            // even position
            int[] even = {0, 2, 4, 6, 8};
            for (int no : even) {
                count = (count + countGoodNumbersRecursiveHelper(n, idx + 1)) % MOD;
            }
        } else {
            // odd position
            int[] primes = {2, 3, 5, 7};
            for (int d : primes) {
                count = (count + countGoodNumbersRecursiveHelper(n, idx + 1)) % MOD;
            }
        }
        return count;
    }

    public static int countGoodNumbersRecursive(long n) {
        return (int) (countGoodNumbersRecursiveHelper(n, 0) % MOD);
    }

    // ===============================================
    // Approach 2: Optimal Binary Exponentiation
    // TC: O(log n) per exponentiation ? overall O(log n)
    // SC: O(1)
    // ===============================================
    public static long helper(long base, long expo, long mod) {
        /*
        Original Comments Preserved:
        - Binary exponentiation to compute (base^expo) % mod
        - If expo is even: base = base^2, expo /= 2
        - If expo is odd: ans *= base, expo--
        */
        long ans = 1;
        while (expo > 0) {
            if (expo % 2 == 0) {
                // x^m = (x^2)^(m/2)
                base = (base * base) % mod;
                expo = expo / 2;
            } else {
                // Odd: multiply once and reduce exponent
                ans = (ans * base) % mod;
                expo -= 1;
            }
        }
        return ans;
    }

    public static int countGoodNumbersOptimal(long n) {
        if (n == 1) return 5;

        long even = (n + 1) / 2; // number of even positions
        long odd = n / 2;        // number of odd positions
        long mod = 1000000007;

        // Result = 5^even * 4^odd % mod
        return (int) ((helper(5, even, mod) * helper(4, odd, mod)) % mod);
    }

    // ===============================================
    // Main Method: Testing both approaches independently
    // ===============================================
    public static void main(String[] args) {
        long[] smallTestCases = {1, 2, 3}; // small for recursive
        long[] largeTestCases = {1, 2, 5, 10, 50, 1000000000L}; // for optimal

        System.out.println("=== Testing Recursive Brute Force (Small n only) ===");
        for (long n : smallTestCases) {
            System.out.println("n = " + n + " ? countGoodNumbersRecursive = " + countGoodNumbersRecursive(n));
        }

        System.out.println("\n=== Testing Optimal Approach ===");
        for (long n : largeTestCases) {
            System.out.println("n = " + n + " ? countGoodNumbersOptimal = " + countGoodNumbersOptimal(n));
        }
    }
}
