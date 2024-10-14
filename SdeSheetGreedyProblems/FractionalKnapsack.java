package SdeSheetGreedyProblems;

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {

    /*
    Class to represent an item with a value and a weight.
    */
    static class Item {
        int value, weight;

        Item(int x, int y) {
            this.value = x;
            this.weight = y;
        }
    }

    /*
    Very Important:
    -1: The first object should come before the second object.
    1: The first object should come after the second object.
    0: The two objects are considered equal in terms of ordering.
    */
    static class ItemComparator implements Comparator<Item> {
        @Override
        public int compare(Item a, Item b) {
            double r1 = (double) a.value / (double) a.weight;  // value-to-weight ratio of item a
            double r2 = (double) b.value / (double) b.weight;  // value-to-weight ratio of item b

            // Sort in descending order of value-to-weight ratio
            if (r1 > r2) {
                return -1;  // Item 'a' should come before 'b'
            } else if (r1 < r2) {
                return 1;   // Item 'b' should come before 'a'
            } else {
                return 0;   // They are equal
            }
        }
    }

    public static void main(String[] args) {
        // Create items
        Item[] items = {
                new Item(100, 20),  // value = 100, weight = 20
                new Item(60, 15),   // value = 60, weight = 15
                new Item(120, 30)   // value = 120, weight = 30
        };

        // Maximum weight the knapsack can hold
        int capacity = 50;

        // Sort using ItemComparator
        sortUsingComparator(items);
        System.out.println("Sorted using ItemComparator:");
        printItems(items);

        // Sort using a lambda expression for comparison
        sortUsingLambda(items);
        System.out.println("\nSorted using Lambda Expression:");
        printItems(items);

        // Calculate maximum value in the knapsack
        double maxValue = fractionalKnapsack(capacity, items);
        System.out.println("\nMaximum value in the knapsack: " + maxValue);
    }

    // Method to sort items using ItemComparator
    private static void sortUsingComparator(Item[] items) {
        Arrays.sort(items, new ItemComparator());
    }

    // Method to sort items using a lambda expression
    private static void sortUsingLambda(Item[] items) {
        Arrays.sort(items, (a, b) -> {
            double p = (double) a.value / (double) a.weight;  // value-to-weight ratio of item a
            double q = (double) b.value / (double) b.weight;  // value-to-weight ratio of item b
            return (p > q) ? -1 : 1;  // Sort in descending order
        });
    }

    // Function to calculate the maximum total value in the knapsack.
    private static double fractionalKnapsack(int w, Item[] arr) {
        double totalVal = 0;  // Stores total value of the knapsack
        for (Item item : arr) {
            if (item.weight <= w) {
                // If the item can be fully added to the knapsack
                totalVal += item.value;  // Add the full value
                w -= item.weight;  // Decrease the remaining weight
            } else {
                // If only a fraction of the item can be added
                double fractionalVal = ((double) item.value / (double) item.weight) * w;
                totalVal += fractionalVal;  // Add the fractional value
                break;  // No more items can be added after this
            }
        }
        return totalVal;  // Return the total value of the knapsack
    }

    // Function to print items in the array
    private static void printItems(Item[] items) {
        for (Item item : items) {
            System.out.println("Value: " + item.value + ", Weight: " + item.weight);
        }
    }
}

