package SdeSheetLinkedList.HardProblemsLL;

import SdeSheetLinkedList.Node;

public class RotateLinkedList {
    public static Node brute(Node head, int k) {
        // Brute
        // go to last element, connect that to head and mark prev as null.
        // keep doing till k > 0

        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int len = getLength(head);
        k = k % len;

        while (k > 0) {
            Node temp = head;
            Node prev = null;

            // Go to last node
            while (temp.next != null) {
                prev = temp;
                temp = temp.next;
            }

            // Move last to front
            temp.next = head;
            head = temp;
            prev.next = null;
            k--;
        }
        return head;
    }

    public static Node optimal(Node head, int k) {
        // Optimal: try to rotate by %
        if (head == null || head.next == null) {
            return head;
        }

        Node temp = head;
        int length = 1;

        // Temp will point to last elmt
        while (temp.next != null) {
            temp = temp.next;
            length++;
        }

        // link last node to first node
        temp.next = head;
        k = k % length; // when k is more than length of list
        int end = length - k; // to get end of the list

        while (end > 0) {
            temp = temp.next;
            end--;
        }

        head = temp.next;
        temp.next = null;
        return head;
    }

    private static int getLength(Node head) {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Input: 1->2->3->4->5, k=2
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);

        System.out.print("Brute: ");
        Node bruteRes = brute(head1, 2);
        printList(bruteRes);

        System.out.print("Optimal: ");
        Node optRes = optimal(head2, 2);
        printList(optRes);
    }
}
