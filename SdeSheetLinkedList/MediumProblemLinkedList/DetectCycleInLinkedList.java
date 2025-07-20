package SdeSheetLinkedList.MediumProblemLinkedList;

import SdeSheetLinkedList.Node;

import java.util.HashSet;
import java.util.Set;

public class DetectCycleInLinkedList {
    // BRUTE FORCE APPROACH (using HashSet)
    public static boolean hasCycleBrute(Node head) {
        Set<Node> set = new HashSet<>();
        Node temp = head;
        while (temp != null) {
            if (!set.add(temp)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // OPTIMAL APPROACH (Floyd’s Tortoise and Hare)
    public static boolean hasCycleOptimal(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Create cycle: 1 -> 2 -> 3 -> 4 -> 5 -> 3 ...
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);
        node.next.next.next.next.next = node.next.next; // cycle at node 3

        System.out.println("Brute Cycle Detected: " + hasCycleBrute(node));

        // Recreate list again for independent test
        node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);
        node.next.next.next.next.next = node.next.next; // cycle at node 3

        System.out.println("Optimal Cycle Detected: " + hasCycleOptimal(node));
    }
}
