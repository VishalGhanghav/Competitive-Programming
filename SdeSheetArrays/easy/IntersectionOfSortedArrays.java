package SdeSheetArrays.easy;

import java.util.ArrayList;

public class IntersectionOfSortedArrays {

    // Optimal approach using two-pointer technique
    public static ArrayList<Integer> findArrayIntersection(ArrayList<Integer> arr1, int m, ArrayList<Integer> arr2, int n) {
        int i = 0, j = 0; // Initialize two pointers for arr1 and arr2
        ArrayList<Integer> ans = new ArrayList<>(); // Initialize the result list

        // Traverse both arrays until one of them is exhausted
        while (i < m && j < n) {
            if (arr1.get(i) < arr2.get(j)) {
                // If the current element of arr1 is smaller, move the pointer in arr1
                i++;
            } else if (arr2.get(j) < arr1.get(i)) {
                // If the current element of arr2 is smaller, move the pointer in arr2
                j++;
            } else {
                // If both elements are equal, add to the result and move both pointers
                ans.add(arr1.get(i));
                i++;
                j++;
            }
        }

        return ans; // Return the intersection list
    }

    // Brute force approach to find the intersection of two sorted arrays
    public static ArrayList<Integer> findArrayIntersectionBruteForce(ArrayList<Integer> arr1, int m, ArrayList<Integer> arr2, int n) {
        int[] vis = new int[n]; // Visited array to keep track of elements already matched
        ArrayList<Integer> ans = new ArrayList<>(); // Initialize the result list

        // Traverse the first array
        for (int i = 0; i < m; i++) {
            // For each element in arr1, traverse the second array
            for (int j = 0; j < n; j++) {
                if (arr1.get(i) == arr2.get(j) && vis[j] == 0) {
                    // If a match is found and the element is not yet visited, add to the result
                    ans.add(arr1.get(i));
                    vis[j] = 1; // Mark as visited
                    break; // Stop further comparisons for this element
                } else if (arr2.get(j) > arr1.get(i)) {
                    // Since arrays are sorted, no need to check further if arr2[j] is greater
                    break;
                }
            }
        }

        return ans; // Return the intersection list
    }

    public static void main(String[] args) {
        // Test case 1
        ArrayList<Integer> arr1 = new ArrayList<>();
        arr1.add(1);
        arr1.add(2);
        arr1.add(2);
        arr1.add(3);
        arr1.add(4);
        int m = arr1.size();

        ArrayList<Integer> arr2 = new ArrayList<>();
        arr2.add(2);
        arr2.add(2);
        arr2.add(4);
        arr2.add(6);
        arr2.add(7);
        int n = arr2.size();

        // Run and print the result of the optimal approach
        System.out.println("Using Optimal Approach:");
        ArrayList<Integer> resultOptimal = findArrayIntersection(arr1, m, arr2, n);
        System.out.println(resultOptimal);

        // Run and print the result of the brute force approach
        System.out.println("Using Brute Force Approach:");
        ArrayList<Integer> resultBruteForce = findArrayIntersectionBruteForce(arr1, m, arr2, n);
        System.out.println(resultBruteForce);

        // Additional test case 2
        ArrayList<Integer> arr3 = new ArrayList<>();
        arr3.add(1);
        arr3.add(2);
        arr3.add(3);
        arr3.add(4);
        arr3.add(5);
        int m2 = arr3.size();

        ArrayList<Integer> arr4 = new ArrayList<>();
        arr4.add(3);
        arr4.add(4);
        arr4.add(5);
        arr4.add(6);
        arr4.add(7);
        int n2 = arr4.size();

        // Run and print the result of the optimal approach
        System.out.println("Using Optimal Approach:");
        resultOptimal = findArrayIntersection(arr3, m2, arr4, n2);
        System.out.println(resultOptimal);

        // Run and print the result of the brute force approach
        System.out.println("Using Brute Force Approach:");
        resultBruteForce = findArrayIntersectionBruteForce(arr3, m2, arr4, n2);
        System.out.println(resultBruteForce);
    }
}
