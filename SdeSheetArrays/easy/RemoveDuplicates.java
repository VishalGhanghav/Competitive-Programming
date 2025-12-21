package SdeSheetArrays.easy;

public class RemoveDuplicates {
    public int removeDuplicates(int[] arr) {
        /*Cant use set as its unordered
        HashSet < Integer > set = new HashSet < > ();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        int k = set.size();
        int j = 0;
        for (int x: set) {
            arr[j++] = x;
        }
        return k;*/

        //Using Two pointers
        int i = 0;
        int n = arr.length;
        for (int j = 1; j < n; j++) {
            if (arr[j] != arr[i]) {
                i = i + 1;
                arr[i] = arr[j];
            }
        }
        //In the end i moves upto the no. of unique elements index
        //To return total elements ind+1
        return i + 1;
    }

    // Helper method to print array till k elements
    private static void printArray(int[] arr, int k) {
        for (int i = 0; i < k; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RemoveDuplicates obj = new RemoveDuplicates();

        int[] arr = {1, 1, 2, 2, 2, 3, 4, 4};
        int k = obj.removeDuplicates(arr);

        System.out.println("Number of unique elements: " + k);
        System.out.print("Array after removing duplicates: ");
        printArray(arr, k);
    }
}
