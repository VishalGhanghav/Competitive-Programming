package SdeSheetLinkedList.MediumProblemLinkedList;

import SdeSheetLinkedList.Node;

public class DeleteNthNodeFromBack {
    // ? Optimal Approach using two pointers (Fast-Slow)
    public static Node removeNthFromEnd(Node head, int n) {
        // Approach: If we travel n=2 from back remaining as distance d.
        // So we move fast pointer till n=2 and then move slow and fast
        // together. Till fast reaches null i.e., cover distance d. Slow will be
        // at position to delete

        Node fast = head;
        Node slow = head;

        for (int i = 1; i <= n; i++) {
            fast = fast.next;
        }

        // If fast pointer is already at the end
        if (fast == null) {
            return head.next;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        //  ListNode delNode = slow.next;
        //  slow.next = slow.next.next;
        //  delNode = null;

        return head;
    }

    public static void main(String[] args) {
        // Input: 1 -> 2 -> 3 -> 4 -> 5 ? null, n = 2
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);

        Node result = removeNthFromEnd(node, 2);
        System.out.print("List after removing 2nd node from end: ");
        for (Node cur = result; cur != null; cur = cur.next) {
            System.out.print(cur.val + " ");
        }
        System.out.println();
    }
}
