package SdeSheetLinkedList.MediumProblemLinkedList;

import SdeSheetLinkedList.Node;

public class ReverseLinkedListIerativeAndRecursive {

    // ITERATIVE APPROACH
    public static Node reverseListIterative(Node head) {
        Node temp = null;
        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = temp;
            temp = head;
            head = next;
        }
        return temp;
    }

    // RECURSIVE APPROACH
    public static Node reverseListRecursive(Node head) {
        return reverse(head, null, null);
    }

    private static Node reverse(Node head, Node temp, Node next) {
        if (head == null) {
            return temp;
        }
        next = head.next;
        head.next = temp;
        temp = head;
        head = next;
        return reverse(head, temp, next);
    }

    public static void main(String[] args) {
        Node node = new Node(5);
        node.next = new Node(6);
        node.next.next = new Node(7);
        node.next.next.next = new Node(8);
        node.next.next.next.next = new Node(9);

        Node reversedIterative = reverseListIterative(node);
        System.out.print("Iterative Reversed List: ");
        for (Node cur = reversedIterative; cur != null; cur = cur.next) {
            System.out.print(cur.val + " ");
        }
        System.out.println();

        // Reconstruct list again for independent test
        node = new Node(5);
        node.next = new Node(6);
        node.next.next = new Node(7);
        node.next.next.next = new Node(8);
        node.next.next.next.next = new Node(9);

        Node reversedRecursive = reverseListRecursive(node);
        System.out.print("Recursive Reversed List: ");
        for (Node cur = reversedRecursive; cur != null; cur = cur.next) {
            System.out.print(cur.val + " ");
        }
        System.out.println();
    }
}