package SdeSheetBinarySearch.BinarySearchOn2dArray;
/*

 */
public class Maximum1sInMatrix {

        // Brute Force Approach
        public static int rowWithMaxOnesBrute(int[][] mat) {
            int m = mat.length;
            int n = mat[0].length;
            int maxCnt = 0;
            int res = -1;

            for (int i = 0; i < m; i++) {
                int cnt = 0;
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == 1) cnt++;
                }
                if (cnt > maxCnt) {
                    maxCnt = cnt;
                    res = i;
                }
            }
            return res;
        }

        // Better Approach using Binary Search
        public static int rowWithMaxOnesBetter(int[][] mat) {
            int m = mat.length;
            int n = mat[0].length;
            int maxCnt = 0;
            int res = -1;

            for (int i = 0; i < m; i++) {
                int firstOneInd = getFirstOne(mat[i], n);
                int cnt = n - firstOneInd;
                if (cnt > maxCnt) {
                    maxCnt = cnt;
                    res = i;
                }
            }
            return res;
        }

        // Binary search to find index of first 1 in a sorted row
        private static int getFirstOne(int[] row, int n) {
            int start = 0, end = n - 1, ans = n;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (row[mid] == 1) {
                    ans = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            return ans;
        }

        // Optimal Approach (O(m+n)) starting from top-right corner
        public static int rowWithMaxOnesOptimal(int[][] mat) {
            int m = mat.length;
            int n = mat[0].length;
            int i = 0, j = n - 1;
            int res = -1;

            while (i < m && j >= 0) {
                if (mat[i][j] == 1) {
                    res = i;
                    j--;
                } else {
                    i++;
                }
            }
            return res;
        }

        // Main method for testing all approaches
        public static void main(String[] args) {
            int[][] mat = {
                    {0, 0, 0, 1},
                    {0, 1, 1, 1},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0}
            };

            System.out.println("Brute Force: Row with max 1s = " + rowWithMaxOnesBrute(mat));
            System.out.println("Better (Binary Search): Row with max 1s = " + rowWithMaxOnesBetter(mat));
            System.out.println("Optimal (O(m+n)): Row with max 1s = " + rowWithMaxOnesOptimal(mat));
        }

}
