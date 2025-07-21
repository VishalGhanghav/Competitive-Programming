package SdeSheetLinkedList.MediumProblemLinkedList;

import SdeSheetLinkedList.Node;

public class AddTwoNumbersLinkedList {

    public static Node addTwoNumbers(Node l1, Node l2) {
        Node dummy = new Node(0);
        int carry = 0;
        Node temp = dummy;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = 0;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            sum += carry;
            carry = sum / 10;

            Node newNode = new Node(sum % 10);
            temp.next = newNode;
            temp = temp.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Node l1 = new Node(2);
        l1.next = new Node(4);
        l1.next.next = new Node(3); // represents 342

        Node l2 = new Node(5);
        l2.next = new Node(6);
        l2.next.next = new Node(4); // represents 465

        Node result = addTwoNumbers(l1, l2); // should give 807 (7 -> 0 -> 8)

        while (result != null) {
            System.out.print(result.val);
            if (result.next != null) System.out.print(" -> ");
            result = result.next;
        }
    }
}
