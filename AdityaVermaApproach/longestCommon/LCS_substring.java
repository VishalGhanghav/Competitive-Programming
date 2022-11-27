package longestCommon;

public class LCS_substring {
   public static void main(String[] args) {
      String x = "ADC";
      String y = "ADADAC";
      int m = x.length();
      int n = y.length();
      int ans = LCS(x, y, m, n);
      System.out.println(ans);
   }

   private static int LCS(String x, String y, int n, int m) {
      int[][] t = new int[n + 1][m + 1];

      int mx;
      int i;
      for(mx = 0; mx <= n; ++mx) {
         for(i = 0; i <= m; ++i) {
            if (mx == 0 || i == 0) {
               t[mx][i] = 0;
            }

            System.out.print(t[mx][i] + " ");
         }

         System.out.println();
      }

      for(mx = 1; mx <= n; ++mx) {
         for(i = 1; i <= m; ++i) {
            if (x.charAt(mx - 1) == y.charAt(i - 1)) {
               t[mx][i] = t[mx - 1][i - 1] + 1;
            } else {
               t[mx][i] = 0;
            }

            System.out.print(t[mx][i] + " ");
         }

         System.out.println();
      }

      mx = Integer.MIN_VALUE;

      for(i = 0; i <= n; ++i) {
         for(int j = 0; j <= m; ++j) {
            mx = Math.max(mx, t[i][j]);
         }
      }

      return mx;
   }
}
