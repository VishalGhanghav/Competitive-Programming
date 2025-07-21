package SdeSheetLinkedList.MediumProblemLinkedList;

import SdeSheetLinkedList.Node;

import static SdeSheetLinkedList.Node.printLinkedList;

public class SortLinkedListOf0s1s2s {
    static Node segregate(Node head) {
        // code here
        Node zeroDummy = new Node(-1);
        Node oneDummy = new Node(-1);
        Node twoDummy = new Node(-1);

        Node zeroD = zeroDummy, oneD = oneDummy, twoD = twoDummy;

        Node curr = head;

        while(curr != null) {
            if(curr.val == 0) {
                zeroD.next = curr;
                zeroD = zeroD.next;
            }

            if(curr.val == 1) {
                oneD.next = curr;
                oneD = oneD.next;
            }

            if(curr.val == 2) {
                twoD.next = curr;
                twoD = twoD.next;
            }
            curr = curr.next;
        }

        zeroD.next = oneDummy.next != null ? oneDummy.next : twoDummy.next;
        oneD.next = twoDummy.next;
        twoD.next = null;

        head = zeroDummy.next;
        return head;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(0);
        node.next.next.next = new Node(1);
        node.next.next.next.next = new Node(2);
        node.next.next.next.next.next = new Node(0);

        Node result = segregate(node);
        printLinkedList(result);
    }
}
