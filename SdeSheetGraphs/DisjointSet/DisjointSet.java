package SdeSheetGraphs.DisjointSet;

import java.util.Arrays;

class DisjointSet {
    private int[] size;
    private int[] parent;

    public DisjointSet(int n) {
        size = new int[n + 1];
        parent = new int[n + 1];

        Arrays.fill(size, 1); // to take care of 1-based indexing as well

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    public int findParent(int u) {
        if (u == parent[u])
            return u;
        return parent[u] = findParent(parent[u]);
    }

    public void unionBySize(int u, int v) {
        int up_u = findParent(u);
        int up_v = findParent(v);

        if (up_u == up_v)
            return;

        if (size[up_u] > size[up_v]) {
            parent[up_v] = up_u;
            size[up_u] += size[up_v];
        } else {
            parent[up_u] = up_v;
            size[up_v] += size[up_u];
        }
    }
}

