package SdeSheetLinkedList.LearnDoublyLinkedList;

import SdeSheetLinkedList.DoublyNode;

import static SdeSheetLinkedList.DoublyNode.convertArr2DLL;
import static SdeSheetLinkedList.DoublyNode.print;

public class InsertInDoublyLinkedList {
    public static void main(String[] args) {
        int[] arr = {12, 5, 6, 8, 4};
        // Convert the array to a doubly linked list
        DoublyNode head = convertArr2DLL(arr);
        head = insertAtEnd(head, 10); // Insert a node with value 10 at the end
        print(head);
    }

    private static DoublyNode insertAtEnd(DoublyNode head, int val) {
        DoublyNode temp= head;
        DoublyNode dummy = temp;
        while(temp.next!=null) {
            temp = temp.next;
        }
        DoublyNode newNode = new DoublyNode(val);
        temp.next = newNode;
        newNode.prev = temp;
        return dummy;
    }


}
