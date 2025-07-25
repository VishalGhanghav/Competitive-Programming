package SdeSheetLinkedList.MediumProblemsDLL;

import SdeSheetLinkedList.DoublyNode;

public class RemoveDuplicatesFromDLL {
    public static DoublyNode optimal(DoublyNode head) {
        if (head == null) return null;

        DoublyNode left = head;
        DoublyNode right = head.next;

        while (right != null) {
            if (left.val == right.val) {
                right = right.next;
            } else {
                left.next = right;
                right.prev = left;
                left = left.next;
                right = right.next;
            }
        }

        left.next = null; // Important to terminate properly
        return head;
    }

    public static void main(String[] args) {
        // Create sorted DLL: 1 <-> 2 <-> 2 <-> 3 <-> 3 <-> 4
        DoublyNode head = new DoublyNode(1);
        head.next = new DoublyNode(2);
        head.next.prev = head;
        head.next.next = new DoublyNode(2);
        head.next.next.prev = head.next;
        head.next.next.next = new DoublyNode(3);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new DoublyNode(3);
        head.next.next.next.next.prev = head.next.next.next;
        head.next.next.next.next.next = new DoublyNode(4);
        head.next.next.next.next.next.prev = head.next.next.next.next;

        DoublyNode res = optimal(head);

        // Print manually
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
