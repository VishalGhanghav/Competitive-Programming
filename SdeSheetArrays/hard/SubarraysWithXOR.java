package SdeSheetArrays.hard;

import java.util.*;

public class SubarraysWithXOR {

    // Brute force approach: O(n^3)
    public static int subarraysXorBrute(ArrayList<Integer> arr, int k) {
        int n = arr.size();
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int xor = 0;
                // Calculate XOR for every subarray starting from i to j
                for (int l = i; l <= j; l++) {
                    xor = xor ^ arr.get(l);
                }
                if (xor == k) {
                    cnt++;  // Increment count if XOR matches k
                }
            }
        }
        return cnt;
    }

    // Better approach: O(n^2)
    public static int subarraysXorBetter(ArrayList<Integer> arr, int k) {
        int n = arr.size();
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            int xor = 0;
            // Calculate XOR for subarrays starting from index i
            for (int j = i; j < n; j++) {
                xor = xor ^ arr.get(j);
                if (xor == k) {
                    cnt++;  // Increment count if XOR matches k
                }
            }
        }
        return cnt;
    }

    // Optimal approach using prefix XOR and HashMap: O(n)
    public static int subarraysXorOptimal(ArrayList<Integer> arr, int k) {
        int n = arr.size();
        int cnt = 0;
        int xor = 0;

        // HashMap to store prefix XOR values and their frequency
        Map<Integer, Integer> xorMap = new HashMap<>();
        xorMap.put(0, 1);  // This handles the case when the subarray itself equals k

        for (int i = 0; i < n; i++) {
            // Prefix XOR till index i
            xor = xor ^ arr.get(i);

            // Find if there exists a prefix such that xor ^ prefix = k
            int x = xor ^ k;

            // If x exists in the map, increment count by its frequency
            if (xorMap.containsKey(x)) {
                cnt += xorMap.get(x);
            }

            // Update the map with the current XOR value
            xorMap.put(xor, xorMap.getOrDefault(xor, 0) + 1);
        }

        return cnt;
    }

    // Main method to test all three approaches
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(4, 2, 2, 6, 4));
        int k = 6;

        // Brute Force Approach
        System.out.println("Brute Force Approach:");
        int resultBrute = subarraysXorBrute(arr, k);
        System.out.println("Number of subarrays with XOR equal to " + k + " (Brute Force): " + resultBrute);

        // Better Approach
        System.out.println("\nBetter Approach:");
        int resultBetter = subarraysXorBetter(arr, k);
        System.out.println("Number of subarrays with XOR equal to " + k + " (Better): " + resultBetter);

        // Optimal Approach
        System.out.println("\nOptimal Approach:");
        int resultOptimal = subarraysXorOptimal(arr, k);
        System.out.println("Number of subarrays with XOR equal to " + k + " (Optimal): " + resultOptimal);
    }
}
