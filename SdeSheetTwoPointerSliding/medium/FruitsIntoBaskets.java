package SdeSheetTwoPointerSliding.medium;
import java.util.*;

public class FruitsIntoBaskets {

    // TC: O(N^2), SC: O(N)
    public static int totalFruitBrute(int[] fruits) {
        int noOfBaskets = 2; // basically k=2
        int n = fruits.length;
        int maxFruits = 0;

        //Brute:
        //Similar to longest substring with k distinct characters
        //My condition will be based map.size() and at most k(noOfBaskets)
        for(int i=0;i<n;i++) {
            Map<Integer,Integer> freqMap = new HashMap<>();
            for(int j=i;j<n;j++) {
                freqMap.put(fruits[j], freqMap.getOrDefault(fruits[j],0)+1);
                //COmmented since its not necessary to fill both baskets
                //eg:[3,3,3,3,3,3]
                // if(freqMap.size() == noOfBaskets) {
                //     maxFruits = Math.max(maxFruits, j-i+1);
                // }
                if(freqMap.size() > noOfBaskets) {
                    break;
                }
                //If freqMap.size<=k then in both cases we check maxFruits
                maxFruits = Math.max(maxFruits, j-i+1);
            }
        }
        return maxFruits;
    }

    // TC: O(N), SC: O(N)
    public static int totalFruitOptimal(int[] fruits) {
        int noOfBaskets = 2;//basically k=2
        int n = fruits.length;
        int maxFruits = 0;

        //Optimal
        //Sliding Window:Since repeating checks for same elements
        // and subarray or substring and max or min
        int i = 0, j = 0;
        Map<Integer,Integer> freqMap = new HashMap<>();
        while (j < n) {
            freqMap.put(fruits[j], freqMap.getOrDefault(fruits[j], 0) + 1);
            if (freqMap.size() <= noOfBaskets) {
                maxFruits = Math.max(maxFruits, j - i + 1);
                j++;
            } else {
                //Remove from start till freqMap.size()>k
                while (freqMap.size() > noOfBaskets) {
                    freqMap.put(fruits[i], freqMap.get(fruits[i]) - 1);
                    if (freqMap.get(fruits[i]) == 0) {
                        freqMap.remove(fruits[i]);
                    }
                    i++;
                }
                maxFruits = Math.max(maxFruits, j - i + 1);
                j++;
            }
        }
        return maxFruits;
    }

    public static void main(String[] args) {
        int[] fruits = {1, 2, 1, 2, 3};

        System.out.println("Brute: " + totalFruitBrute(fruits));     // Output: 4
        System.out.println("Optimal: " + totalFruitOptimal(fruits)); // Output: 4
    }
}
