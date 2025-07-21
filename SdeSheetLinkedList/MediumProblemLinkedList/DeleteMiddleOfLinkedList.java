package SdeSheetLinkedList.MediumProblemLinkedList;

import SdeSheetLinkedList.Node;

public class DeleteMiddleOfLinkedList {

    // ? Brute Force
    public static Node deleteMiddleBrute(Node head) {
        Node temp = head;
        int cnt = 0;
        if (head.next == null) {
            return head.next;
        }
        while (temp != null) {
            temp = temp.next;
            cnt++;
        }
        int res = cnt / 2;
        temp = head;
        for (int i = 0; i < res - 1; i++) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
        return head;
    }

    // ? Optimal using slow/fast pointers
    public static Node deleteMiddleOptimal(Node head) {
        if (head == null || head.next == null) return null;
        Node slow = head;
        Node fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public static void main(String[] args) {
        // Input: 1 -> 2 -> 3 -> 4 -> 5 ? null
        Node input1 = new Node(1);
        input1.next = new Node(2);
        input1.next.next = new Node(3);
        input1.next.next.next = new Node(4);
        input1.next.next.next.next = new Node(5);

        Node input2 = new Node(1);
        input2.next = new Node(2);
        input2.next.next = new Node(3);
        input2.next.next.next = new Node(4);
        input2.next.next.next.next = new Node(5);

        Node resBrute = deleteMiddleBrute(input1);
        Node resOptimal = deleteMiddleOptimal(input2);

        System.out.print("Brute: ");
        for (Node cur = resBrute; cur != null; cur = cur.next) {
            System.out.print(cur.val + " ");
        }
        System.out.println();

        System.out.print("Optimal: ");
        for (Node cur = resOptimal; cur != null; cur = cur.next) {
            System.out.print(cur.val + " ");
        }
        System.out.println();
    }
}
