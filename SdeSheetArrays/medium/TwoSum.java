package SdeSheetArrays.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    // Brute Force Approach: O(n^2) time complexity
    public int[] twoSumBruteForce(int[] arr, int target) {
        int n = arr.length;
        int[] res = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res; // Return if no pair is found
    }

    // Optimal Approach using HashMap: O(n) time complexity, O(n) space complexity
    public int[] twoSumHashMap(int[] arr, int target) {
        int n = arr.length;
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int req = target - arr[i];
            if (map.containsKey(req)) {
                res[0] = map.get(req);
                res[1] = i;
                return res;
            }
            map.put(arr[i], i);
        }
        return res; // Return if no pair is found
    }

    // Optimal Approach using Two Pointers: Works on sorted array but not for original indexes
    public int[] twoSumTwoPointers(int[] arr, int target) {
        int[] sortedArr = arr.clone(); // Clone array to maintain original indexes
        Arrays.sort(sortedArr); // Sort the array for two-pointer approach
        int left = 0, right = sortedArr.length - 1;
        int[] res = new int[2];
        while (left < right) {
            int sum = sortedArr[left] + sortedArr[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                res[0] = left;
                res[1] = right;
                return res;
            }
        }
        return res; // Return if no pair is found
    }

    public static void main(String[] args) {
        TwoSum ts = new TwoSum();
        int[] arr = {2, 7, 11, 15};
        int target = 9;

        // Testing Brute Force Approach
        int[] resultBrute = ts.twoSumBruteForce(arr, target);
        System.out.println("Brute Force Result: [" + resultBrute[0] + ", " + resultBrute[1] + "]");

        // Testing HashMap Approach
        int[] resultHashMap = ts.twoSumHashMap(arr, target);
        System.out.println("HashMap Result: [" + resultHashMap[0] + ", " + resultHashMap[1] + "]");

        // Testing Two Pointer Approach on sorted array
        int[] resultTwoPointers = ts.twoSumTwoPointers(arr, target);
        System.out.println("Two Pointers Result on Sorted Array: [" + resultTwoPointers[0] + ", " + resultTwoPointers[1] + "]");
    }
}
