package SdeSheetLinkedList.Learn1dLinkedList;

import SdeSheetLinkedList.Node;
import static SdeSheetLinkedList.Node.printLinkedList;

public class DeleteInLinkedList {
    public static void main(String[] args) {
        Node node = new Node(5);
        node.next = new Node(6);
        node.next.next = new Node(7);
        node.next.next.next = new Node(8);
        node.next.next.next.next = new Node(9);


        node = deleteAtStart(node);
        printLinkedList(node);

        node = deleteAtEnd(node);
        printLinkedList(node);

        node = deleteAtIndex(node, 1);
        printLinkedList(node);



    }

    private static Node deleteAtEnd(Node node) {
        Node temp = node;
        Node dummy = temp;
        //stop at last second
        while (temp.next.next!=null) {
            temp = temp.next;
        }
        temp.next = temp.next.next;

        node = dummy;
        return node;
    }

    private static Node deleteAtIndex(Node node, int i) {
        int cnt =0;
        Node temp = node;
        Node dummy = temp;
        while(temp!=null && cnt!=i-1) {
            temp = temp.next;
            cnt++;
        }
        temp.next = temp.next.next;
        node = dummy;
        return node;
    }

    private static Node deleteAtStart(Node head) {
        if(head == null || head.next==null) {
            return null;
        }
        Node next = head.next;
        head = next;

        return head;

    }
}
