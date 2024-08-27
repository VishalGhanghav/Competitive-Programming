package SdeSheetStackQueue.MonotonicStack.Problems;

import java.util.Stack;

public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        int n = asteroids.length;

        for (int i = 0; i < n; i++) {
            // if positive always push in stack
            if (asteroids[i] > 0) {
                st.push(asteroids[i]);
            } else {
                // if negative
                // till stack is not empty and new asteroid greater than prev(st.top) keep popping
                // addded this condition. and its positive as
                //// eg.-2 -1 1 2. First we push -2. Next time when -1.-2 shouldn't get popped as they are in the same dir.
                while (!st.isEmpty() && st.peek() > 0 && st.peek() < Math.abs(asteroids[i])) {
                    st.pop();
                }
                // after popping if stack is empty, push current asteroid in stack
                // eg.-2 -1 1 2. First we push -2. Next time again -1 should also push
                // as the same left direction. So add a condition if existing is -ve. Push new in stack as well
                if (st.isEmpty() || st.peek() < 0) {
                    st.push(asteroids[i]);
                } else if (!st.isEmpty() && st.peek() == Math.abs(asteroids[i])) {
                    // if both are of the same size destroy both, i.e., pop from stack and don't add the current in stack
                    st.pop();
                }
            }
        }
        // In the end, we have the final surviving asteroids in the stack
        // we will get asteroids in reverse order after popping from stack. So
        int size = st.size();
        int ans[] = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            ans[i] = st.pop();
        }
        return ans;
    }

    // Main method to test the function
    public static void main(String[] args) {
        AsteroidCollision ac = new AsteroidCollision();

        int[] asteroids = {5, 10, -5};
        int[] result = ac.asteroidCollision(asteroids);

        System.out.print("Final state of asteroids: ");
        for (int asteroid : result) {
            System.out.print(asteroid + " ");
        }
        // Expected output: 5 10

        System.out.println();  // For a new line after the result

        int[] asteroids2 = {8, -8};
        result = ac.asteroidCollision(asteroids2);

        System.out.print("Final state of asteroids: ");
        for (int asteroid : result) {
            System.out.print(asteroid + " ");
        }
        // Expected output: (empty output, as both asteroids are destroyed)
    }
}
