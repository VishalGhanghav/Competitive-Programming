package SdeSheetBinarySearch.BinarySearchOn1dArray;

public class CountOccurenceOfNumberInSortedArray {
    static int countFreq(int[] nums, int target) {
        // code here
        int firstOccurence = getFirstAndLastOccurence(nums,target,"first");
        int lastOccurence = getFirstAndLastOccurence(nums,target,"last");
        if(firstOccurence == -1) {
            return 0;
        }
        return lastOccurence - firstOccurence +1;
    }

    private static int getFirstAndLastOccurence(int[] nums, int target, String flag) {
        int start = 0;
        int end = nums.length -1;
        int res = -1;
        while(start<=end) {
            int mid = start+ (end-start)/2;
            if(nums[mid] == target) {
                //potential ans and keep checking left
                res=mid;
                if (flag == "first") {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (nums[mid] < target) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] arr =  {2, 4, 6, 8, 8, 8, 11, 13};
        int n = 8, x = 8;
        int ans = countFreq(arr, x);
        System.out.println("The number of occurrences is: " + ans);
    }

}
