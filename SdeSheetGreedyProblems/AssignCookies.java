package SdeSheetGreedyProblems;

import java.util.Arrays;

public class AssignCookies {

    /**
     * This method finds the maximum number of content children by assigning cookies to children.
     * Each child `g[i]` has a greed factor, and each cookie `s[j]` has a size.
     * A child is content if their greed factor is less than or equal to the size of the cookie.
     *
     * @param g array representing the greed factors of the children
     * @param s array representing the sizes of the cookies
     * @return the number of content children
     */
    public int findContentChildren(int[] g, int[] s) {
        int m = g.length;
        int n = s.length;

        // Sort greed factors and cookie sizes
        Arrays.sort(g);
        Arrays.sort(s);

        int l = 0, r = 0;  // l tracks children, r tracks cookies

        // Try to satisfy as many children as possible
        while (l < m && r < n) {
            if (g[l] <= s[r]) {
                // If the current cookie can satisfy the current child
                l++;  // move to the next child
            }
            // In either case, move to the next cookie
            r++;
        }

        // l represents the number of children who got a cookie
        return l;
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        AssignCookies cc = new AssignCookies();

        // Example 1
        int[] greedFactors1 = {1, 2, 3};  // greed factors of children
        int[] cookieSizes1 = {1, 1};  // sizes of cookies
        System.out.println(cc.findContentChildren(greedFactors1, cookieSizes1));  // Output: 1

        // Example 2
        int[] greedFactors2 = {1, 2};  // greed factors of children
        int[] cookieSizes2 = {1, 2, 3};  // sizes of cookies
        System.out.println(cc.findContentChildren(greedFactors2, cookieSizes2));  // Output: 2
    }
}
