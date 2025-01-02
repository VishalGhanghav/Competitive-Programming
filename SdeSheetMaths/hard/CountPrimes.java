package SdeSheetMaths.hard;
import java.util.Arrays;

public class CountPrimes {
    // Method to count the number of prime numbers less than 'n'
    public int countPrimes(int n) {
        int[] prime = new int[n + 1]; // Array to mark prime numbers
        int cnt = 0; // Counter for prime numbers
        Arrays.fill(prime, 1); // Initialize all entries as prime (1)

        // Sieve of Eratosthenes: Mark non-prime numbers
        for (int i = 2; i * i < n; i++) {
            if (prime[i] == 1) { // If 'i' is prime
                for (int j = i * i; j < n; j += i) { // Mark multiples of 'i'
                    prime[j] = 0;
                }
            }
        }

        // Count the numbers marked as prime
        for (int j = 2; j < n; j++) {
            if (prime[j] == 1) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        CountPrimes obj = new CountPrimes();

        int n = 20; // Example input 2 3 5 7 11 13 17 19
        System.out.println("Number of primes less than " + n + ": " + obj.countPrimes(n));
    }
}
