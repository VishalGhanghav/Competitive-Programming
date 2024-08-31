package SdeSheetSorting.src;

class InsertionSort {

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // Function to sort the array using insertion sort algorithm.
    public static void insertionSort(int arr[], int n) {
        for (int i = 1; i < n; i++) {
            // Inserting the element at position i into its correct place
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break; // No need to continue if the order is correct
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        int n = arr.length;

        System.out.println("Original array:");
        for(int num : arr) {
            System.out.print(num + " ");
        }

        insertionSort(arr, n);

        System.out.println("\nSorted array using Insertion Sort:");
        for(int num : arr) {
            System.out.print(num + " ");
        }
    }
}

