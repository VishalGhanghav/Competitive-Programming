package SdeSheetStackQueue.learning;

import java.util.Stack;


    // MinStack class to implement a stack that supports push, pop, top, and retrieving the minimum element in constant time
    class MinStackUsingPair {
        // Stack to hold pairs of values and their corresponding minimums
        Stack<Pair> s;

        // Constructor to initialize the stack
        public MinStackUsingPair() {
            s = new Stack<>();
        }

        // Method to push a value onto the stack
        public void push(int val) {
            if (s.isEmpty()) {
                // If the stack is empty, the first value is also the minimum
                s.push(new Pair(val, val));
            } else {
                // Otherwise, the minimum is the lesser of the new value and the current minimum
                int mini = Math.min(val, s.peek().second);
                s.push(new Pair(val, mini));
            }
        }

        // Method to pop the top value from the stack
        public void pop() {
            s.pop();
        }

        // Method to get the top value of the stack without removing it
        public int top() {
            return s.peek().first;
        }

        // Method to retrieve the current minimum value in the stack
        public int getMin() {
            return s.peek().second;
        }

        // Main method to test the MinStack class
        public static void main(String[] args) {
            MinStackUsingPair minStack = new MinStackUsingPair();

            // Test cases
            minStack.push(5);
            minStack.push(3);
            minStack.push(7);
            minStack.push(2);

            System.out.println("Top: " + minStack.top()); // Expected output: 2
            System.out.println("Min: " + minStack.getMin()); // Expected output: 2

            minStack.pop();
            System.out.println("Top after pop: " + minStack.top()); // Expected output: 7
            System.out.println("Min after pop: " + minStack.getMin()); // Expected output: 3

            minStack.pop();
            System.out.println("Min after second pop: " + minStack.getMin()); // Expected output: 3

            minStack.push(1);
            System.out.println("Min after pushing 1: " + minStack.getMin()); // Expected output: 1
        }

        class Pair {
            int first;
            int second;

            // Constructor to initialize the pair
            Pair(int first, int second) {
                this.first = first;
                this.second = second;
            }
        }
    }


