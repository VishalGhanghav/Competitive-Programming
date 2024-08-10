package SdeSheetDp.src.DpOnLIS;

import java.util.ArrayList;

public class LISusingBinarySearch {
    private static int binarySearch(ArrayList<Integer> list, int key) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) == key) {
                return mid;
            } else if (list.get(mid) < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low; // The insertion point for the key
    }

    public static int lengthOfLIS(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }

        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > temp.get(temp.size() - 1)) {
                // If arr[i] is greater than the last element of temp, extend the subsequence
                temp.add(arr[i]);
            } else {
                // If arr[i] is not greater, replace the element in temp with arr[i]
                int ind = binarySearch(temp, arr[i]);
                temp.set(ind, arr[i]);
            }
        }

        return temp.size();
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 10, 30, 20, 50};
        System.out.println("Length of Longest Increasing Subsequence: " + lengthOfLIS(arr));
    }
}
