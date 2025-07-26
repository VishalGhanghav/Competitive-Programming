package SdeSheetHeaps.HeapsMediumProblems;

import java.util.*;

public class SortNearlySortedArray {

    // Brute Force: Sort the entire array
    public static void brute(int[] arr) {
        // Brute
        // TC: O(nlogn), SC: O(1)
        Arrays.sort(arr);
        System.out.println("Brute Output: " + Arrays.toString(arr));
    }

    // Optimal: Use a Min Heap of size k+1
    public static void optimal(int[] arr, int k) {
        // Optimal
        // Approach: I will go through elements and put in minHeap
        // and whatever new comes will go in result post k size

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int resInd = 0;
        for(int num : arr) {
            minHeap.add(num);
            if(minHeap.size() > k) {
                arr[resInd] = minHeap.poll();
                resInd++;
            }
        }

        // Fill remaining elements
        while(!minHeap.isEmpty()) {
            arr[resInd] = minHeap.poll();
            resInd++;
        }

        System.out.println("Optimal Output: " + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] input1 = {6, 5, 3, 2, 8, 10, 9};
        int[] input2 = input1.clone();
        int k = 3;

        // Run Brute
        brute(input1);

        // Run Optimal
        optimal(input2, k);
    }
}
