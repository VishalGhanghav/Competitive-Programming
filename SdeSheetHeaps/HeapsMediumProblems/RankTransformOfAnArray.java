package SdeSheetHeaps.HeapsMediumProblems;

import java.util.*;

public class RankTransformOfAnArray {
    // Brute Force
    // TC: O(n^2)
    // SC: O(n)
    public static int[] brute(int[] arr) {
        // Brute
        // Approach: Check all elements by for loop
        // Since we need to ignore duplicates we use set
        int n = arr.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int rank = 1;
            Set<Integer> helperSet = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if (arr[j] < arr[i]) {
                    helperSet.add(arr[j]);
                }
            }
            res[i] = helperSet.size() + 1;
        }
        return res;
    }

    // Better Approach (Using Sorting + HashMap)
    // TC: O(n log n)
    // SC: O(n)
    public static int[] better(int[] arr) {
        // Better
        // Approach: Create copy array. Sort it.
        // Put in rankMap. Get its rank.
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }

        Arrays.sort(copy);
        Map<Integer, Integer> rankMap = new HashMap<>();
        int ind = 1;
        for (int num : copy) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, ind);
                ind++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rankMap.get(arr[i]);
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] input1 = {40, 10, 20, 30};
        int[] input2 = input1.clone();

        // Run Brute
        int[] bruteRes = brute(input1.clone());
        System.out.println("Brute Output: " + Arrays.toString(bruteRes));

        // Run Better
        int[] betterRes = better(input2.clone());
        System.out.println("Better Output: " + Arrays.toString(betterRes));
    }
}
