package longestCommon;

public class LCS_Tabulation {
   static int LCS(String x, String y, int m, int n) {
      int[][] t = new int[m + 1][n + 1];

      int i;
      int j;
      for(i = 0; i <= m; ++i) {
         for(j = 0; j <= n; ++j) {
            if (i == 0 || j == 0) {
               t[i][j] = 0;
            }
         }
      }

      for(i = 1; i <= m; ++i) {
         for(j = 1; j <= n; ++j) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) {
               t[i][j] = t[i - 1][j - 1] + 1;
            } else {
               t[i][j] = Math.max(t[i][j - 1], t[i - 1][j]);
            }
         }
      }

      return t[m][n];
   }

   public static void main(String[] args) {
      String x = "abdc";
      String y = "abc";
      int n = x.length();
      int m = y.length();
      System.out.println(LCS(x, y, n, m));
   }
}
