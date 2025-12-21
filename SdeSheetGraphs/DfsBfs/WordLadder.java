package SdeSheetGraphs.DfsBfs;

import java.util.*;

// Class Name: WordLadder
class WordLadder {

    // Helper class kept as inner class per instructions
    class Pair {
        String word;
        int level;

        Pair(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }

    /**
     * Approach: Breadth First Search (BFS)
     * TC: O(N * M * 26) - Where N is wordList size and M is word length.
     * For each word, we try 26 characters for each of its M positions.
     * SC: O(N * M) - To store words in the Set and the Queue.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<Pair> q = new LinkedList<>();
        // Tried without arraylist and getting timeout.
        // Reason remove in arraylist takes O(N)
        // So using set as it takes O(1)
        Set<String> set = new HashSet<>();
        for (String s : wordList) {
            set.add(s);
        }

        q.add(new Pair(beginWord, 1));
        // Remove the beginWord from the set if it exists to avoid cycles
        set.remove(beginWord);

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            String word = pair.word;
            int steps = pair.level;

            if (word.equals(endWord)) {
                return steps;
            }

            // For every character, I will try all possibilities from a to z
            for (int i = 0; i < word.length(); i++) {
                // eg .word=hat .i=0 is h
                char[] replacedCharArray = word.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    // here i will change it to aat bat cat...zat
                    replacedCharArray[i] = ch;
                    String replacedWord = String.valueOf(replacedCharArray);

                    if (set.contains(replacedWord)) {
                        set.remove(replacedWord);
                        q.add(new Pair(replacedWord, steps + 1));
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        WordLadder solution = new WordLadder();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));

        System.out.println("Begin: " + beginWord + ", End: " + endWord);
        System.out.println("Word List: " + wordList);

        int result = solution.ladderLength(beginWord, endWord, wordList);
        System.out.println("Shortest Transformation Length: " + result);
    }
}
