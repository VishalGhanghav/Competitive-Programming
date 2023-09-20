package abluvaCardsProblem;

	import java.util.*;
	
	/*
	 * Problem Statement: Two sets of cards from 2 separate decks of 52 playing cards are selected
	 *  (note that both sets can have different numbers of cards). Both these sets of cards are
	 *   laid down sequentially creating a unique pattern like below.
	Set1: {Jack of Spade}{10 of clubs}{8 of hearts}{Queen of Spade}
	Set2: {Queen of Diamond}{3 of clubs}{8 of hearts}{2 of hearts} {5 of hearts} {9 of club}
	
	Find out the minimum number of transformations (adding, removing, replacing a card) are needed to transform set1 into set2. 
	You may use remaining cards for addition/replacement
	Note that there are various ways in which one pattern can be transformed to another, but not all are optimal.
		 
	 Solution:
	 1.Looks like a variation of Standard Longest Common Subsequence problem.
	 2.Had idea about converting str1 to str2 with min inserts or delete.
	 3.Used same concept with slight change in Logic.
	 Set1: {Jack of Spade}{10 of clubs}{8 of hearts}{Queen of Spade}
	 Set2: {Queen of Diamond}{3 of clubs}{8 of hearts}{2 of hearts} {5 of hearts} {9 of club}
	 Steps:
	 1.Find lcsLength=1 ie.{8 of hearts} 
	 2.We have to find min no of transformations.It can be acheived by replacing non lcs cards if 
	 lengths are similar.
	 3.And inserting or deleting extra card in either set1 or set2
	 */
	/*
	 Time Complexity:O(2^n)
	  Space Complexity:O(mxn)+O(n)
	 */
	public class CardsProblemRecursion {

	    public static int lcs(List<String> l1, List<String> l2, int m, int n) {
	        if(m==0 || n==0) {
	        	return 0;
	        }
	        if (l1.get(m - 1).equals(l2.get(n- 1))) {
	            return lcs(l1, l2, m - 1, n - 1)+1;
	        }else {
	        	return Math.max(lcs(l1, l2, m, n-1),
	        			lcs(l1, l2, m-1, n));
	        }
	        
	        
	    }

	    public static void main(String[] args) {
	        List<String> l1 = Arrays.asList("Jack of Spade", "10 of clubs", "8 of hearts", "Queen of Spade");
	        List<String> l2 = Arrays.asList("Queen of Diamond", "3 of clubs", "8 of hearts", "2 of hearts", "5 of hearts", "9 of club");
	        int m=l1.size();
	        int n=l2.size();
	        //Find length of lcs
	        int lcsLength = lcs(l1, l2, m, n);
	        int minTransformations=0;
	        //If length of l1==l2 ,we can replace all elements to achieve minTransformation
	        //eg : "abc"=="pqr" ,3 replacements for min transformation(n-lcs+(m-n)=3-0+0)
	        
	        //If l1>l2 "pabq">"mab" ,(n-lcs+(m-n)=3-2+1=2) minTransformations=2.Replace p delete q
	        if(m>=n) {
	        	minTransformations=n-lcsLength+Math.abs(m-n);
	        }else {
	        	//If l1<l2 "mab">"pabq" ,(m-lcs+(m-n)=3-2+1=3) minTransformations=2.Replace p delete q
	        	minTransformations=m-lcsLength+Math.abs(m-n);
	        }
	        System.out.println("---------SET1:Given Input set-----------------");
	        System.out.println("Minimum number of transformations: " + minTransformations);
	        
	        List<String> set1 = Arrays.asList("Queen of Diamond", "10 of clubs", "8 of hearts", "Queen of Spade");
	        List<String> set2 = Arrays.asList("Queen of Diamond", "3 of clubs", "8 of hearts", "2 of hearts");
	        int m1=set1.size();
	        int n1=set2.size();
	        
	        int lcsLength2 = lcs(set1, set2, m1, n1);
	        int minTransformations2=0;
	        if(m>=n) {
	        	minTransformations2=n1-lcsLength2+Math.abs(m1-n1);
	        }else {
	        	minTransformations2=m1-lcsLength2+Math.abs(m1-n1);
	        }
	        System.out.println("---------SET2:My Input set-----------------");
	        System.out.println("Minimum number of transformations: " + minTransformations2);
	        
	        
	    }
	
}
