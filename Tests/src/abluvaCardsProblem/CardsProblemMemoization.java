
package abluvaCardsProblem;

import java.util.Arrays;
import java.util.List;
/*
 Time Complexity:O(mxn)
  Space Complexity:O(mxn)+O(n)
 */
public class CardsProblemMemoization {
	
	
    public static void main(String[] args) {
        List<String> l1 = Arrays.asList("Jack of Spade", "10 of clubs", "8 of hearts", "Queen of Spade");
        List<String> l2 = Arrays.asList("Queen of Diamond", "3 of clubs", "8 of hearts", "2 of hearts", "5 of hearts", "9 of club");
        int m=l1.size();
        int n=l2.size();
        //Create dp array with size m+1,n+1 and fill -1
        int dp[][]=new int[m+1][n+1];
        for(int[] p:dp) {
        	Arrays.fill(p, -1);
        }
        //Find length of lcs
        int lcsLength = lcsMemoization(l1, l2, m, n,dp);
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
      //Create dp array with size m+1,n+1 and fill -1
        int dp2[][]=new int[m+1][n+1];
        for(int[] p:dp2) {
        	Arrays.fill(p, -1);
        }
        int lcsLength2 = lcsMemoization(set1, set2, m1, n1,dp2);
        int minTransformations2=0;
        if(m>=n) {
        	minTransformations2=n1-lcsLength2+Math.abs(m1-n1);
        }else {
        	minTransformations2=m1-lcsLength2+Math.abs(m1-n1);
        }
        System.out.println("---------SET2:My Input set-----------------");
        System.out.println("Minimum number of transformations: " + minTransformations2);
        
        
    }

	private static int lcsMemoization(List<String> l1, List<String> l2, int m, int n, int[][] dp) {
		if(m==0 || n==0) {
        	return 0;
        }
		if(dp[m][n]!=-1) {
			return dp[m][n];
		}
		
        if (l1.get(m - 1).equals(l2.get(n- 1))) {
            return dp[m][n]=lcsMemoization(l1, l2, m - 1, n - 1,dp)+1;
        }else {
        	return dp[m][n]=Math.max(lcsMemoization(l1, l2, m, n-1,dp),
        			lcsMemoization(l1, l2, m-1, n,dp));
        }
	}

}
