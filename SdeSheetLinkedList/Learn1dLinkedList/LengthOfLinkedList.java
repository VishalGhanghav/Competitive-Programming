package SdeSheetLinkedList.Learn1dLinkedList;

import SdeSheetLinkedList.Node;

public class LengthOfLinkedList {
    public static void main(String[] args) {
        Node node = new Node(5);
        node.next = new Node(6);
        node.next.next = new Node(7);

        System.out.println(length(node));

    }

    private static int length(Node node) {
        int cnt =0;
        while(node!=null) {
            cnt++;
            node = node.next;
        }
        return cnt;
    }
}
