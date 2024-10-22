package SdeSheetGreedyProblems.hard;

import java.util.LinkedHashSet;

public class LRUPageReplacement {

    // Function to calculate the number of page faults using LRU algorithm
    static int pageFaults(int n, int c, int pages[]) {
        // If our data structure contains the page, follow LRU:
        // - Delete from that place and re-add at the end.
        // If it doesn't contain the page, increment faults.
        // If the cache is full, delete the first page and add the new page at the end.

        // List not recommended as insert/delete, search takes O(n).
        // Using LinkedHashSet as insert/delete, search takes O(1).
        LinkedHashSet<Integer> cacheList = new LinkedHashSet<>();
        int faults = 0; // Count of page faults

        // Iterate through each page in the pages array
        for (int page : pages) {
            // If the page is not already in the cache
            if (!cacheList.contains(page)) {
                faults++; // Increment the page fault count

                // If the cache is full, remove the least recently used page
                if (cacheList.size() == c) {
                    // Remove the first page using an iterator
                    int firstPage = cacheList.iterator().next(); // Get the first page (least recently used)
                    cacheList.remove(firstPage); // Remove the least recently used page
                }
            } else {
                // If the page is already in the cache, remove it to update its position
                cacheList.remove((Integer) page); // Remove the current page
            }
            cacheList.add(page); // Add the current page as the most recently used
        }
        return faults; // Return the total number of page faults
    }

    // Main method to test the pageFaults function
    public static void main(String[] args) {
        int n = 9; // Number of pages
        int c = 4; // Capacity of the cache
        int[] pages = {5, 0, 1, 3, 2, 4, 1, 0, 5}; // Page reference string

        // Call the pageFaults method and print the result
        System.out.println("Page Faults: " + pageFaults(n, c, pages)); // Output should be 8
    }
}
