package SdeSheetLinkedList.MediumProblemLinkedList;

import SdeSheetLinkedList.Node;

import static SdeSheetLinkedList.Node.printLinkedList;

class AddOneToLinkedList {

    public static Node addOne(Node head) {
        // code here.
        //Approach : Reverse ll.Add now at first .
        //Handle carry.After adding reverse back the ll
        // Step 1: Reverse the list
        head = reverse(head);

        Node curr = head;
        int carry = 1;

        // Step 2: Add 1 and handle carry
        while (curr != null) {
            int sum = curr.val + carry;
            curr.val = sum % 10;
            carry = sum / 10;

            // If there's no carry left, we're done
            if (carry == 0) break;

            // If at the end and carry is still left, add a new node
            if (curr.next == null && carry > 0) {
                curr.next = new Node(0); // Placeholder for carry to be added in next loop
            }

            curr = curr.next;
        }

        // Step 3: Reverse back the list
        return reverse(head);
    }

    // Helper to reverse linked list
    private static Node reverse(Node head) {
        Node prev = null;
        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(9);
        node.next.next = new Node(9);

        Node res = addOne(node);

        // Should print 2 -> 0 -> 0 if printLL is defined externally
        printLinkedList(res); // assuming printLL is imported
    }
}

