package SdeSheetStackQueue.learning;

import java.util.Stack;

class MinStackUsingVariable {

    Stack<Long> st = new Stack<>(); // Stack to store elements
    Long mini; // Variable to track the minimum value in the stack

    public MinStackUsingVariable() {
        mini = Long.MAX_VALUE; // Initialize mini to the largest possible value
    }

    public void push(int value) {
        Long val = Long.valueOf(value); // Convert the integer value to Long for consistency
        if (st.isEmpty()) {
            mini = val; // If the stack is empty, set mini to the value
            st.push(val); // Push the value onto the stack
        } else {
            if (val < mini) {
                // If the value is less than the current minimum, store a modified value
                st.push(2 * val - mini);
                mini = val; // Update the minimum to the new value
            } else {
                st.push(val); // Otherwise, push the value as is
            }
        }
    }

    public void pop() {
        if (st.isEmpty()) return; // If the stack is empty, do nothing

        Long val = st.pop(); // Pop the top value from the stack
        if (val < mini) {
            // If the popped value is less than the current minimum,
            // it means the actual value was altered during push
            mini = 2 * mini - val; // Restore the previous minimum value
        }
    }

    public int top() {
        Long val = st.peek(); // Peek at the top value of the stack
        if (val < mini) {
            // If the top value is less than the current minimum, it means the actual value was altered
            return mini.intValue(); // Return the current minimum as the top value
        }
        return val.intValue(); // Otherwise, return the top value
    }

    public int getMin() {
        return mini.intValue(); // Return the current minimum value in the stack
    }

    public static void main(String[] args) {
        MinStackUsingVariable minStack = new MinStackUsingVariable();

        // Test cases to validate the MinStack functionality
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
}

