package SdeSheetBinarySearch.BinarySearchOnAnswer;

public class CapacityToShipPackages {

        //Brute-force version
        public static int shipWithinDaysBrute(int[] weights, int days) {
            int n = weights.length;
            //We need the minimum capacity and it will be the smallest in array
            //The maximum capacity will be the sum of all elements in array
            //we can calculate mini and maxi in one loop as well
            int mini = getMaximumInArray(weights);
            int maxi = getSumOfArray(weights);

            //We check for each capacity from start and if its possible return the capacity
            //Brute:TC O((maxi-mini+1)*n)
            for(int i=mini;i<=maxi;i++) {
                if(isPossible(weights,days,i)) {
                    return i;
                }
            }
            return -1;
        }

        //Binary Search version (Optimal)
        public static int shipWithinDays(int[] weights, int days) {
            int n = weights.length;
            //We need the minimum capacity and it will be the smallest in array
            //The maximum capacity will be the sum of all elements in array
            //we can calculate mini and maxi in one loop as well
            int mini = getMaximumInArray(weights);
            int maxi = getSumOfArray(weights);

            //Optimal :Use binary search
            int start = mini;
            int end = maxi;

            while(start<=end) {
                int mid = start + (end-start)/2;
                //if mid is possible check on left for smaller
                if(isPossible(weights,days,mid)) {
                    //ans =mid
                    end = mid-1;
                } else {
                    start = mid+1;
                }
            }
            return start;//or return ans
        }

        private static boolean isPossible(int[] nums,int days,int capacity) {
            int cnt=0;
            int tempSum = 0;
            for(int num:nums) {
                if((tempSum+num) > capacity) {
                    cnt++;
                    tempSum=0;
                }
                tempSum +=num;
            }
            //The last elmt will be left of count
            //eg:weights = [1,2,3,4,5,6,7,8,9,10], days = 5.Last day tempSum=10.but that weight is not yet loaded in ship.
            //lets load it and increase counter
            cnt++;
            return cnt<=days;
        }

        private static int getMaximumInArray(int[] arr) {
            int maxi = Integer.MIN_VALUE;
            for(int elmt:arr) {
                maxi = Math.max(maxi,elmt);
            }
            return maxi;
        }

        private static int getSumOfArray(int[] arr) {
            int sum=0;
            for(int elmt:arr) {
                sum += elmt;
            }
            return sum;
        }

        public static void main(String[] args) {
            int[] weights = {1, 2, 3, 1, 1};
            int days = 4;

            int bruteResult = shipWithinDaysBrute(weights, days);
            System.out.println("Brute-force Result: " + bruteResult);

            int optimalResult = shipWithinDays(weights, days);
            System.out.println("Binary Search Result: " + optimalResult);
        }

}
