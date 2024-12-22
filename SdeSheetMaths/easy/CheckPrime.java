package SdeSheetMaths.easy;

public class CheckPrime {

        // Brute Force Approach
        static boolean isPrimeBruteForce(int n) {
            if (n <= 1) return false; // 0 and 1 are not prime numbers
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }

        // Count Divisors Approach
        static boolean isPrimeCountDivisors(int n) {
            if (n <= 1) return false; // 0 and 1 are not prime numbers
            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) {
                    cnt++;
                }
            }
            return cnt == 2;
        }

        // Optimal Approach
        static boolean isPrimeOptimal(int n) {
            if (n <= 1) return false; // 0 and 1 are not prime numbers
            int cnt = 0;
            for (int i = 1; i * i <= n; i++) {
                if (n % i == 0) {
                    cnt++;
                    if (n / i != i) {
                        cnt++;
                    }
                    if (cnt > 2) {
                        return false; // Not prime
                    }
                }
            }
            return cnt == 2;
        }

        // Main method to run all approaches
        public static void main(String[] args) {
            int n = 29; // Example input

            System.out.println("Brute Force: " + n + " is prime? " + isPrimeBruteForce(n));
            System.out.println("Count Divisors: " + n + " is prime? " + isPrimeCountDivisors(n));
            System.out.println("Optimal: " + n + " is prime? " + isPrimeOptimal(n));
        }

}
