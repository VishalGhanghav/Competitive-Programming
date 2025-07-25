package SdeSheetLinkedList.MediumProblemsDLL;

import SdeSheetLinkedList.DoublyNode;

import java.util.ArrayList;

public class PairsWithGivenSumDLL {
    public static ArrayList<ArrayList<Integer>> brute(int target, DoublyNode head) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        DoublyNode temp1 = head;
        while (temp1 != null) {
            DoublyNode temp2 = temp1.next;
            while (temp2 != null && (temp1.val + temp2.val) <= target) {
                int sum = temp1.val + temp2.val;
                if (sum == target) {
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(temp1.val);
                    pair.add(temp2.val);
                    res.add(pair);
                }
                temp2 = temp2.next;
            }
            temp1 = temp1.next;
        }
        return res;
    }

    public static ArrayList<ArrayList<Integer>> optimal(int target, DoublyNode head) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        DoublyNode left = head;
        DoublyNode right = getTail(head);

        while (left != null && right != null && left.val < right.val) {
            int sum = left.val + right.val;
            if (sum == target) {
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(left.val);
                pair.add(right.val);
                res.add(pair);

                left = left.next;
                right = right.prev;
            } else if (sum > target) {
                right = right.prev;
            } else {
                left = left.next;
            }
        }
        return res;
    }

    private static DoublyNode getTail(DoublyNode head) {
        DoublyNode curr = head;
        while (curr != null && curr.next != null) {
            curr = curr.next;
        }
        return curr;
    }

    public static void main(String[] args) {
        // Create DLL: 1 <-> 2 <-> 3 <-> 4 <-> 9
        DoublyNode head = new DoublyNode(1);
        head.next = new DoublyNode(2);
        head.next.prev = head;
        head.next.next = new DoublyNode(3);
        head.next.next.prev = head.next;
        head.next.next.next = new DoublyNode(4);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new DoublyNode(9);
        head.next.next.next.next.prev = head.next.next.next;

        int target = 5;

        ArrayList<ArrayList<Integer>> res1 = brute(target, head);
        for (ArrayList<Integer> pair : res1) {
            System.out.println(pair.get(0) + " " + pair.get(1));
        }

        ArrayList<ArrayList<Integer>> res2 = optimal(target, head);
        for (ArrayList<Integer> pair : res2) {
            System.out.println(pair.get(0) + " " + pair.get(1));
        }
    }
}
