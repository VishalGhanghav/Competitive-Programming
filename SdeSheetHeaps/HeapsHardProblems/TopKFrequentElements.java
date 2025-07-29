package SdeSheetHeaps.HeapsHardProblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    static class Pair {
        int num;
        int freq;
        Pair(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }
    }

    // TC: O(N log K) where N = number of unique elements
    // SC: O(N) for freqMap and heap
    public int[] topKFrequent(int[] nums, int k) {
        //Approach
        //Get freq with hashmap.
        //Get top k with minHeap(use Pair for sort) and then get as per result

        Map<Integer, Integer> freqMap = new HashMap<>();
        //Get Frequencies of all numbers
        for (int num : nums) {
            int val = freqMap.getOrDefault(num, 0);
            freqMap.put(num, val + 1);
        }

        // MinHeap based on frequency
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(
                (a, b) -> a.freq - b.freq
        );

        //Sort numbers by their frequencies and keep top k in minHeap
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int freq = entry.getValue();
            int num = entry.getKey();
            minHeap.add(new Pair(num, freq));
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] res = new int[k];
        int i = 0;
        //Get top k frequent in res
        while (!minHeap.isEmpty()) {
            res[i++] = minHeap.poll().num;
        }

        return res;
    }

    public static void main(String[] args) {
        TopKFrequentElements obj = new TopKFrequentElements();

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] topK = obj.topKFrequent(nums, k);
        System.out.println("Top " + k + " frequent elements: " + Arrays.toString(topK));

        // Additional test
        nums = new int[]{4, 4, 1, 2, 2, 3, 3, 3};
        k = 3;
        topK = obj.topKFrequent(nums, k);
        System.out.println("Top " + k + " frequent elements: " + Arrays.toString(topK));
    }
}
