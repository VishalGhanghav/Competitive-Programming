package SdeSheetStackQueue.learning;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {
    Queue<Integer> q;

    // Constructor initializes the queue
    public StackUsingQueue() {
        q = new LinkedList<>();
    }

    // Push method to add an element to the stack
    public void push(int x) {
        // Add new element at the end of the queue
        q.add(x);
        // Rotate the elements to maintain stack order
        for (int i = 0; i < q.size() - 1; i++) {
            q.add(q.poll());
        }
    }

    // Pop method to remove the top element from the stack
    public int pop() {
        return q.poll();
    }

    // Top method to get the top element without removing it
    public int top() {
        return q.peek();
    }

    // Empty method to check if the stack is empty
    public boolean empty() {
        return q.isEmpty();
    }

    // Main method to test the MyStack implementation
    public static void main(String[] args) {
        StackUsingQueue stack = new StackUsingQueue();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Top element: " + stack.top()); // Should print 3
        System.out.println("Popped element: " + stack.pop()); // Should print 3
        System.out.println("Top element after pop: " + stack.top()); // Should print 2
        System.out.println("Is stack empty? " + stack.empty()); // Should print false
        stack.pop();
        stack.pop();
        System.out.println("Is stack empty after popping all elements? " + stack.empty()); // Should print true
    }
}
