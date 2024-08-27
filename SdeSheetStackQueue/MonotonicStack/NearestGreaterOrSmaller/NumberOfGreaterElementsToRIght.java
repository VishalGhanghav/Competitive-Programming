package SdeSheetStackQueue.MonotonicStack.NearestGreaterOrSmaller;

import java.util.List;

public class NumberOfGreaterElementsToRIght {
    public static int[] countNGEs( int arr[], int queries, int indices[]) {
        // code here
        int[] NGEs = new int[queries]; // List to store the results

        // Process each query
        for (int i = 0; i < queries; i++) {
            int index = indices[i];
            int count = 0;

            // Count the number of elements greater than arr[index] to the right of index
            for (int j = index + 1; j < arr.length; j++) {
                if (arr[j] > arr[index]) {
                    count++;
                }
            }

            NGEs[i]=count; // Store the count in the result list
        }

        return NGEs;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 7, 5, 8, 10, 6}; // Example array
        int[] indices = {0, 5}; // Example indices for queries
        int queries = indices.length; // Number of queries

        // Call the function and get the result
        int[] result = countNGEs(arr, queries, indices);

        // Print the result
        System.out.print("Count of Next Greater Elements to the Right: ");
        for (int r : result) {
            System.out.print(r + " ");
        }
    }
}
