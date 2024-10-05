package SdeSheetArrays.hard;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    public static void main(String[] args) {
        PascalTriangle pt = new PascalTriangle();
        int numRows = 5; // You can change this to any number of rows

        // Running brute-force method
        System.out.println("Brute-force method result:");
        List<List<Integer>> bruteResult = pt.generateBrute(numRows);
        System.out.println(bruteResult);

        // Running optimal method
        System.out.println("\nOptimal method result:");
        List<List<Integer>> optimalResult = pt.generateOptimal(numRows);
        System.out.println(optimalResult);
    }

    // Brute-force method: Computes each element using combination formula nCr
    public List<List<Integer>> generateBrute(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        // Brute: Calculate each element using combination (nCr)
        for (int row = 1; row <= numRows; row++) {
            List<Integer> temp = new ArrayList<>();
            for (int col = 1; col <= row; col++) {
                temp.add(findncr(row - 1, col - 1)); // Using combination formula to compute elements
            }
            res.add(temp);
        }
        return res;
    }

    // Optimal method: Generates rows directly by calculating values using the pattern
    public List<List<Integer>> generateOptimal(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        // Optimal: Generate all rows
        for (int i = 1; i <= numRows; i++) {
            res.add(generateRow(i));
        }
        return res;
    }

    // Helper method for optimal approach: Generates a specific row using the formula
    private List<Integer> generateRow(int row) {
        int res = 1; // The first element is always 1
        List<Integer> rowList = new ArrayList<>();
        rowList.add(res); // Add the first element

        for (int col = 1; col < row; col++) {
            // Calculate next element using the formula res = res * (row - col) / col
            res = res * (row - col) / col;
            rowList.add(res);
        }

        return rowList;
    }

    // Helper method for brute-force approach: Calculates combination nCr
    private int findncr(int row, int col) {
        int res = 1; // Initialize result

        // nCr = n! / (r! * (n-r)!)
        // We compute nCr using an iterative approach to avoid calculating large factorials
        for (int i = 0; i < col; i++) {
            res = res * (row - i);
            res = res / (i + 1);
        }

        return res; // Return the result of nCr
    }
}

