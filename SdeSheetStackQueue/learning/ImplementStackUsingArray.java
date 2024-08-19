package SdeSheetStackQueue.learning;

public class ImplementStackUsingArray {
    private int[] arr; // Array to store stack elements
    private int top;   // Index of the top element in the stack

    // Constructor to initialize the stack
    public ImplementStackUsingArray() {
        arr = new int[1000]; // Initialize the array with a size of 1000
        top = -1;            // Initialize top to -1 to indicate the stack is empty
    }

    // Method to push an element onto the stack
    public void push(int x) {
        // Increment the top index and add the element to the top of the stack
        top++;
        arr[top] = x;
    }

    // Method to pop an element from the stack
    public int pop() {
        // Check if the stack is empty before popping
        if (top == -1) {
            return -1; // Return -1 if the stack is empty
        } else {
            int res = arr[top]; // Store the top element to return it
            top--;              // Decrement the top index
            return res;         // Return the popped element
        }
    }

    // Method to get the current size of the stack
    public int size() {
        return top + 1; // Size is top index + 1
    }

    // Method to get the top element of the stack without removing it
    public int top() {
        return arr[top]; // Return the element at the top of the stack
    }

    // Main method to test the MyStack class
    public static void main(String[] args) {
        ImplementStackUsingArray stack = new ImplementStackUsingArray(); // Create a new stack

        // Push elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Print the top element
        System.out.println("Top element is: " + stack.top()); // Expected: 30

        // Pop elements from the stack
        System.out.println("Popped element is: " + stack.pop()); // Expected: 30
        System.out.println("Popped element is: " + stack.pop()); // Expected: 20

        // Print the current size of the stack
        System.out.println("Current size of stack is: " + stack.size()); // Expected: 1

        // Pop the last element and print it
        System.out.println("Popped element is: " + stack.pop()); // Expected: 10

        // Try popping from an empty stack
        System.out.println("Popped element from empty stack: " + stack.pop()); // Expected: -1
    }
}
