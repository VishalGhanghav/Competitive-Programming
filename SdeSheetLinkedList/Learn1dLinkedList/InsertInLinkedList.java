package SdeSheetLinkedList.Learn1dLinkedList;

import SdeSheetLinkedList.Node;

import static SdeSheetLinkedList.Node.printLinkedList;

public class InsertInLinkedList {

    public static void main(String[] args) {
        Node node = new Node(5);
        node.next = new Node(6);
        node.next.next = new Node(7);

        node = insertAtStart(node, 4);
        printLinkedList(node);

        node = insertAtIndex(node, 2,11);
        printLinkedList(node);

        node = insertAtEnd(node, 8);
        printLinkedList(node);

    }

    private static Node insertAtIndex(Node node, int ind,int val) {
        int cnt = 0;
        Node temp = node;
        Node head = temp;
        while(temp !=null && cnt!=ind-1) {
            temp = temp.next;
            cnt++;
        }
        Node next = temp.next;
        Node newNode = new Node(val);
        temp.next = newNode;
        newNode.next = next;

        node = head;
        return node;
    }

    private static Node insertAtEnd(Node head, int val) {
        Node temp = head;
        Node dummyHead = temp;
        while(temp.next!=null) {
            temp = temp.next;
        }
        Node newNode = new Node(val);
        temp.next = newNode;

        head = dummyHead;
        return dummyHead;
    }



    private static Node insertAtStart(Node head, int val) {

        if(head ==null) {
            return head;
        }

        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        return head;
    }
}
