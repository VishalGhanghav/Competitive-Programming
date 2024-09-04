package SdeSheetArrays.easy;

public class SecondLargestElement {

    // Function to find the second largest element in an array
    public int print2largest(int[] arr) {
        int n = arr.length; // Get the length of the array
        int max = arr[0]; // Initialize max with the first element of the array
        int secondMax = -1; // Initialize secondMax with -1 (or you can choose another default value)

        // Iterate through the array starting from the second element
        for (int i = 1; i < n; i++) {
            // If the current element is greater than max, update secondMax and max
            if (arr[i] > max) {
                secondMax = max;
                max = arr[i];
            }
            // If the current element is less than max but greater than secondMax, update secondMax
            else if (arr[i] > secondMax && arr[i] != max) {
                secondMax = arr[i];
            }
        }
        return secondMax; // Return the second largest element found
    }

    public static void main(String[] args) {
        // Test array
        int[] arr = {12, 35, 1, 10, 34, 1};

        // Create an object to call the method
        SecondLargestElement obj = new SecondLargestElement();

        // Call the print2largest function and store the result
        int secondMaxElement = obj.print2largest(arr);

        // Print the result
        System.out.println("The second largest element in the array is: " + secondMaxElement);
    }
}

