package SdeSheetArrays.easy;

public class RotateArrayLeft {

    static void rotateArrBrute(int arr[], int k, int n) {
        // Ensure k is within the bounds of n
        k = k % n;

        // Brute Force Approach
        // Step 1: Copy the first k elements to a temporary array
        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = arr[i];
        }

        // Step 2: Shift the remaining elements to the left by k positions
        for (int i = k; i < n; i++) {
            arr[i - k] = arr[i];
        }

        // Step 3: Re-insert the k elements from temp to the end of the array
        int ind = 0;
        for (int i = n - k; i < n; i++) {
            arr[i] = temp[ind++];
        }
    }

    static void rotateArrOptimal(int arr[], int k, int n) {
        // Ensure k is within the bounds of n
        k = k % n;

        // Optimal Approach using Array Reversal
        // Step 1: Reverse the first k elements
        reverse(arr, 0, k - 1);

        // Step 2: Reverse the last n-k elements
        reverse(arr, k, n - 1);

        // Step 3: Reverse the entire array to achieve the final rotated state
        reverse(arr, 0, n - 1);
    }

    // Helper method to reverse elements in an array between two indices
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        System.out.println("Original Array:");
        printArray(arr);

        // Rotate using Brute Force approach
        int[] arrBrute = arr.clone(); // Clone array to maintain the original state
        rotateArrBrute(arrBrute, k, arrBrute.length);
        System.out.println("\nArray after Brute Force Rotation:");
        printArray(arrBrute);

        // Rotate using Optimal approach
        int[] arrOptimal = arr.clone(); // Clone array to maintain the original state
        rotateArrOptimal(arrOptimal, k, arrOptimal.length);
        System.out.println("\nArray after Optimal Rotation:");
        printArray(arrOptimal);
    }

    // Helper method to print the array
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

