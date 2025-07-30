package SdeSheetHeaps.HeapsHardProblems;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {
    // Brute approach: Store all elements in minHeap and clone it to find median
    // TC: O(log N) for addNumBrute (heap insert), O(N) for findMedianBrute (polling clone)
    // SC: O(N) for storing elements
    PriorityQueue<Integer> bruteMinHeap;

    // Optimal approach: Two Heaps
    // maxHeap for left half (smaller numbers), minHeap for right half (larger numbers)
    // TC: O(log N) for addNumOptimal, O(1) for findMedianOptimal
    // SC: O(N)
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    public FindMedianFromDataStream() {
        bruteMinHeap = new PriorityQueue<>();
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    // ---------------- BRUTE METHODS ----------------

    // TC: O(log N)
    // SC: O(N)
    public void addNumBrute(int num) {
        bruteMinHeap.add(num);
    }

    // TC: O(N)
    // SC: O(N) clone space
    public double findMedianBrute() {
        int size = bruteMinHeap.size();
        PriorityQueue<Integer> cloneHeap = new PriorityQueue<>(bruteMinHeap);

        for (int i = 0; i < (size + 1) / 2 - 1; i++) {
            cloneHeap.poll();
        }

        if (size % 2 == 1) {
            return (double) cloneHeap.poll();
        } else {
            return ((double) cloneHeap.poll() + cloneHeap.poll()) / 2;
        }
    }

    // ---------------- OPTIMAL METHODS ----------------

    // TC: O(log N)
    // SC: O(N)
    public void addNumOptimal(int num) {
        //first add in maxHeap
        maxHeap.add(num);
        //Add maximum from maxHeap in minHeap
        minHeap.add(maxHeap.poll());
        //If minHeap.size() > maxHeap.size() by 1(only possible) then
        //put smallest from minHeap in maxHeap
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    // TC: O(1)
    // SC: O(1)
    public double findMedianOptimal() {
        //We have maxHeap and minHeap implemented
        if ((maxHeap.size() + minHeap.size()) % 2 == 1) {
            return (double) maxHeap.peek();
        }
        return ((double) (maxHeap.peek() + minHeap.peek())) / 2;
    }

    // ---------------- MAIN TO TEST ----------------

    public static void main(String[] args) {
        FindMedianFromDataStream mf = new FindMedianFromDataStream();
        int[] stream = {5, 15, 1, 3};

        System.out.println("== Brute Approach ==");
        for (int num : stream) {
            mf.addNumBrute(num);
            System.out.println("Added: " + num);
            System.out.println("MinHeap (Brute): " + mf.bruteMinHeap);
            System.out.println("Current Median (Brute): " + mf.findMedianBrute());
            System.out.println("----------");
        }

        System.out.println("\n== Optimal Approach ==");
        FindMedianFromDataStream mf2 = new FindMedianFromDataStream(); // fresh instance
        for (int num : stream) {
            mf2.addNumOptimal(num);
            System.out.println("Added: " + num);
            System.out.println("MaxHeap (Left): " + mf2.maxHeap);
            System.out.println("MinHeap (Right): " + mf2.minHeap);
            System.out.println("Current Median (Optimal): " + mf2.findMedianOptimal());
            System.out.println("----------");
        }
    }
}
