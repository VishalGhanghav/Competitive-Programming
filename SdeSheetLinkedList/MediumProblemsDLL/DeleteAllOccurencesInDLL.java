package SdeSheetLinkedList.MediumProblemsDLL;

import SdeSheetLinkedList.DoublyNode;

public class DeleteAllOccurencesInDLL {
    static DoublyNode deleteAllOccurOfX(DoublyNode head, int x) {
        // Write your code here
        DoublyNode temp = head;

        while (temp != null) {
            if (temp.val == x) {

                if (temp == head) {
                    head = head.next;
                }

                DoublyNode prevNode = temp.prev;
                DoublyNode nextNode = temp.next;

                if (nextNode != null) {
                    nextNode.prev = prevNode;
                }

                if (prevNode != null) {
                    prevNode.next = nextNode;
                }

                temp = temp.next;
            } else {
                temp = temp.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        // Input: 1 <-> 2 <-> 3 <-> 2 <-> 4, x = 2
        DoublyNode head = new DoublyNode(1);
        head.next = new DoublyNode(2);
        head.next.prev = head;
        head.next.next = new DoublyNode(3);
        head.next.next.prev = head.next;
        head.next.next.next = new DoublyNode(2);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new DoublyNode(4);
        head.next.next.next.next.prev = head.next.next.next;

        head = deleteAllOccurOfX(head, 2); // expected: 1 <-> 3 <-> 4

        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" <-> ");
            head = head.next;
        }
    }
}
