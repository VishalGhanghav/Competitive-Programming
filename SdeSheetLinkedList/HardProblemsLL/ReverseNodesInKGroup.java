package SdeSheetLinkedList.HardProblemsLL;

import SdeSheetLinkedList.Node;

public class ReverseNodesInKGroup {
    public static Node optimal(Node head, int k) {
        Node temp = head;
        Node next = null, prev = null;

        while (temp != null) {
            Node knode = getKthNode(temp, k);
            // If I am at the end
            if (knode == null) {
                // If at end I need to connect prev to current temp
                if (prev != null) {
                    prev.next = temp;
                }
                break;
            }

            // To reverse I need kth's next point to null
            // Also store next as we will need in future
            next = knode.next;
            knode.next = null;

            Node reversedHead = reverse(temp);

            // Adjust the head if the reversal starts from the head
            if (temp == head) {
                head = reversedHead;
            } else {
                // Link the last node of the previous group to the reversed group
                prev.next = reversedHead;
            }

            prev = temp;
            temp = next;
        }
        return head;
    }

    private static Node reverse(Node head) {
        Node temp = head;
        Node prev = null;

        while (temp != null) {
            Node next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        return prev;
    }

    private static Node getKthNode(Node head, int k) {
        Node temp = head;
        int cnt = 0;
        while (temp != null && cnt < k - 1) {
            temp = temp.next;
            cnt++;
        }
        return temp;
    }

    public static void main(String[] args) {
        // Input: 1->2->3->4->5, k=2
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node result = optimal(head, 2);

        // Print result
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
