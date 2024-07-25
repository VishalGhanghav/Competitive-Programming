package SdeSheetRecursion.hard;

import SdeSheetRecursion.medium.CombinationSum;

import java.util.Arrays;
import java.util.List;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {

        int n=s.length();
        //return recursion(0,s,wordDict,n);
        //Only index is changing .So we will just need 1d array.boolean as we are returning true/false
        Boolean[] memo=new Boolean[n];
        return memoization(0,s,wordDict,n,memo);
    }

    /*public boolean recursion(int ind,String s,List<String> wordDict,int n){

        //if we reach upto last index of string s
        if(ind==n){
            return true;
        }
        //if string is directly present in wordDict
        //not needed
        if(wordDict.contains(s)){
            return true;
        }

        //if not present check each char by length
        //eg.leetcodemik .ind=0 and endIdx=1 then for string leet.ind=0 and endIdx=4.substr=leet.
        //for code ind=endIdx ie.ind=4 and end goes from 4->8 for code
        for(int endIdx=ind+1;endIdx<=n;endIdx++){
            String temp=s.substring(ind,endIdx);
            if(wordDict.contains(temp)==true && solve(endIdx,s,wordDict,n)==true){
                return true;
            }
        }
        //if in whole loop you dont all words from string dict
        return false;
    }*/

    public boolean memoization(int ind, String s, List<String> wordDict, int n, Boolean[] memo){

        //if we reach upto last index of string s
        if(ind==n){
            return true;
        }
        if(memo[ind]!=null){
            return memo[ind];
        }
        //if string is directly present in wordDict
        //not needed
        if(wordDict.contains(s)){
            return true;
        }

        //if not present check each char by length
        //eg.leetcodemik .ind=0 and endIdx=1 then for string leet.ind=0 and endIdx=4.substr=leet.
        //for code ind=endIdx ie.ind=4 and end goes from 4->8 for code
        for(int endIdx=ind+1;endIdx<=n;endIdx++){
            String temp=s.substring(ind,endIdx);
            if(wordDict.contains(temp)==true && memoization(endIdx,s,wordDict,n,memo)==true){
                return memo[ind]=true;
            }
        }
        //if in whole loop you dont all words from string dict
        return memo[ind]=false;
    }

    public static void main(String args[]) {
        String s="leetcodemik";
        List<String> wordDict1 = Arrays.asList("leet", "code","mik");
        System.out.println(new WordBreak().wordBreak(s,wordDict1));//should return true
        List<String> wordDict2 = Arrays.asList("leet", "code","og");
        System.out.println(new WordBreak().wordBreak(s,wordDict2));//should return false


    }
}
