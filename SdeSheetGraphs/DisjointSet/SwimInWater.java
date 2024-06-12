package SdeSheetGraphs.DisjointSet;

import java.util.HashMap;

public class SwimInWater {

    public static int swimInWater(int[][] grid) {
        HashMap<Integer, int[]> map = new HashMap<>();
        int n = grid.length;

        if (n == 1)
            return 0;

        DisjointSet ds = new DisjointSet(n * n);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                map.put(grid[i][j], new int[]{i, j});
        System.out.println(map);
        int time = 0;

        while (ds.findParent(0) != ds.findParent(n * n - 1)) {
            int[] node = map.get(time);
            int x = node[0];
            int y = node[1];
            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, -1, 0, 1};

            for (int i = 0; i < 4; i++) {
                int nrow = x + dx[i];
                int ncol = y + dy[i];

                if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < n && grid[nrow][ncol] <= time)
                    ds.unionBySize(nrow * n + ncol, x * n + y);
            }

            time++;
        }

        return time - 1;
    }
    public static void main(String[] args) {

        int[][] grid2 = {
                {0, 1, 2, 3, 4},
                {24, 23, 22, 21, 5},
                {12, 13, 14, 15, 16},
                {11, 17, 18, 19, 20},
                {10, 9, 8, 7, 6}
        };
        System.out.println(swimInWater(grid2)); // Output: 16
    }
}
