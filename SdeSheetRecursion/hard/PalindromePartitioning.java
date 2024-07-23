package SdeSheetRecursion.hard;

import java.util.ArrayList;
import java.util.List;

/*
Given a string s, partition s such that every
substring
 of the partition is a
palindrome
. Return all possible palindrome partitioning of s.



Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]


Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res=new ArrayList<>();
        List<String> path=new ArrayList<>();
        getPartition(0,s,path,res);
        return res;
    }

    public void getPartition(int ind,String s,List<String> path,List<List<String>> res){
        //when complete string(or all partitions) are checked we get res
        if(ind==s.length()){
            res.add(new ArrayList<>(path));
            return;
        }
        //I am checking every index for a partition
        for(int i=ind;i<s.length();i++){
            if(isPalindrome(s,ind,i)){
                //If palindrome add in partition list the substring
                path.add(s.substring(ind,i+1));
                getPartition(i+1,s,path,res);
                path.remove(path.size()-1);
            }
        }
    }
    public boolean isPalindrome(String s,int start,int end){
        while(start<=end){
            if(s.charAt(start)!=s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    public static void main(String args[]) {
        List<List<String>> ls = new PalindromePartitioning().partition("aabb");
        System.out.println("Combinations are: ");
        System.out.println(ls);
    }
}
