package SdeSheetHeaps.HeapsMediumProblems;

import java.util.*;

public class HandOfStraights {

    // Brute Force using MinHeap + .remove()
    // TC: O(n * k * n) in worst case (n = hand.length, k = groupSize) because PriorityQueue.remove(val) is O(n)
    // SC: O(n) for heap
    public static boolean brute(int[] hand, int groupSize) {
        //My approach
        //Put everything in minHeap.
        //Till minheap not empty.Use priorityqueue.remove(index)
        //To remove particular element upto group size

        if (hand.length % groupSize != 0) {
            return false;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int card : hand) {
            minHeap.add(card);
        }

        while (!minHeap.isEmpty()) {
            //Get smallest and check consecutive from there
            int smallest = minHeap.poll();
            for (int i = 1; i < groupSize; i++) {
                //At anypoint consecutive not present in heap return false
                if (!minHeap.remove(smallest + i)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Optimal using TreeMap
    // TC: O(n log n) for sorting keys (TreeMap operations), SC: O(n) for frequency map
    public static boolean optimal(int[] hand, int groupSize) {
        //Optimal
        //Use TreeMap to elements sorted and have frequency
        //Frequency of smallest should be >= consecutive then only we can adjust group size
        int n = hand.length;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        //Get freq of all cards
        for (int card : hand) {
            int val = treeMap.getOrDefault(card, 0);
            treeMap.put(card, val + 1);
        }

        //Get first smallest from sorted treemap and check its consecutives
        //Heave freqcheck>0 .So not present elmts are not considered as start point
        for (int key : treeMap.keySet()) {
            int keyFreq = treeMap.get(key);
            //If that elmt exists in map
            if (keyFreq > 0) {
                for (int i = 0; i < groupSize; i++) {
                    int curr = key + i;
                    //using getordefault.as if key not present. map.get(key) returns null and give null pe
                    if (treeMap.getOrDefault(curr, 0) < keyFreq) {
                        return false; //Count of consecutive less than startPoint
                    }
                    //We remove all with freq upto parent
                    //treeMap.put(curr, treeMap.getOrDefault(curr,0)-keyFreq);
                    //Already above checked that curr present in map
                    treeMap.put(curr, treeMap.get(curr) - keyFreq);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;

        boolean bruteResult = brute(hand.clone(), groupSize);
        System.out.println("Brute Output: " + bruteResult);

        boolean optimalResult = optimal(hand.clone(), groupSize);
        System.out.println("Optimal Output: " + optimalResult);
    }
}

