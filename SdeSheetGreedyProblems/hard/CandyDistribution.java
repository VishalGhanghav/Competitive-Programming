package SdeSheetGreedyProblems.hard;

import java.util.Arrays;

public class CandyDistribution {

    // Brute Force Approach
    public int candyBruteForce(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        int[] right = new int[n];

        // Initialize left and right arrays
        left[0] = 1;
        right[n-1] = 1;

        // Fill left array based on increasing ratings
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i-1]) {
                left[i] = left[i-1] + 1;
            } else {
                left[i] = 1;
            }
        }

        // Fill right array based on decreasing ratings
        for (int i = n-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                right[i] = right[i+1] + 1;
            } else {
                right[i] = 1;
            }
        }

        // Calculate sum of max of left and right at each index
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.max(left[i], right[i]);
        }

        return sum;
    }

    // Better Approach: Reducing space to O(n)
    public int candyBetter(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];

        // Initialize the left array
        left[0] = 1;

        // Fill left array based on increasing ratings
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i-1]) {
                left[i] = left[i-1] + 1;
            } else {
                left[i] = 1;
            }
        }

        // Initialize variables for right side calculation
        int curr = 1, right = 1;
        int sum = Math.max(left[n-1], 1);  // Initialize sum with the last element

        // Calculate right side and sum
        for (int i = n-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                right = curr + 1;
            } else {
                right = 1;
            }
            sum += Math.max(left[i], right);
        }

        return sum;
    }

    // Optimal Approach: Slope-Based
    public int candyOptimal(int[] ratings) {
        int n = ratings.length;
        int sum = 1, i = 1;

        while (i < n) {
            // If flat surface
            if (ratings[i] == ratings[i-1]) {
                sum += 1;
                i++;
                continue;
            }

            // Increasing slope
            int peak = 1;
            while (i < n && ratings[i] > ratings[i-1]) {
                peak++;
                sum += peak;
                i++;
            }

            // Decreasing slope
            int down = 1;
            while (i < n && ratings[i] < ratings[i-1]) {
                sum += down;
                down++;
                i++;
            }

            // Adjust if the downward slope is greater than the peak
            if (peak < down) {
                sum += (down - peak);
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        CandyDistribution obj = new CandyDistribution();

        int[] ratings = {1, 3, 2, 2, 1};

        // Brute force approach
        int resultBrute = obj.candyBruteForce(ratings);
        System.out.println("Brute Force Result: " + resultBrute);

        // Better approach
        int resultBetter = obj.candyBetter(ratings);
        System.out.println("Better Approach Result: " + resultBetter);

        // Optimal approach
        int resultOptimal = obj.candyOptimal(ratings);
        System.out.println("Optimal Approach Result: " + resultOptimal);
    }
}

