package SdeSheetTwoPointerSliding.medium;

public class MaxConsecutiveOnesIII {
    //Brute
    //Approach: We will consider k number of zeroes as part of maxOnes and check
    //Time: O(N^2), Space: O(1)
    public static int longestOnesBrute(int[] nums, int k) {
        int n = nums.length;
        int res = 0;

        for (int i = 0; i < n; i++) {
            int countZero = 0;

            for (int j = i; j < n; j++) {
                if (nums[j] == 0) {
                    countZero++;
                }
                if (countZero > k) {
                    break;
                }
                res = Math.max(res, j - i + 1);
            }
        }

        return res;
    }

    //Optimal:
    //Since we are repeatedly checking same elements.We can apply sliding window
    //We will consider k number of zeroes as part of window
    //Conditions will be based on countZeroes
    //Time: O(N), Space: O(1)
    public static int longestOnesOptimal(int[] nums, int k) {
        int n = nums.length;
        int maxWindow = 0;
        int i = 0, j = 0;
        int countZero = 0;

        while (j < n) {
            if (nums[j] == 0) {
                countZero++;
            }
            // if(countZero < k) {
            //     j++;
            // } else
            if (countZero <= k) {
                maxWindow = Math.max(maxWindow, j - i + 1);
                //Still we can find bigger window ahead
                j++;
            } else if (countZero > k) {
                //Start removing zeroes from start
                while (countZero > k) {
                    if (nums[i] == 0) {
                        countZero--;
                    }
                    i++;
                }
                //After removing from start.We can get maxWindow
                maxWindow = Math.max(maxWindow, j - i + 1);
                j++;
            }
        }
        return maxWindow;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 0, 1, 1, 1, 0, 1};
        int k = 2;

        System.out.println("Brute Result: " + longestOnesBrute(nums, k));
        System.out.println("Optimal Result: " + longestOnesOptimal(nums, k));
    }
}
