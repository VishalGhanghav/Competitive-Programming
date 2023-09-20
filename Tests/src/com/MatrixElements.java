package com;

import java.util.ArrayList;

public class MatrixElements {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2,  3,   4, 5, 6},
                {7, 8,  9,   10, 11, 12},
                {13, 14, 15, 16, 17, 18}
        };

        printRequiredElements(matrix);
    }

    public static void printRequiredElements(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int i=0,j=0;
        ArrayList<Integer> al=new ArrayList<Integer>();
        int start=0,end=0;
        while(i< rows && j<cols) {
        	if(i==j && start<rows) {
        		al.add(matrix[i][j]);
        	}
        }
        
    }
}


