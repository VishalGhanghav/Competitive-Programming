package SdeSheetStackQueue.learning;

import java.util.Stack;

public class QueueUsingStacks {
    Stack<Integer> s1;
    Stack<Integer> s2;

    // Constructor initializes the two stacks
    public QueueUsingStacks() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    // Push method to add an element to the queue
    public void push(int x) {
        // Move all elements from s1 to s2
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        // Push the new element onto s1
        s1.push(x);
        // Move all elements back from s2 to s1
        // This ensures that the oldest element is always on top of s1
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    // Pop method to remove the front element from the queue
    public int pop() {
        return s1.pop();
    }

    // Peek method to get the front element without removing it
    public int peek() {
        return s1.peek();
    }

    // Empty method to check if the queue is empty
    public boolean empty() {
        return s1.isEmpty();
    }

    // Main method to test the MyQueue implementation
    public static void main(String[] args) {
        QueueUsingStacks queue = new QueueUsingStacks();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println("Front element: " + queue.peek()); // Should print 1
        System.out.println("Popped element: " + queue.pop()); // Should print 1
        System.out.println("Front element after pop: " + queue.peek()); // Should print 2
        System.out.println("Is queue empty? " + queue.empty()); // Should print false
        queue.pop();
        queue.pop();
        System.out.println("Is queue empty after popping all elements? " + queue.empty()); // Should print true
    }
}
