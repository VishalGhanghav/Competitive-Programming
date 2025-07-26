package SdeSheetLinkedList.HardProblemsLL;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FlattenLinkedList {

    static class Node {
        int data;
        Node next;
        Node bottom;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.bottom = null;
        }
    }

    public static Node brute(Node head) {
        // Brute
        // Approach: Put in list, sort and put in linked list in vertical
        // TC: O(m*n) for traversing complete ll + O(nlogn)Sort + convert(O(m*n))
        Node temp1 = head;
        List<Integer> res = new ArrayList<>();
        while (temp1 != null) {
            Node temp2 = temp1;
            while (temp2 != null) {
                res.add(temp2.data);
                temp2 = temp2.bottom;
            }
            temp1 = temp1.next;
        }
        Collections.sort(res);
        return convertListToLinkedList(res);
    }

    public static Node optimal(Node head) {
        // Optimal
        // Use recursive and merge approach
        if (head == null || head.next == null) {
            return head;
        }

        Node mergedHead = optimal(head.next);
        return mergeTwoLinkedLists(head, mergedHead);
    }

    public static Node mergeTwoLinkedLists(Node list1, Node list2) {
        Node dummy = new Node(-1);
        Node temp = dummy;
        while (list1 != null && list2 != null) {
            // As both are sorted lists. merge two sorted lists
            if (list1.data < list2.data) {
                temp.bottom = list1;
                temp = list1;
                list1 = list1.bottom;
            } else {
                temp.bottom = list2;
                temp = list2;
                list2 = list2.bottom;
            }
            // For all nodes in vert next = null
            temp.next = null;
        }

        // In the end if either list is not empty connect it
        if (list1 != null) {
            temp.bottom = list1;
        } else {
            temp.bottom = list2;
        }

        // Break the last node's link to prevent cycles
        if (dummy.bottom != null) {
            dummy.bottom.next = null;
        }

        return dummy.bottom;
    }

    public static Node convertListToLinkedList(List<Integer> list) {
        Node head = new Node(list.get(0));
        Node temp = head;

        for (int i = 1; i < list.size(); i++) {
            Node newNode = new Node(list.get(i));
            temp.bottom = newNode;
            temp = newNode;
        }
        return head;
    }

    public static void printFlattenedList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.bottom;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Input setup for both methods: same structure
        // Example: 5 -> 10 -> 19 -> 28
        //           |     |     |     |
        //           7     20    22    35
        //           |           |     |
        //           8           50    40
        //           |                 |
        //           30                45

        Node head1 = new Node(5);
        head1.bottom = new Node(7);
        head1.bottom.bottom = new Node(8);
        head1.bottom.bottom.bottom = new Node(30);
        head1.next = new Node(10);
        head1.next.bottom = new Node(20);
        head1.next.next = new Node(19);
        head1.next.next.bottom = new Node(22);
        head1.next.next.bottom.bottom = new Node(50);
        head1.next.next.next = new Node(28);
        head1.next.next.next.bottom = new Node(35);
        head1.next.next.next.bottom.bottom = new Node(40);
        head1.next.next.next.bottom.bottom.bottom = new Node(45);

        Node head2 = cloneList(head1); // Separate copy for optimal

        System.out.print("Brute: ");
        Node flatBrute = brute(head1);
        printFlattenedList(flatBrute);

        System.out.print("Optimal: ");
        Node flatOptimal = optimal(head2);
        printFlattenedList(flatOptimal);
    }

    // Helper to deep copy a complex multi-level linked list
    public static Node cloneList(Node head) {
        if (head == null) return null;

        Node newHead = new Node(head.data);
        newHead.bottom = cloneList(head.bottom);
        newHead.next = cloneList(head.next);
        return newHead;
    }
}
