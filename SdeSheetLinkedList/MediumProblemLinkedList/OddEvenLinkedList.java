package SdeSheetLinkedList.MediumProblemLinkedList;

import SdeSheetLinkedList.Node;

public class OddEvenLinkedList {

    // ? Brute Force: Use two dummy nodes for odd and even positions
    public static Node oddEvenListBrute(Node head) {
        if (head == null || head.next == null) return head;

        Node oddDummy = new Node(0);
        Node evenDummy = new Node(0);
        Node odd = oddDummy, even = evenDummy;

        Node temp = head;
        int idx = 1;

        while (temp != null) {
            if (idx % 2 == 1) {
                odd.next = temp;
                odd = odd.next;
            } else {
                even.next = temp;
                even = even.next;
            }
            temp = temp.next;
            idx++;
        }

        even.next = null;         // Important to terminate the even list
        odd.next = evenDummy.next;

        return oddDummy.next;
    }

    // ? Optimal Approach: Rewire pointers in-place
    public static Node oddEvenListOptimal(Node head) {
        if (head == null || head.next == null) return head;

        Node odd = head;
        Node even = head.next;
        Node evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
        // Input: 1 -> 2 -> 3 -> 4 -> 5 ? null
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);

        Node resultBrute = oddEvenListBrute(node);
        System.out.print("Brute Odd-Even List: ");
        for (Node cur = resultBrute; cur != null; cur = cur.next) {
            System.out.print(cur.val + " ");
        }
        System.out.println();

        // Recreate for independent optimal test
        node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);

        Node resultOptimal = oddEvenListOptimal(node);
        System.out.print("Optimal Odd-Even List: ");
        for (Node cur = resultOptimal; cur != null; cur = cur.next) {
            System.out.print(cur.val + " ");
        }
        System.out.println();
    }
}
