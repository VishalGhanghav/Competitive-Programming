package SdeSheetLinkedList.MediumProblemLinkedList;

import SdeSheetLinkedList.Node;

import static SdeSheetLinkedList.Node.printLinkedList;

public class MiddleElementInLinkedList {

    // BRUTE FORCE APPROACH
    public static Node middleNodeBrute(Node head) {
        int cnt = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            cnt++;
        }

        int res = cnt / 2;
        for (int i = 0; i < res; i++) {
            head = head.next;
        }

        return head;
    }

    // OPTIMAL APPROACH (Tortoise and Hare / Slow-Fast Pointer)
    public static Node middleNodeOptimal(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        Node node = new Node(5);
        node.next = new Node(6);
        node.next.next = new Node(7);
        node.next.next.next = new Node(8);
        node.next.next.next.next = new Node(9);

        Node midBrute = middleNodeBrute(node);
        System.out.print("Brute Middle to End: ");
        printLinkedList(midBrute);

        // Reconstruct list again for independent test
        node = new Node(5);
        node.next = new Node(6);
        node.next.next = new Node(7);
        node.next.next.next = new Node(8);
        node.next.next.next.next = new Node(9);

        Node midOptimal = middleNodeOptimal(node);
        System.out.print("Optimal Middle to End: ");
        printLinkedList(midOptimal);
    }
}