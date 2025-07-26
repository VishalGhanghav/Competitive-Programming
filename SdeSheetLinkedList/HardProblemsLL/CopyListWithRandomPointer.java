package SdeSheetLinkedList.HardProblemsLL;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {

    // Brute Force Approach
    // TC: O(3n), SC: O(n)
    public Node copyRandomListBrute(Node head) {
        if (head == null) return null;

        // Step 1: Create new nodes and store mapping
        Map<Node, Node> nodeMap = new HashMap<>();
        Node temp = head;
        while (temp != null) {
            Node copy = new Node(temp.val);
            nodeMap.put(temp, copy);
            temp = temp.next;
        }

        // Step 2: Use map to connect next and random pointers
        temp = head;
        while (temp != null) {
            Node copy = nodeMap.get(temp);
            copy.next = nodeMap.get(temp.next);
            copy.random = nodeMap.get(temp.random);
            temp = temp.next;
        }

        // Step 3: Return the copied head
        return nodeMap.get(head);
    }

    // Optimal Approach
    // TC: O(n), SC: O(1)
    public Node copyRandomListOptimal(Node head) {
        if (head == null) return null;

        // Step 1: Insert copy nodes between original nodes
        insertCopyInBetween(head);

        // Step 2: Connect random pointers for the copy nodes
        connectRandomPointers(head);

        // Step 3: Separate the copy list from the original
        return getDeepCopyList(head);
    }

    private void insertCopyInBetween(Node head) {
        Node temp = head;
        while (temp != null) {
            Node copy = new Node(temp.val);
            copy.next = temp.next;
            temp.next = copy;
            temp = copy.next;
        }
    }

    private void connectRandomPointers(Node head) {
        Node temp = head;
        while (temp != null) {
            Node copy = temp.next;
            if (temp.random != null) {
                copy.random = temp.random.next;
            }
            temp = copy.next;
        }
    }

    private Node getDeepCopyList(Node head) {
        Node dummy = new Node(-1);
        Node copyTail = dummy;
        Node temp = head;

        while (temp != null) {
            Node copy = temp.next;
            temp.next = copy.next; // Restore original list
            copyTail.next = copy;
            copyTail = copy;
            temp = temp.next;
        }
        return dummy.next;
    }

    // Node class for random pointer list
    static class Node {
        int val;
        Node next;
        Node random;

        Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // Helper method to print list
    public void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            int randomVal = (temp.random != null) ? temp.random.val : -1;
            System.out.println("Node: " + temp.val + ", Random: " + randomVal);
            temp = temp.next;
        }
    }

    // Main method to run both approaches independently
    public static void main(String[] args) {
        CopyListWithRandomPointer obj = new CopyListWithRandomPointer();

        // Create example list: 7 -> 13 -> 11 -> 10 -> 1 with random links
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

        // Run Brute
        Node copiedBrute = obj.copyRandomListBrute(node1);
        System.out.println("Brute Copy:");
        obj.printList(copiedBrute);

        // Re-create original list again for next test (because brute doesn't modify original, we can reuse it)
        node1 = new Node(7);
        node2 = new Node(13);
        node3 = new Node(11);
        node4 = new Node(10);
        node5 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

        // Run Optimal
        Node copiedOptimal = obj.copyRandomListOptimal(node1);
        System.out.println("\nOptimal Copy:");
        obj.printList(copiedOptimal);
    }
}
