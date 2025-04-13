package SdeSheetBinarySearch.BinarySearchOnAnswer;

public class AllocateMinNoOfPages {

        public static void main(String[] args) {
            int[] books = {12, 34, 67, 90};
            int students = 2;

            System.out.println("Brute Force Result: " + findPagesBrute(books, students));
            System.out.println("Optimal Result: " + findPagesOptimal(books, students));
        }

        // Brute Force Approach: O((sum - max + 1) * n)
        public static int findPagesBrute(int[] arr, int k) {
            int n = arr.length;
            if (n < k) return -1;

            int maxPagesInBook = Integer.MIN_VALUE;
            int sumOfPages = 0;

            for (int elmt : arr) {
                maxPagesInBook = Math.max(maxPagesInBook, elmt);
                sumOfPages += elmt;
            }

            // Try every possible max allocation from max single book to total sum
            for (int i = maxPagesInBook; i <= sumOfPages; i++) {
                if (isPossible(arr, k, i)) {
                    return i;
                }
            }

            return -1;
        }

        // Optimal Approach: Binary Search on Answer Space
        public static int findPagesOptimal(int[] arr, int k) {
            int n = arr.length;
            if (n < k) return -1;

            int maxPagesInBook = Integer.MIN_VALUE;
            int sumOfPages = 0;

            for (int elmt : arr) {
                maxPagesInBook = Math.max(maxPagesInBook, elmt);
                sumOfPages += elmt;
            }

            int start = maxPagesInBook;
            int end = sumOfPages;

            while (start <= end) {
                int mid = start + (end - start) / 2;
                // Check if mid no of pages are possible to allocate.
                // If yes, see if less page allocation is also possible
                if (isPossible(arr, k, mid)) {
                    //ans=mid
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            return start;//return ans;
        }

        // Shared helper to check if it is possible to allocate with given max pages
        private static boolean isPossible(int[] books,int students,int reqPages) {
            int cnt =1;//1st student starts reading
            //decalre cnt=0 and do cnt++ in end or do cnt=1 here and no cnt++ in end
            int pageSum = 0;

            for(int pages:books) {
                if(pageSum+pages > reqPages) {
                    cnt++;
                    pageSum = 0;
                    if(cnt>students) {
                        return false;
                    }
                }
                pageSum += pages;
            }
            //This will check for all pages
            //return cnt<=students;
            return true;
        }

}
