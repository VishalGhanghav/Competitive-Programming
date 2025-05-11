package SdeSheetLinkedList.LearnDoublyLinkedList;

import SdeSheetLinkedList.DoublyNode;

import static SdeSheetLinkedList.DoublyNode.convertArr2DLL;
import static SdeSheetLinkedList.DoublyNode.print;

public class ReverseDoublyLinkedList {
    public static void main(String[] args) {
        int[] arr = {12, 5, 6, 8, 4};
        // Convert the array to a doubly linked list

        DoublyNode head = convertArr2DLL(arr);
        System.out.print("before:");
        print(head);
        head = reverseDoubly(head);
        System.out.println("after");
        print(head);

    }

    private static DoublyNode reverseDoubly(DoublyNode head) {
        DoublyNode current = head;
        DoublyNode prevNode = null;
        while(current!=null) {
            //store prev link in node
            prevNode = current.prev;
            //now change prev link to point to next
            current.prev = current.next;
            //and ext to point to prevNode which was stored
            current.next = prevNode;

            //Now move ahead in linked list
            current = current.prev;//since prev now points to element ahead

        }
        //in the end current will be null and prevNode will have value but last second.an
        return prevNode.prev;
    }
}
