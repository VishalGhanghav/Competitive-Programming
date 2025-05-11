package SdeSheetLinkedList.Learn1dLinkedList;

import SdeSheetLinkedList.Node;

public class SearchInLinkedList {
    public static void main(String[] args) {
        Node node = new Node(5);
        node.next = new Node(6);
        node.next.next = new Node(7);

        System.out.println(search(node,5));
        System.out.println(search(node, 8));

    }

    private static boolean search(Node node, int val) {
        Node temp = node;
        while(temp.next != null) {
            return temp.val == val;
        }
        return false;
    }
}
