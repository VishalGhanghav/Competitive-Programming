package SdeSheetArrays.easy;

public class RotateArrayRight {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        System.out.println("Original Array:");
        printArray(arr);

        // Create a copy of the array for brute force approach
        int[] arrBrute = arr.clone();
        rotateBrute(arrBrute, k);
        System.out.println("\nArray after Brute Force Rotation:");
        printArray(arrBrute);

        // Create a copy of the array for optimal approach
        int[] arrOptimal = arr.clone();
        rotateOptimal(arrOptimal, k);
        System.out.println("\nArray after Optimal Rotation:");
        printArray(arrOptimal);
    }

    // Brute Force Approach
    public static void rotateBrute(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) return;

        k = k % n; // Ensure k is within the bounds of n

        int[] temp = new int[k];

        // Step 1: Copy the last k elements to temp
        for (int i = n - k; i < n; i++) {
            temp[i - (n - k)] = arr[i];
        }

        // Step 2: Shift the remaining elements to the right
        for (int i = n - k - 1; i >= 0; i--) {
            arr[i + k] = arr[i];
        }

        // Step 3: Re-insert the k elements from temp to the beginning of the array
        for (int i = 0; i < k; i++) {
            arr[i] = temp[i];
        }
    }

    // Optimal Approach using Array Reversal
    public static void rotateOptimal(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) return;

        k = k % n; // Ensure k is within the bounds of n

        // Step 1: Reverse the first n-k elements
        reverse(arr, 0, n - k - 1);

        // Step 2: Reverse the last k elements
        reverse(arr, n - k, n - 1);

        // Step 3: Reverse the entire array
        reverse(arr, 0, n - 1);
    }

    // Helper method to reverse elements in an array between two indices
    private static void reverse(int[] arr, int start, int end) {
        while (start <= end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // Helper method to print the array
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

