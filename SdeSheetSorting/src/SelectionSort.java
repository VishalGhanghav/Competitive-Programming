package SdeSheetSorting.src;

class SelectionSort {

    private static void swap(int[] arr, int i, int mini) {
        int temp = arr[i];
        arr[i] = arr[mini];
        arr[mini] = temp;
    }

    // Function to sort the array using selection sort algorithm.
    public static void selectionSort(int arr[], int n) {
        for(int i = 0; i < n - 1; i++) {
            // Find the minimum element in the remaining unsorted array
            int mini = i;
            for(int j = i + 1; j < n; j++) {
                if(arr[j] < arr[mini]) {
                    mini = j;
                }
            }
            // Swap the found minimum element with the current element
            swap(arr, i, mini);
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        int n = arr.length;

        System.out.println("Original array:");
        for(int num : arr) {
            System.out.print(num + " ");
        }

        selectionSort(arr, n);

        System.out.println("\nSorted array using Selection Sort:");
        for(int num : arr) {
            System.out.print(num + " ");
        }
    }
}
