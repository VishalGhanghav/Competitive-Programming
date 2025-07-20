package SdeSheetLinkedList.MediumProblemLinkedList;

import SdeSheetLinkedList.Node;

import java.util.ArrayList;
import java.util.List;

public class DetectPalindromeInLinkedList {
    // Brute
    public static boolean isPalindromeBrute(Node head) {
        //Approach: Store list in array and compare
        List<Integer> list = new ArrayList<>();
        Node temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }

        int i = 0, j = list.size() - 1;
        while (i < j) {
            if (!list.get(i).equals(list.get(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // Optimal
    public static boolean isPalindromeOptimal(Node head) {
        //Approach: 1.Find middle of linked list
        //2.reverse other half.3.compare 1st and second reversed half
        if (head == null || head.next == null) {
            return true;
        }

        //Find middle
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //Now slow is at mid
        slow = reverseSecondHalf(slow);
        while (slow != null) {
            if (head.val != slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

    private static Node reverseSecondHalf(Node head) {
        Node next = null;
        Node prev = null;

        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        // Palindrome: 1 -> 2 -> 3 -> 2 -> 1
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(2);
        node.next.next.next.next = new Node(1);

        boolean resultBrute = isPalindromeBrute(node);
        System.out.println("Brute Palindrome: " + resultBrute);

        // Recreate for independent optimal test
        node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(2);
        node.next.next.next.next = new Node(1);

        boolean resultOptimal = isPalindromeOptimal(node);
        System.out.println("Optimal Palindrome: " + resultOptimal);
    }
}
