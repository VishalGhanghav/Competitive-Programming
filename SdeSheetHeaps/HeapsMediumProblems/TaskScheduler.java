package SdeSheetHeaps.HeapsMediumProblems;

import java.util.*;

public class TaskScheduler {
    // Optimal using Max Heap
    // TC: O(n * 26) ~ O(n), where n = number of tasks
    // SC: O(1) for fixed heap size (26 characters max)
    public static int optimal(char[] tasks, int n) {
        // freqMap approach
        // Get frequency of all elements. Based on frequency pick the tasks
        // from A to Z and within n+1. Execute those tasks by reducing freq count
        // finally calculate time based on count or (n+1) interval

        // Brute doesn't work for:
        // tasks = ["A","A","A","B","B","B", "C","C","C", "D", "D", "E"], n = 2
        /*
        Map<Character,Integer> freqMap = new HashMap<>();
        for(char task : tasks) {
            int val = freqMap.getOrDefault(task,0);
            freqMap.put(task,val+1);
        }

        int time = 0;
        while(!freqMap.isEmpty()) {
            int realTaskCounter = 0;
            for(char ch = 'A'; ch <= 'Z' && realTaskCounter <= n; ch++) {
                if(freqMap.containsKey(ch)) {
                    freqMap.put(ch, freqMap.get(ch) - 1);
                    if(freqMap.get(ch) == 0) freqMap.remove(ch);
                    realTaskCounter++;
                }
            }
            time += freqMap.isEmpty() ? realTaskCounter : (n+1);
        }
        return time;
        */

        // Optimal: Max Heap approach
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : tasks)
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(freqMap.values());

        int time = 0;

        while (!maxHeap.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int interval = 0;

            for (int i = 0; i <= n; i++) {
                if (!maxHeap.isEmpty()) {
                    temp.add(maxHeap.poll());
                    interval++;
                }
            }

            for (int count : temp) {
                if (count - 1 > 0)
                    maxHeap.add(count - 1);
            }

            time += maxHeap.isEmpty() ? interval : (n + 1);
        }

        return time;
    }

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'};
        int n = 2;

        int result = optimal(tasks, n);
        System.out.println("Optimal Output: " + result);
    }
}
