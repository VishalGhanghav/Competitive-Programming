package SdeSheetSorting.src;

public class QuickSort {
    static void quickSort(int arr[], int low, int high) {
        // code here
        //if we have more than one elements
        if (low < high) {
            int partitionIndex = partition(arr, low, high);
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    static int partition(int arr[], int low, int high) {
        // your code here
        int i = low, j = high;
        int pivot = arr[low];
        while (i < j) {  // Corrected condition for the loop
            //to find the nearest greater from left skip all smaller
            while (arr[i] <= pivot && i < high) { // Ensure i does not exceed bounds
                i++;
            }
            //to find the nearest smaller from right skip all greater
            while (arr[j] > pivot && j > low) { // Ensure j does not exceed bounds
                j--;
            }
            //AFter both loops we have reached the point where arr[i]>pivot
            //&& arr[j]<pivot if i<j we can swap
            if (i < j) {
                swap(arr, i, j);
            }
        }
        //After the loop ends we will have to swap pivot and j
        swap(arr, low, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        int n = arr.length;

        // Print original array
        System.out.print("Original array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // Call quickSort on the entire array
        quickSort(arr, 0, n - 1);

        // Print sorted array
        System.out.print("Sorted array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
