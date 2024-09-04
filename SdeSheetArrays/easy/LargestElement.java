package SdeSheetArrays.easy;

public class LargestElement {

    // Function to find the largest element in an array
    public static int largest(int[] arr) {
        int n = arr.length; // Get the length of the array
        int max = arr[0]; // Initialize max with the first element of the array

        // Iterate through the array starting from the second element
        for (int i = 1; i < n; i++) {
            // If the current element is greater than max, update max
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max; // Return the largest element found
    }

    public static void main(String[] args) {
        // Test array
        int[] arr = {3, 9, 1, 4, 7};

        // Call the largest function and store the result
        int maxElement = largest(arr);

        // Print the result
        System.out.println("The largest element in the array is: " + maxElement);
    }
}
