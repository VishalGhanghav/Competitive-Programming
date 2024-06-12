package SdeSheetGraphs;

import java.util.*;
        import java.lang.*;
        import java.io.*;

// A comparator function to sort the answer.
class comp implements Comparator < ArrayList < String >> {

    public int compare(ArrayList < String > a, ArrayList < String > b) {
        String x = "";
        String y = "";
        for (int i = 0; i < a.size(); i++)
            x += a.get(i);
        for (int i = 0; i < b.size(); i++)
            y += b.get(i);
        return x.compareTo(y);
    }
}

public class WordLadder2 {

    public static void main(String[] args) throws IOException {
        String startWord = "der", targetWord = "dfs";
        String[] wordList = {
                "des",
                "der",
                "dfr",
                "dgt",
                "dfs"
        };

        Solution obj = new Solution();
        ArrayList < ArrayList < String >> ans = obj.findSequences(startWord, targetWord, wordList);

        // If no transformation sequence is possible.
        if (ans.size() == 0)
            System.out.println(-1);
        else {

            Collections.sort(ans, new comp());
            for (int i = 0; i < ans.size(); i++) {
                for (int j = 0; j < ans.get(i).size(); j++) {
                    System.out.print(ans.get(i).get(j) + " ");
                }
                System.out.println();
            }
        }
    }
}

class Solution {
    public ArrayList < ArrayList < String >> findSequences(String beginWord, String endWord,
                                                           String[] wordList) {

        // Code here
        //creating set for less time complexity
        Set<String> set=new HashSet<>();
        for(String s:wordList){
            set.add(s);
        }
        // Creating a queue ds which stores the words in a sequence which is
        // required to reach the targetWord after successive transformations.
        Queue<ArrayList<String>> q=new LinkedList<>();
        ArrayList<String> al=new ArrayList<>();
        al.add(beginWord);
        q.add(al);
        ArrayList<String> usedOnLevel=new ArrayList<>();
        usedOnLevel.add(beginWord);
        int level=0;
        ArrayList<ArrayList<String>> ans=new ArrayList<>();
        while(!q.isEmpty()){
            ArrayList<String> currList=q.poll();
            // Now, erase all words that have been
            // used in the previous levels to transform
            if(currList.size()>level){
                level++;
                for (String it: usedOnLevel) {
                    set.remove(it);
                }
            }

            String word = currList.get(currList.size() - 1);

            // store the answers if the end word matches with targetWord.
            if (word.equals(endWord)) {
                // the first sequence where we reached the end.
                if (ans.size() == 0) {
                    ans.add(currList);
                } else if (ans.get(0).size() == currList.size()) {
                    ans.add(currList);
                }
            }

            for (int i = 0; i < word.length(); i++) {

                // Now, replace each character of ‘word’ with char
                // from a-z then check if ‘word’ exists in wordList.
                for (char c = 'a'; c <= 'z'; c++) {
                    char replacedCharArray[] = word.toCharArray();
                    replacedCharArray[i] = c;
                    String replacedWord = new String(replacedCharArray);
                    if (set.contains(replacedWord) == true) {

                        // Java works by reference, so enter the copy of vec
                        // otherwise if you remove word from vec in next lines, it will
                        // remove from everywhere .So I added in temporary list itself
                        ArrayList < String > temp = new ArrayList < > (currList);
                        temp.add(replacedWord);
                        q.add(temp);
                        // mark as visited on the level
                        usedOnLevel.add(replacedWord);
                    }
                }
            }
        }

        return ans;
    }
}
