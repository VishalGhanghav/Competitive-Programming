package SdeSheetDp.src.DpOnStrings;

import java.util.Arrays;

public class MinimumInsertionOrDeletionToConvertStringAtoB {
    static int lcs(String s1, String s2) {
        int m= s1.length();
        int n = s2.length();

        // Create a 2D array to store the LCS lengths
        int dp[][] = new int[n + 1][n + 1];

        // Fill the dp array using a bottom-up approach
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }

    // Function to find the minimum operations required to convert str1 to str2
    static int canYouMake(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        // Find the length of the LCS between str1 and str2
        int k = lcs(str1, str2);

        // The minimum operations required is the sum of the lengths of str1 and str2 minus twice the length of LCS
        //noOfInsertion=smallString-lcs noOfDeletion=bigString-lcs
        //anyways m+n-(2*lcs)
        return (m - k) + (n - k);
    }

    public static void main(String args[]) {
        String str1 = "abcd";
        String str2 = "anc";
        //lcs=ac .delete bd add n.And str2 is created
        System.out.println("The Minimum operations required to convert str1 to str2: "
                + canYouMake(str1, str2));
    }
}
