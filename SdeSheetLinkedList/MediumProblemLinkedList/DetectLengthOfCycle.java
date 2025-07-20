package SdeSheetLinkedList.MediumProblemLinkedList;

import SdeSheetLinkedList.Node;

import java.util.HashMap;
import java.util.Map;

public class DetectLengthOfCycle {
    // Brute
    public static int countNodesinLoopBrute(Node head) {
        // code here.
        //Brute
        /*Map<Node,Integer> map = new HashMap<>();
        Node temp = head;
        int cnt = 0;
        while(temp!=null) {
            if(map.containsKey(temp)) {
                //cycle detected count length
                int len = cnt - map.get(temp);
                return len;
            } else {
                map.put(temp, cnt);
            }
            temp = temp.next;
            cnt++;
        }
        return 0;*/

        Map<Node, Integer> map = new HashMap<>();
        Node temp = head;
        int cnt = 0;
        while (temp != null) {
            if (map.containsKey(temp)) {
                int len = cnt - map.get(temp);
                return len;
            } else {
                map.put(temp, cnt);
            }
            temp = temp.next;
            cnt++;
        }
        return 0;
    }

    // Optimal
    public static int countNodesinLoopOptimal(Node head) {
        //Optimal
        Node slow = head;
        Node fast = head;
        int cnt = 1;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                //cycle detected count length
                slow = slow.next;
                while (slow != fast) {
                    slow = slow.next;
                    cnt++;
                }
                return cnt;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        // Create cycle: 1 -> 2 -> 3 -> 4 -> 5 -> 3 ...
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);
        node.next.next.next.next.next = node.next.next; // cycle starts at node 3

        int lenBrute = countNodesinLoopBrute(node);
        System.out.println("Brute Loop Length: " + lenBrute);

        // Recreate list again for independent test
        node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(5);
        node.next.next.next.next.next = node.next.next; // cycle starts at node 3

        int lenOptimal = countNodesinLoopOptimal(node);
        System.out.println("Optimal Loop Length: " + lenOptimal);
    }
}
