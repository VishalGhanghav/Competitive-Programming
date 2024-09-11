package SdeSheetArrays.medium;

import java.util.ArrayList;
import java.util.Collections;

class LeadersInAnArray {

    // Brute force approach to find leaders
    static ArrayList<Integer> leadersBruteForce(int n, int arr[]) {
        ArrayList<Integer> leadersList = new ArrayList<>();

        /*
        The brute force approach compares each element with every other element
        to its right to see if it's a leader.
        */
        for (int i = 0; i < n; i++) {
            boolean leader = true;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    leader = false; // Not a leader if any element on the right is larger
                    break;
                }
            }
            if (leader) {
                leadersList.add(arr[i]); // Add the leader to the list
            }
        }
        return leadersList;
    }

    // Optimal approach to find leaders
    static ArrayList<Integer> leadersOptimal(int n, int arr[]) {
        ArrayList<Integer> leadersList = new ArrayList<>();

        /*
        In the optimal approach, we traverse the array from right to left.
        We keep track of the maximum element encountered so far (maxFromRight).
        If the current element is greater than or equal to maxFromRight, it is a leader.
        */
        int maxFromRight = Integer.MIN_VALUE;  // Initialize maxFromRight with the smallest possible value
        for (int i = n - 1; i >= 0; i--) {     // Traverse from right to left
            if (arr[i] >= maxFromRight) {      // If the current element is greater or equal to maxFromRight
                maxFromRight = arr[i];         // Update maxFromRight
                leadersList.add(arr[i]);       // Add current element to leadersList
            }
        }
        // Reverse the list as the leaders are collected from right to left
        Collections.reverse(leadersList);
        return leadersList;
    }

    // Main method for testing both brute force and optimal approaches
    public static void main(String[] args) {
        // Example input arrays
        int[] arr1 = {16, 17, 4, 3, 5, 2};  // Expected leaders: [17, 5, 2]
        int[] arr2 = {1, 2, 3, 4, 5};       // Expected leaders: [5]
        int[] arr3 = {5, 4, 3, 2, 1};       // Expected leaders: [5, 4, 3, 2, 1]

        // Test case 1 using Brute Force
        System.out.println("Test case 1 (Brute Force): Original array: [16, 17, 4, 3, 5, 2]");
        ArrayList<Integer> bruteLeaders1 = leadersBruteForce(arr1.length, arr1);
        System.out.println("Leaders (Brute Force): " + bruteLeaders1);

        // Test case 1 using Optimal Approach
        System.out.println("Test case 1 (Optimal): Original array: [16, 17, 4, 3, 5, 2]");
        ArrayList<Integer> optimalLeaders1 = leadersOptimal(arr1.length, arr1);
        System.out.println("Leaders (Optimal): " + optimalLeaders1);

        // Test case 2 using Brute Force
        System.out.println("\nTest case 2 (Brute Force): Original array: [1, 2, 3, 4, 5]");
        ArrayList<Integer> bruteLeaders2 = leadersBruteForce(arr2.length, arr2);
        System.out.println("Leaders (Brute Force): " + bruteLeaders2);

        // Test case 2 using Optimal Approach
        System.out.println("Test case 2 (Optimal): Original array: [1, 2, 3, 4, 5]");
        ArrayList<Integer> optimalLeaders2 = leadersOptimal(arr2.length, arr2);
        System.out.println("Leaders (Optimal): " + optimalLeaders2);

        // Test case 3 using Brute Force
        System.out.println("\nTest case 3 (Brute Force): Original array: [5, 4, 3, 2, 1]");
        ArrayList<Integer> bruteLeaders3 = leadersBruteForce(arr3.length, arr3);
        System.out.println("Leaders (Brute Force): " + bruteLeaders3);

        // Test case 3 using Optimal Approach
        System.out.println("Test case 3 (Optimal): Original array: [5, 4, 3, 2, 1]");
        ArrayList<Integer> optimalLeaders3 = leadersOptimal(arr3.length, arr3);
        System.out.println("Leaders (Optimal): " + optimalLeaders3);
    }
}

