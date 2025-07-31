package SdeSheetHeaps.HeapsHardProblems;

import java.util.*;

public class MaximumSumCombination {
    // Custom Tuple class to store sum and indices
    class Tuple {
        int sum;
        int i;
        int j;

        public Tuple(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }
    }

    // Brute Force Approach
    // TC: O(M*N + Klog(M*N)), SC: O(M*N)
    public ArrayList<Integer> topKSumPairsBrute(int[] a, int[] b, int k) {
        //Brute
        //Create a maxHeap of sums of all pair elements in both arrays.Gives OOM
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                maxHeap.add(a[i] + b[j]);
            }
        }
        ArrayList<Integer> resList = new ArrayList<>();
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            resList.add(maxHeap.poll());
        }
        return resList;
    }

    // Optimal Approach
    // TC: O(KlogK), SC: O(K)
    public ArrayList<Integer> topKSumPairsOptimal(int[] a, int[] b, int k) {
        //Optimal
        //Sort both arrays and sum of last pair .It gives maximum and store that in maxHeap
        //Also maintain visited set so that we don't visit same pair twice or more
        ArrayList<Integer> resList = new ArrayList<>();
        int m = a.length;
        int n = b.length;

        Arrays.sort(a);
        Arrays.sort(b);

        Set<String> visited = new HashSet<>();
        PriorityQueue<Tuple> maxHeap = new PriorityQueue<>((p, q) -> q.sum - p.sum);

        maxHeap.add(new Tuple(a[m - 1] + b[n - 1], m - 1, n - 1));
        visited.add((m - 1) + "," + (n - 1));

        while (k-- > 0 && !maxHeap.isEmpty()) {
            Tuple tuple = maxHeap.poll();
            resList.add(tuple.sum);

            int i = tuple.i;
            int j = tuple.j;

            // i-1, j
            if (i - 1 >= 0) {
                String key1 = (i - 1) + "," + j;//Using string to make search easy
                if (!visited.contains(key1)) {
                    maxHeap.add(new Tuple(a[i - 1] + b[j], i - 1, j));
                    visited.add(key1);
                }
            }

            // i, j-1
            if (j - 1 >= 0) {
                String key2 = i + "," + (j - 1);
                if (!visited.contains(key2)) {
                    maxHeap.add(new Tuple(a[i] + b[j - 1], i, j - 1));
                    visited.add(key2);
                }
            }
        }

        return resList;
    }

    // Main method to run both approaches independently
    public static void main(String[] args) {
        MaximumSumCombination obj = new MaximumSumCombination();
        int[] a = {1, 4, 2, 3};
        int[] b = {2, 5, 1, 6};
        int k = 3;

        System.out.println("Brute Output:");
        System.out.println(obj.topKSumPairsBrute(a, b, k));

        System.out.println("Optimal Output:");
        System.out.println(obj.topKSumPairsOptimal(a, b, k));
    }
}
