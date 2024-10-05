package SdeSheetArrays.hard;

import java.util.*;

public class MajorityElementNBy3 {

    // Brute force approach to find majority elements
    public List<Integer> majorityElementBrute(int[] nums) {
        List<Integer> majorityList = new ArrayList<>();
        int n = nums.length;

        // Loop over the array to find majority elements
        for (int i = 0; i < n; i++) {
            if (majorityList.size() == 0 || !majorityList.contains(nums[i])) {
                int cnt = 0;
                // Count the occurrences of nums[i]
                for (int j = i; j < n; j++) {
                    if (nums[j] == nums[i]) {
                        cnt++;
                    }
                }
                // Check if the count is greater than n/3
                if (cnt > n / 3) {
                    majorityList.add(nums[i]);
                }
            }
            // We only need a maximum of 2 elements
            if (majorityList.size() == 2) {
                break;
            }
        }
        return majorityList;
    }

    // Better approach using a frequency map
    public List<Integer> majorityElementBetter(int[] nums) {
        List<Integer> majorityList = new ArrayList<>();
        int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Build frequency map for each element
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Check the frequencies against n/3
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > n / 3) {
                majorityList.add(entry.getKey());
            }
        }
        return majorityList;
    }

    // Optimal approach using Boyer-Moore's Voting Algorithm
    public List<Integer> majorityElementOptimal(int[] nums) {
        int n = nums.length;
        int cnt1 = 0, cnt2 = 0, ele1 = Integer.MIN_VALUE, ele2 = Integer.MIN_VALUE;

        // First pass to find potential candidates
        for (int num : nums) {
            if (cnt1 == 0 && ele2 != num) {
                ele1 = num;
                cnt1 = 1;
            } else if (cnt2 == 0 && ele1 != num) {
                ele2 = num;
                cnt2 = 1;
            } else if (ele1 == num) {
                cnt1++;
            } else if (ele2 == num) {
                cnt2++;
            } else {
                cnt1--;
                cnt2--;
            }
        }

        // Second pass to verify the candidates
        cnt1 = 0;
        cnt2 = 0;
        for (int num : nums) {
            if (num == ele1) cnt1++;
            if (num == ele2) cnt2++;
        }

        List<Integer> result = new ArrayList<>();
        int threshold = n / 3;
        if (cnt1 > threshold) result.add(ele1);
        if (cnt2 > threshold) result.add(ele2);

        return result;
    }

    // Main method to call all three approaches
    public static void main(String[] args) {
        MajorityElementNBy3 me = new MajorityElementNBy3();
        int[] nums = {3, 2, 3, 1, 1, 1, 2, 2};

        System.out.println("Input array: " + Arrays.toString(nums));

        // Brute force approach
        System.out.println("Brute force result: " + me.majorityElementBrute(nums));

        // Better approach
        System.out.println("Better approach result: " + me.majorityElementBetter(nums));

        // Optimal approach (Boyer-Moore)
        System.out.println("Optimal approach result: " + me.majorityElementOptimal(nums));
    }
}
