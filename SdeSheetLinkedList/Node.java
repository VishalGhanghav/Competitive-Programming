package SdeSheetLinkedList;

public class Node {
    public int val;
    public Node next;

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public Node(int val) {
        this.val = val;
        this.next = null;
    }
    public static void printLinkedList(Node head) {
        Node temp = head;

        while(temp!=null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
