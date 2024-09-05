package SdeSheetArrays.easy;

import java.util.HashMap;
import java.util.Map;

public class NumAppearingOnceOthersTwice {
    // Better solution using Map
    public int findUniqueElementUsingMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        // Populate the map with frequency of each element
        for (int num : nums) {
            int value = map.getOrDefault(num, 0);
            map.put(num, value + 1);
        }

        // Iterate through the map to find the element with frequency 1
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey(); // Return the unique element
            }
        }

        return -1; // Return -1 if no unique element is found
    }

    // Optimal solution using XOR
    public int findUniqueElementUsingXOR(int[] nums) {
        int xor = 0;
        int n = nums.length;

        // XOR all elements; pairs of the same number cancel out each other
        for (int i = 0; i < n; i++) {
            xor = xor ^ nums[i];
        }

        // The remaining value is the unique element
        return xor;
    }

    public static void main(String[] args) {
        NumAppearingOnceOthersTwice finder = new NumAppearingOnceOthersTwice();

        int[] nums = {2, 3, 5, 4, 5, 3, 4};

        // Run and print the result using the Map-based approach
        System.out.println("Unique Element (Using Map): " + finder.findUniqueElementUsingMap(nums)); // Output should be 2

        // Run and print the result using the XOR-based approach
        System.out.println("Unique Element (Using XOR): " + finder.findUniqueElementUsingXOR(nums)); // Output should be 2
    }
}
