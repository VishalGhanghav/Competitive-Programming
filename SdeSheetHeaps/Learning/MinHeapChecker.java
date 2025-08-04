package SdeSheetHeaps.Learning;

public class MinHeapChecker {

    // ? Brute Approach
    // TC: O(N), SC: O(1)
    public boolean countSubBrute(long arr[], long n) {
        // Your code goes here
        int ind = 0;
        while (hasLeftChild(ind, n)) {
            boolean flag = true;

            if (arr[getLeftChildIndex(ind)] > arr[ind]) {
                flag = false;
            }

            if (hasRightChild(ind, n) && arr[getRightChildIndex(ind)] > arr[ind]) {
                flag = false;
            }

            if (flag == false) {
                return false;
            }
            ind++;
        }
        return true;
    }

    private int getLeftChildIndex(int ind) {
        return 2 * ind + 1;
    }

    private int getRightChildIndex(int ind) {
        return 2 * ind + 2;
    }

    private boolean hasLeftChild(int ind, long size) {
        return getLeftChildIndex(ind) < size;
    }

    private boolean hasRightChild(int ind, long size) {
        return getRightChildIndex(ind) < size;
    }

    // ? Optimal Approach
    // TC: O(N), SC: O(1)
    public boolean countSubOptimal(long arr[], long n) {
        // Iterate each node and verify left & right child do not violate min heap property
        for (int i = 0; i < n; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[left] > arr[i]) {
                return false;
            }

            if (right < n && arr[right] > arr[i]) {
                return false;
            }
        }
        return true;
    }

    // ? Main method to test both
    public static void main(String[] args) {
        MinHeapChecker obj = new MinHeapChecker();

        long[] heapArr = {10, 15, 14, 25, 30}; // Valid min-heap
        long n = heapArr.length;

        System.out.println("Brute Approach: " + obj.countSubBrute(heapArr, n));
        System.out.println("Optimal Approach: " + obj.countSubOptimal(heapArr, n));

        long[] nonHeapArr = {10, 9, 8, 7}; // Not a min-heap
        System.out.println("Brute Approach (Invalid): " + obj.countSubBrute(nonHeapArr, nonHeapArr.length));
        System.out.println("Optimal Approach (Invalid): " + obj.countSubOptimal(nonHeapArr, nonHeapArr.length));
    }
}

