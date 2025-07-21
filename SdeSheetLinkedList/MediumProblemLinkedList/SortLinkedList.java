package SdeSheetLinkedList.MediumProblemLinkedList;

import SdeSheetLinkedList.Node;

import java.util.*;

public class SortLinkedList {

    // Brute: Extract all values, sort, then rebuild the list
    public static Node sortListBrute(Node head) {
        if (head == null || head.next == null)
            return head;

        // Step 1: Extract all values
        List<Integer> values = new ArrayList<>();
        Node temp = head;
        while (temp != null) {
            values.add(temp.val);
            temp = temp.next;
        }

        // Step 2: Sort values
        Collections.sort(values);

        // Step 3: Rebuild linked list from sorted values
        Node dummy = new Node(0); // dummy node to simplify appending
        Node current = dummy;
        for (int val : values) {
            current.next = new Node(val);
            current = current.next;
        }

        return dummy.next;
    }

    // Optimal: Merge Sort on Linked List
    // Approach:
    // 1. Find Middle
    // 2. Recursively sort both halves
    // 3. Merge sorted halves
    public static Node sortListOptimal(Node head) {
        // Base condition
        if (head == null || head.next == null)
            return head;

        Node mid = getMiddle(head);

        // Divide ll in two halves
        // Basically left points to start of left half
        // right points to start of right half
        Node right = mid.next;
        mid.next = null;
        Node left = head;

        left = sortListOptimal(left);
        right = sortListOptimal(right);

        // Merge two sorted lists
        return mergeTwoSortedLinkedLists(left, right);
    }

    private static Node mergeTwoSortedLinkedLists(Node list1, Node list2) {
        Node dummy = new Node(-1);
        Node temp = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }

        // If any list still has remaining elements, append them
        temp.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }

    private static Node getMiddle(Node head) {
        // If the list is empty or has only one node
        // the middle is the head itself
        if (head == null || head.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Node input1 = new Node(4);
        input1.next = new Node(2);
        input1.next.next = new Node(1);
        input1.next.next.next = new Node(3);

        Node input2 = new Node(4);
        input2.next = new Node(2);
        input2.next.next = new Node(1);
        input2.next.next.next = new Node(3);

        Node resBrute = sortListBrute(input1);
        Node resOptimal = sortListOptimal(input2);

        System.out.print("Brute: ");
        for (Node cur = resBrute; cur != null; cur = cur.next) {
            System.out.print(cur.val + " ");
        }
        System.out.println();

        System.out.print("Optimal: ");
        for (Node cur = resOptimal; cur != null; cur = cur.next) {
            System.out.print(cur.val + " ");
        }
        System.out.println();
    }
}
