package SdeSheeetStrings.medium;



import java.util.*;

public class SortCharactersByFrequency {

    // Custom Pair class to store characters and their frequencies
    class Pair {
        char ch; // The character
        int freq; // Frequency of the character

        // Constructor for Pair
        Pair(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    public String frequencySort(String s) {
        // Plan: Create a frequency map, sort using a maxHeap, and build the result

        // Step 1: Create a map to store the frequency of each character
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int value = frequencyMap.getOrDefault(ch, 0);
            frequencyMap.put(ch, value + 1); // Increment frequency
        }

        // Step 2: Use a max-heap (PriorityQueue) to sort characters by frequency
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(
                (a, b) -> b.freq - a.freq // Comparator for descending order of frequency
        );
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            maxHeap.add(new Pair(entry.getKey(), entry.getValue()));
        }

        // Step 3: Pop from maxHeap and build the result string
        StringBuilder res = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Pair p = maxHeap.poll(); // Get the pair with the highest frequency
            int freq = p.freq;
            while (freq != 0) { // Append the character 'freq' times
                res.append(p.ch);
                freq--;
            }
        }

        return res.toString(); // Return the final sorted string
    }

    // Main method to test the function
    public static void main(String[] args) {
        SortCharactersByFrequency fs = new SortCharactersByFrequency();
        String input = "tree";
        String result = fs.frequencySort(input);
        System.out.println("Input: " + input);
        System.out.println("Output (sorted by frequency): " + result);
    }
}
