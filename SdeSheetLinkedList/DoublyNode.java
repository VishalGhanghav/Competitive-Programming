package SdeSheetLinkedList;

public class DoublyNode {
    public  DoublyNode next;
    public  DoublyNode prev;
    public int val;

    public DoublyNode(int val) {
        this.val = val;
    }

    // Constructor for a Node with both data, a reference to the next node, and a reference to the previous node
    public DoublyNode(int data1, DoublyNode next1, DoublyNode back1) {
        val = data1;
        next = next1;
        prev = back1;
    }

    public static DoublyNode convertArr2DLL(int[] arr) {
        // Create the head node with the first element of the array
        DoublyNode head = new DoublyNode(arr[0]);
        // Initialize 'prev' to the head node
        DoublyNode prev = head;

        for (int i = 1; i < arr.length; i++) {
            // Create a new node with data from the array and set its 'back' pointer to the previous node
            DoublyNode temp = new DoublyNode(arr[i], null, prev);
            // Update the 'next' pointer of the previous node to point to the new node
            prev.next = temp;
            // Move 'prev' to the newly created node for the next iteration
            prev = temp;
        }
        // Return the head of the doubly linked list
        return head;
    }

    public static void print(DoublyNode head) {
        while (head != null) {
            // Print the data in the current node
            System.out.print(head.val + " ");
            // Move to the next node
            head = head.next; // Move to the next node
        }
        System.out.println();
    }
}

