package SdeSheetSorting.src;

class BubbleSort {

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // Function to sort the array using bubble sort algorithm.
    public static void bubbleSort(int arr[], int n) {
        for(int i = n - 1; i >= 0; i--) {
            // If there were no swaps made in a step, it means arr is now sorted
            int didSwap = 0;
            for(int j = 0; j < i; j++) {
                if(arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    didSwap = 1;
                }
            }
            if(didSwap == 0) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 1, 4, 2, 8};
        int n = arr.length;

        System.out.println("Original array:");
        for(int num : arr) {
            System.out.print(num + " ");
        }

        bubbleSort(arr, n);

        System.out.println("\nSorted array using Bubble Sort:");
        for(int num : arr) {
            System.out.print(num + " ");
        }
    }
}

