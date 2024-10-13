package SdeSheetArrays.hard;

public class RepeatingAndMissing {

    public static void main(String[] args) {
        // Example input array
        int[] arr = {3, 1, 3, 4, 2}; // 3 is repeating, 5 is missing

        // Run each approach and display results
        int[] bruteResult = findRepeatingAndMissingBrute(arr);
        System.out.println("Brute Force Approach: Repeating = " + bruteResult[0] + ", Missing = " + bruteResult[1]);

        int[] betterResult = findRepeatingAndMissingHash(arr);
        System.out.println("Hashing Approach: Repeating = " + betterResult[0] + ", Missing = " + betterResult[1]);

        int[] optimalResult = findRepeatingAndMissingMath(arr);
        System.out.println("Mathematical Approach: Repeating = " + optimalResult[0] + ", Missing = " + optimalResult[1]);
    }

    static int[] findRepeatingAndMissingBrute(int arr[]) {
        long n = arr.length;
        int missing = -1, repeating = -1;
        int[] res = new int[2];

        // Brute
        // See if any number from 1->n appears
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j] == i) {
                    cnt++;
                }
            }
            if (cnt == 2) {
                repeating = i;
            }
            if (cnt == 0) {
                missing = i;
            }
        }
        res = new int[]{repeating, missing};
        return res;
    }

    static int[] findRepeatingAndMissingHash(int arr[]) {
        long n = arr.length;
        int missing = -1, repeating = -1;
        int[] res = new int[2];

        // Better
        // Use hashing
        int hash[] = new int[(int)n + 1];
        for (int i = 0; i < n; i++) {
            hash[arr[i]]++;
        }
        for (int i = 1; i <= n; i++) {
            if (hash[i] == 2) {
                repeating = i;
            }
            if (hash[i] == 0) {
                missing = i;
            }
            // Both are found
            if (repeating != -1 && missing != -1) {
                break;
            }
        }
        res = new int[]{repeating, missing};
        return res;
    }

    static int[] findRepeatingAndMissingMath(int arr[]) {
        long n = arr.length;
        int missing = -1, repeating = -1;
        int[] res = new int[2];

        // Optimal
        // Using maths
        long s = 0, s2 = 0;

        for (int i = 0; i < n; i++) {
            s += arr[i];
            // Since doing operation. So multiplication needed
            s2 += (long) arr[i] * (long) arr[i];
        }

        long sn = (n * (n + 1)) / 2;
        long s2n = (n * (n + 1) * (2 * n + 1)) / 6;
        // calculating x - y = s - sn
        long val1 = s - sn;
        // calculating x + y = s2 - s2n / (x - y)
        long val2 = (s2 - s2n);
        val2 = val2 / val1;

        long longRepeating = (val1 + val2) / 2;
        // longRepeating - missing = val1 so, missing = longRepeating - val1
        long longMissing = longRepeating - val1;

        res = new int[]{(int) longRepeating, (int) longMissing};
        return res;
    }
}

