package SdeSheetBinarySearch.BinarySearchOnAnswer;

public class SplitArrayLargestSum {

        public static void main(String[] args) {
            int[] nums = {7, 2, 5, 10, 8};
            int k = 2;

            System.out.println("Brute Force Result: " + splitArrayBrute(nums, k));
            System.out.println("Optimal Result: " + splitArrayOptimal(nums, k));
        }

        //brute
        public static int splitArrayBrute(int[] nums, int k) {
            int n = nums.length;
            int arrSum = 0;
            int maxInArray = Integer.MIN_VALUE;
            if(n < k) {
                return -1;
            }
            for(int i=0;i<n;i++) {
                arrSum += nums[i];
                maxInArray = Math.max(maxInArray,nums[i]);
            }


            for(int i=maxInArray;i<=arrSum;i++) {
                if(isPossible(nums,k,i)) {
                    return i;
                }
            }
            return -1;
        }

        //optimal
        public static int splitArrayOptimal(int[] nums, int k) {
            int n = nums.length;
            int arrSum = 0;
            int maxInArray = Integer.MIN_VALUE;
            if(n < k) {
                return -1;
            }
            for(int i=0;i<n;i++) {
                arrSum += nums[i];
                maxInArray = Math.max(maxInArray,nums[i]);
            }

            int start = maxInArray;
            int end = arrSum;

            while(start <= end) {
                int mid = start + (end - start) / 2;
                if(isPossible(nums, k, mid)) {
                    //check if we can minimize sum more
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            //just think of scenario when all mid strt end mid at answer.We will go end=mid-1.
            //But start stays at answer itself
            return start;
        }

        private static boolean isPossible(int[] nums, int k, int reqSplitSum) {
            int cnt = 1;
            int sum = 0;
            for(int num : nums) {
                if((sum + num) > reqSplitSum) {
                    cnt++;
                    sum = 0;
                    if(cnt > k) {
                        return false;
                    }
                }
                sum += num;
            }
            return true;
        }

}
