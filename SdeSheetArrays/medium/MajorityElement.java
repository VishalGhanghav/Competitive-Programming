package SdeSheetArrays.medium;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    // Function to find the majority element using the better approach
    public int majorityElement(int[] arr) {
        int n = arr.length;
        // Better Approach: Using a HashMap to store frequency of each element
        Map<Integer, Integer> map = new HashMap<>();
        if (arr.length == 1) {
            return arr[0];
        }
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
                // If frequency becomes greater than n/2, return the element
                if (map.get(num) > (n / 2)) {
                    return num;
                }
            } else {
                map.put(num, 1);
            }
        }
        return 0;
    }

    // Function to find the majority element using the brute force approach
    public int majorityElementBrute(int[] arr) {
        int n = arr.length;
        // Brute Force: Check the frequency of each element
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (arr[i] == arr[j]) {
                    cnt++;
                }
            }
            // If frequency becomes greater than n/2, return the element
            if (cnt > n / 2) {
                return arr[i];
            }
        }
        return 0;
    }

    // Function to find the majority element using the optimal approach (Moore's Voting Algorithm)
    public int majorityElementOptimal(int[] arr) {
        int n = arr.length;
        int ele = 0, cnt = 0;

        // Step 1: Find a candidate for majority element
        for (int i = 0; i < n; i++) {
            if (cnt == 0) {
                // If count is 0, we set the current element as the candidate
                ele = arr[i];
                cnt = 1;
            } else if (ele == arr[i]) {
                // If the current element is the same as our candidate, increase the count
                cnt++;
            } else {
                // If the current element is different, decrease the count
                cnt--;
            }
        }

        // At this point, `ele` is our candidate for the majority element.
        // However, we need to verify that it appears more than n/2 times.

        // Step 2: Verify the candidate
        int counter = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == ele) {
                counter++;
            }
        }

        // If the candidate occurs more than n/2 times, return it as the majority element
        if (counter > n / 2) {
            return ele;
        }

        // Return the candidate (although in most cases, it's guaranteed to be the majority element if it passes verification)
        return ele;
    }

    public static void main(String[] args) {
        MajorityElement finder = new MajorityElement();

        // Example array to test all approaches
        int[] arr = {2, 2, 1, 1, 1, 2, 2};

        // Brute Force Approach
        int resultBrute = finder.majorityElementBrute(arr);
        System.out.println("Majority Element (Brute Force): " + resultBrute);

        // Better Approach
        int resultBetter = finder.majorityElement(arr);
        System.out.println("Majority Element (Better): " + resultBetter);

        // Optimal Approach
        int resultOptimal = finder.majorityElementOptimal(arr);
        System.out.println("Majority Element (Optimal): " + resultOptimal);
    }
}

