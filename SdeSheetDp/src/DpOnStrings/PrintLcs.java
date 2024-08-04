package SdeSheetDp.src.DpOnStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PrintLcs {
        // Method to find all longest common subsequences
        public String printLcs(String s1, String s2) {
            int m=s1.length();
            int n=s2.length();
            int[][] dp=new int[m+1][n+1];
            //base case would be if string length 0.lcs length is 0
            //but we dont need as anyways 0 is stored in dp array

            for(int i=1;i<=m;i++){
                for(int j=1;j<=n;j++){
                    if(s1.charAt(i-1)==s2.charAt(j-1)){
                        dp[i][j]=dp[i-1][j-1]+1;
                    }else{
                        dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j]);
                    }
                }
            }
            //we have got the filled dp array
            //now we traverse through the array backward and get our lcs
            int i=m;
            int j=n;
            //using string builder to reverse string using function later and better time complexity
            StringBuilder res=new StringBuilder();
            while(i>0 && j>0){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    res.append(s1.charAt(i-1));
                    i--;
                    j--;
                }else{
                    //2  3
                    //1  1  as 3>1 so go up
                    if(dp[i-1][j]>dp[i][j-1]){
                        i--;
                    }else{
                        j--;
                    }
                }
            }
            //after traversing this we will get the reverse string.so just reverse to get proper
            return res.reverse().toString();
        }

        // Main method to test the solution
        public static void main(String[] args) {
            PrintLcs sol = new PrintLcs();

            String s = "abdaf";
            String t = "abcacf";

            System.out.println(" Longest Common Subsequences:"+sol.printLcs(s, t) );

        }
}

