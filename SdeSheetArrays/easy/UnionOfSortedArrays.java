package SdeSheetArrays.easy;

import java.util.*;

public class UnionOfSortedArrays {

    // Optimal approach using two-pointer technique
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m) {
        int i = 0, j = 0;
        ArrayList<Integer> union = new ArrayList<>();

        while (i < n && j < m) {
            if (arr1[i] <= arr2[j]) {
                // Add to union if it's the first element or different from the last added element
                if (union.size() == 0 || arr1[i] != union.get(union.size() - 1)) {
                    union.add(arr1[i]);
                }
                i++;
            } else {
                if (union.size() == 0 || arr2[j] != union.get(union.size() - 1)) {
                    union.add(arr2[j]);
                }
                j++;
            }
        }

        // Add remaining elements of arr2[]
        while (j < m) {
            if (union.size() == 0 || arr2[j] != union.get(union.size() - 1)) {
                union.add(arr2[j]);
            }
            j++;
        }

        // Add remaining elements of arr1[]
        while (i < n) {
            if (union.size() == 0 || arr1[i] != union.get(union.size() - 1)) {
                union.add(arr1[i]);
            }
            i++;
        }

        return union;
    }

    // Brute force approach using TreeSet
    public static ArrayList<Integer> findUnionBruteForce(int arr1[], int arr2[], int n, int m) {
        Set<Integer> s = new TreeSet<>();
        ArrayList<Integer> union = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            s.add(arr1[i]);
        }

        for (int i = 0; i < m; i++) {
            s.add(arr2[i]);
        }

        union.addAll(s);
        return union;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 6, 7};
        int n = arr1.length;
        int m = arr2.length;

        System.out.println("Using Optimal Approach:");
        ArrayList<Integer> resultOptimal = findUnion(arr1, arr2, n, m);
        System.out.println(resultOptimal);

        System.out.println("Using Brute Force Approach:");
        ArrayList<Integer> resultBruteForce = findUnionBruteForce(arr1, arr2, n, m);
        System.out.println(resultBruteForce);

        // Additional example 1
        int[] arr3 = {2, 2, 3, 4, 5};
        int[] arr4 = {1, 1, 2, 3, 4};

        System.out.println("Using Optimal Approach:");
        resultOptimal = findUnion(arr3, arr4, arr3.length, arr4.length);
        System.out.println(resultOptimal);

        System.out.println("Using Brute Force Approach:");
        resultBruteForce = findUnionBruteForce(arr3, arr4, arr3.length, arr4.length);
        System.out.println(resultBruteForce);

        // Additional example 2
        int[] arr5 = {1, 1, 1, 1, 1};
        int[] arr6 = {2, 2, 2, 2, 2};

        System.out.println("Using Optimal Approach:");
        resultOptimal = findUnion(arr5, arr6, arr5.length, arr6.length);
        System.out.println(resultOptimal);

        System.out.println("Using Brute Force Approach:");
        resultBruteForce = findUnionBruteForce(arr5, arr6, arr5.length, arr6.length);
        System.out.println(resultBruteForce);
    }
}
