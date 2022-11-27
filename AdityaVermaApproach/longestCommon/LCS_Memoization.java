package longestCommon;

import java.util.Arrays;

public class LCS_Memoization {
   static int LCS(String x, String y, int m, int n) {
      int[][] t = new int[m + 1][n + 1];
      int[][] var8 = t;
      int var7 = t.length;

      for(int var6 = 0; var6 < var7; ++var6) {
         int[] row = var8[var6];
         Arrays.fill(row, -1);
      }

      if (n != 0 && m != 0) {
         if (t[m][n] != -1) {
            return t[m][n];
         } else {
            return x.charAt(m - 1) == y.charAt(n - 1) ? (t[m][n] = LCS(x, y, m - 1, n - 1) + 1) : (t[m][n] = Math.max(LCS(x, y, m, n - 1), LCS(x, y, m - 1, n)));
         }
      } else {
         return 0;
      }
   }

   public static void main(String[] args) {
      String x = "abdc";
      String y = "abc";
      int n = x.length();
      int m = y.length();
      System.out.println(LCS(x, y, n, m));
   }
}
