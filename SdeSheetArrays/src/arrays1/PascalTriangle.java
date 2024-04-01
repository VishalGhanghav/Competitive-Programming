package arrays1;
/*
 Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:


 

Example 1:

Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
Example 2:

Input: numRows = 1
Output: [[1]]
 

Constraints:

1 <= numRows <= 30
 */

import java.util.*;

public class PascalTriangle {
    public static long nCr(int n, int r) {
        long res = 1;

        // calculating nCr:
        for (int i = 0; i < r; i++) {
            res = res * (n - i);
            res = res / (i + 1);
        }
        return res;
    }
/*
 Print nth row
 
 ArrayList<Long> nthRowOfPascalTriangle(int n) {
        // code here
        ArrayList<Long> al=new ArrayList<>();
        
        long ans=1;
        al.add(ans);
        
        for(int i=1;i<n;i++){
            ans=ans*(n-i);
            ans=ans/i;
            al.add(ans);
            
        }
        return al;
    }
    
    
    Print n rows
 */
    public static int pascalTriangle(int r, int c) {
        int element = (int) nCr(r - 1, c - 1);
        return element;
    }

    public static void main(String[] args) {
        int r = 5; // row number
        int c = 3; // col number
        int element = pascalTriangle(r, c);
        System.out.println("The element at position (r,c) is: " + element);
    }
}  
        

