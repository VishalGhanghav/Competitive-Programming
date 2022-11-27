package knapsackProblem;

public class TargetSum {
   public static void main(String[] args) {
      int[] arr = new int[]{1, -1, 2, 3};
      int n = arr.length;
      int sum = 1;
      System.out.println("The countOfSubsetWithGivenDifference " + targetSum(arr, sum));
   }

   private static int targetSum(int[] nums, int target) {
      int n = nums.length;
      int arrsum = 0;

      int sum;
      for(sum = 0; sum < n; ++sum) {
         arrsum += nums[sum];
      }

      if (arrsum >= target && arrsum + target >= 0 && (arrsum + target) % 2 == 0) {
         sum = (target + arrsum) / 2;
         int[][] dp = new int[n + 1][sum + 1];

         int i;
         int j;
         for(i = 0; i < n + 1; ++i) {
            for(j = 0; j < sum + 1; ++j) {
               if (i == 0) {
                  dp[i][j] = 0;
               }

               if (j == 0) {
                  dp[i][j] = 1;
               }
            }
         }

         for(i = 1; i < n + 1; ++i) {
            for(j = 0; j < sum + 1; ++j) {
               if (nums[i - 1] <= j) {
                  dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
               } else {
                  dp[i][j] = dp[i - 1][j];
               }
            }
         }

         return dp[n][sum];
      } else {
         return 0;
      }
   }
}
