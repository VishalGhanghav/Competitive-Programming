package SdeSheetLinkedList.MediumProblemLinkedList;

import SdeSheetLinkedList.Node;

import java.util.HashSet;
import java.util.Set;

public class DetectStartOfCycle {
    // BRUTE FORCE APPROACH (using HashSet)
    public static Node detectCycleBrute(Node head) {
        Set<Node> set = new HashSet<>();
        Node temp = head;
        while (temp != null) {
            if (!set.add(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // OPTIMAL APPROACH (Floyd’s Tortoise and Hare)
    public static Node detectCycleOptimal(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Create a cycle: 1 -> 2 -> 3 -> 4 -> 5 -> 3 ...
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);
        node.next.next.next.next.next = node.next.next; // cycle starts at node 3

        Node startBrute = detectCycleBrute(node);
        System.out.println("Brute Cycle starts at: " + (startBrute != null ? startBrute.val : "null"));

        // Recreate list for independent optimal test
        node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);
        node.next.next.next.next.next = node.next.next; // cycle starts at node 3

        Node startOptimal = detectCycleOptimal(node);
        System.out.println("Optimal Cycle starts at: " + (startOptimal != null ? startOptimal.val : "null"));
    }
}
