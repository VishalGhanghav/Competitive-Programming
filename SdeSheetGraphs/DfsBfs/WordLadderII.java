package SdeSheetGraphs.DfsBfs;

import java.util.*;

// Class Name: WordLadderII
class WordLadderII {

    /**
     * Approach: BFS with path storage (Level-order word removal)
     * TC: O(N * M * 26 * M) - N is wordList size, M is word length. The extra M is due to String/List copying.
     * SC: O(N * M * N) - Storing multiple paths in the queue can lead to high memory usage.
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // Creating set for less time complexity
        Set<String> set = new HashSet<>();
        for (String s : wordList) {
            set.add(s);
        }

        // Creating a queue ds which stores the words in a sequence
        Queue<ArrayList<String>> q = new LinkedList<>();
        ArrayList<String> al = new ArrayList<>();
        al.add(beginWord);
        q.add(al);

        // Track words used in current level to remove them before moving to next level
        ArrayList<String> usedOnLevel = new ArrayList<>();
        usedOnLevel.add(beginWord);
        int level = 0;

        List<List<String>> ans = new ArrayList<>();

        while (!q.isEmpty()) {
            ArrayList<String> currList = q.poll();

            // Level management: erase all words used in the previous levels
            if (currList.size() > level) {
                level++;
                for (String it : usedOnLevel) {
                    set.remove(it);
                }
                usedOnLevel.clear(); // Reset for the current level
            }

            String word = currList.get(currList.size() - 1);

            // If target word is found
            if (word.equals(endWord)) {
                if (ans.size() == 0) {
                    ans.add(currList);
                } else if (ans.get(0).size() == currList.size()) {
                    ans.add(currList);
                }
                // Continue to find other paths of the same shortest length
                continue;
            }

            for (int i = 0; i < word.length(); i++) {
                char originalChar = word.charAt(i);
                char replacedCharArray[] = word.toCharArray();

                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == originalChar) continue;

                    replacedCharArray[i] = c;
                    String replacedWord = new String(replacedCharArray);

                    if (set.contains(replacedWord)) {
                        ArrayList<String> temp = new ArrayList<>(currList);
                        temp.add(replacedWord);
                        q.add(temp);
                        // Mark as visited on this level
                        usedOnLevel.add(replacedWord);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        WordLadderII solver = new WordLadderII();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));

        System.out.println("Input: " + beginWord + " -> " + endWord);

        List<List<String>> result = solver.findLadders(beginWord, endWord, wordList);

        System.out.println("Shortest Sequences:");
        for (List<String> seq : result) {
            System.out.println(seq);
        }
    }
}