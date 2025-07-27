package SdeSheetHeaps.HeapsMediumProblems;

import SdeSheetLinkedList.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedLinkedLists {


    // Brute Force
// TC: O(N + n log(n) + n) = O(n log(n))
// SC: O(n)
    public static Node brute(Node[] lists) {
        // Brute
        // Approach: Put all in list. Sort that list and put back in linked list
        List<Integer> sortedList = new ArrayList<>();
        for (Node list : lists) {
            while (list != null) {
                sortedList.add(list.val);
                list = list.next;
            }
        }
        Collections.sort(sortedList);
        Node dummy = new Node(-1);
        Node temp = dummy;
        for (Integer num : sortedList) {
            Node newNode = new Node(num);
            temp.next = newNode;
            temp = temp.next;
        }
        return dummy.next;
    }

    // Optimal using Min Heap
// TC: O(N log k), where N = total nodes and k = number of lists
// SC: O(k) for minHeap
    public static Node optimal(Node[] lists) {
        // Optimal
        // Approach: Push all heads in minHeap. Take smallest, add to result, push its next.

        PriorityQueue<Node> minHeap = new PriorityQueue<>(
                (a, b) -> a.val - b.val
        );

        for (Node headNode : lists) {
            if (headNode != null) {
                minHeap.add(headNode);
            }
        }

        Node dummy = new Node(-1);
        Node res = dummy;

        while (!minHeap.isEmpty()) {
            Node smallest = minHeap.poll();
            res.next = smallest;
            res = res.next;
            if (smallest.next != null) {
                minHeap.add(smallest.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Example input:
        // list1: 1 -> 4 -> 5
        // list2: 1 -> 3 -> 4
        // list3: 2 -> 6
        Node list1 = new Node(1, new Node(4, new Node(5)));
        Node list2 = new Node(1, new Node(3, new Node(4)));
        Node list3 = new Node(2, new Node(6));

        Node[] input1 = new Node[]{list1, list2, list3};
        Node[] input2 = new Node[]{list1, list2, list3}; // Reusing for demo

        // Run Brute
        Node bruteRes = brute(input1);
        System.out.print("Brute Output: ");
        printList(bruteRes);

        // Run Optimal
        Node optimalRes = optimal(input2);
        System.out.print("Optimal Output: ");
        printList(optimalRes);
    }

    // Helper method to print list (only used in main)
    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
