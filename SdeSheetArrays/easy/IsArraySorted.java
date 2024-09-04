package SdeSheetArrays.easy;

public class IsArraySorted {

    // Function to check if the array is sorted in non-decreasing order
    public static int isSorted(int n, int[] a) {
        // Iterate through the array starting from the second element
        for (int i = 1; i < n; i++) {
            // If the current element is less than the previous element, the array is not sorted
            if (a[i] < a[i - 1]) {
                return 0; // Return 0 indicating the array is not sorted
            }
        }
        return 1; // Return 1 indicating the array is sorted
    }

    public static void main(String[] args) {
        // Test array
        int[] a = {1, 2, 3, 4, 5};

        // Call the isSorted function and store the result
        int result = isSorted(a.length, a);

        // Print the result
        if (result == 1) {
            System.out.println("The array is sorted.");
        } else {
            System.out.println("The array is not sorted.");
        }
    }
}

