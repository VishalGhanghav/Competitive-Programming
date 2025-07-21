package SdeSheetLinkedList.MediumProblemLinkedList;

import SdeSheetLinkedList.Node;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoLinkedLists {
    public static Node getIntersectionNodeBrute(Node headA, Node headB) {
        // Brute
        Set<Node> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (!set.add(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    public static Node getIntersectionNodeOptimal(Node headA, Node headB) {
        // Optimal
        Node d1 = headA, d2 = headB;
        while (d1 != d2) {
            if (d1 == null)
                d1 = headB;
            else
                d1 = d1.next;

            if (d2 == null)
                d2 = headA;
            else
                d2 = d2.next;
        }
        return d1;
    }

    public static void main(String[] args) {
        // Shared nodes (intersection starts here)
        Node common = new Node(8);
        common.next = new Node(10);

        // First list: 3 -> 7 -> 8 -> 10
        Node headA = new Node(3);
        headA.next = new Node(7);
        headA.next.next = common;

        // Second list: 99 -> 1 -> 8 -> 10
        Node headB = new Node(99);
        headB.next = new Node(1);
        headB.next.next = common;

        // Run Brute
        Node ans1 = getIntersectionNodeBrute(headA, headB);
        System.out.println("Brute: " + (ans1 != null ? ans1.val : "No intersection"));

        // Run Optimal
        Node ans2 = getIntersectionNodeOptimal(headA, headB);
        System.out.println("Optimal: " + (ans2 != null ? ans2.val : "No intersection"));
    }
}
