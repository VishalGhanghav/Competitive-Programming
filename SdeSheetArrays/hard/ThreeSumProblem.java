package SdeSheetArrays.hard;

import java.util.*;

public class ThreeSumProblem {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};

        // Call Brute Force approach
        System.out.println("Brute Force Result:");
        List<List<Integer>> bruteForceResult = threeSumBruteForce(nums);
        System.out.println(bruteForceResult);

        // Call Better approach
        System.out.println("Better Result:");
        List<List<Integer>> betterResult = threeSumBetter(nums);
        System.out.println(betterResult);

        // Call Optimal approach
        System.out.println("Optimal Result:");
        List<List<Integer>> optimalResult = threeSumOptimal(nums);
        System.out.println(optimalResult);
    }

    // Brute Force Approach
    public static List<List<Integer>> threeSumBruteForce(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Set<List<Integer>> uniqueTriplets = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(temp);
                        uniqueTriplets.add(temp);
                    }
                }
            }
        }
        res = new ArrayList<>(uniqueTriplets);
        return res;
    }

    // Better Approach
    public static List<List<Integer>> threeSumBetter(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Set<List<Integer>> uniqueTriplets = new HashSet<>();

        for (int i = 0; i < n; i++) {
            Set<Integer> tempSet = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                int third = -(nums[i] + nums[j]);
                if (tempSet.contains(third)) {
                    List<Integer> temp = Arrays.asList(nums[i], nums[j], third);
                    Collections.sort(temp);
                    uniqueTriplets.add(temp);
                }
                tempSet.add(nums[j]);
            }
        }
        res = new ArrayList<>(uniqueTriplets);
        return res;
    }

    // Optimal Approach
    public static List<List<Integer>> threeSumOptimal(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < 0) {
                    j++;
                } else if (sum > 0) {
                    k--;
                } else {
                    List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k]);
                    res.add(temp);
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
            }
        }
        return res;
    }
}

