package SdeSheetHeaps.Learning;

import java.util.Arrays;

public class MinToMaxHeapConverter {

    // ? Convert Min Heap to Max Heap
    // TC: O(N), SC: O(logN) recursive stack space
    static void convertMinToMaxHeap(int n, int arr[]) {
        // code here
        // I will get the last non-leaf node by n/2-1
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDown(arr, n, i);
        }
    }

    private static void heapifyDown(int[] arr, int n, int ind) {
        int left = 2 * ind + 1;
        int right = 2 * ind + 2;
        int largest = ind;
        // Approach: I find largest between curr and childs
        // and then if current is not the largest then
        // swap curr and largest values and heapify the largest index to check more levels
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not currInd means we need to heapify
        if (largest != ind) {
            int temp = arr[ind];
            arr[ind] = arr[largest];
            arr[largest] = temp;
            heapifyDown(arr, n, largest);
        }
    }

    // ? Main method to test conversion
    public static void main(String[] args) {
        int[] minHeap = {1, 3, 5, 7, 9, 11}; // A valid Min Heap
        int n = minHeap.length;

        System.out.println("Original Min Heap: " + Arrays.toString(minHeap));

        convertMinToMaxHeap(n, minHeap);

        System.out.println("Converted Max Heap: " + Arrays.toString(minHeap));
    }
}
