package SdeSheetStackQueue.learning;

public class ImplementQueueUsingArray {
    static class QueueWithoutCycle {
        private int front, rear;
        private int[] arr;

        // Constructor to initialize the queue
        QueueWithoutCycle() {
            front = 0;
            rear = 0;
            arr = new int[100005]; // Fixed large size queue
        }

        // Function to push an element x in the queue
        void push(int x) {
            arr[rear] = x;
            rear++;
        }

        // Function to pop an element from the queue and return that element
        int pop() {
            if (front == rear) {
                return -1; // Queue is empty
            }
            int temp = arr[front];
            front++;
            return temp;
        }

        // Function to return the size of the queue
        int size() {
            return rear - front;
        }

        // Function to return the front element of the queue without removing it
        int top() {
            if (front == rear) {
                return -1; // Queue is empty
            }
            return arr[front];
        }
    }

    static class QueueWithCycle {
        private int[] arr;
        private int start, end, currSize, maxSize;

        // Constructor with default maxSize
        public QueueWithCycle() {
            this(16); // Default size of 16
        }

        // Constructor to initialize the queue with a specified maxSize
        public QueueWithCycle(int maxSize) {
            this.maxSize = maxSize;
            arr = new int[maxSize];
            start = -1;
            end = -1;
            currSize = 0;
        }

        // Function to push an element in the queue
        public void push(int newElement) {
            if (currSize == maxSize) {
                System.out.println("Queue is full\nExiting...");
                System.exit(1);
            }
            if (end == -1) {
                start = 0;
                end = 0;
            } else {
                end = (end + 1) % maxSize;
            }
            arr[end] = newElement;
            System.out.println("The element pushed is " + newElement);
            currSize++;
        }

        // Function to pop an element from the queue and return that element
        public int pop() {
            if (start == -1) {
                System.out.println("Queue Empty\nExiting...");
                System.exit(1);
            }
            int popped = arr[start];
            if (currSize == 1) {
                start = -1;
                end = -1;
            } else {
                start = (start + 1) % maxSize;
            }
            currSize--;
            return popped;
        }

        // Function to return the front element of the queue without removing it
        public int top() {
            if (start == -1) {
                System.out.println("Queue is Empty");
                System.exit(1);
            }
            return arr[start];
        }

        // Function to return the size of the queue
        public int size() {
            return currSize;
        }
    }

    public static void main(String[] args) {
        // Test non-cyclic queue
        QueueWithoutCycle myQueue = new QueueWithoutCycle();
        myQueue.push(10);
        myQueue.push(20);
        myQueue.push(30);
        System.out.println("Non-Cyclic Queue Top: " + myQueue.top()); // Expected: 10
        System.out.println("Non-Cyclic Queue Size: " + myQueue.size()); // Expected: 3
        System.out.println("Non-Cyclic Queue Pop: " + myQueue.pop()); // Expected: 10
        System.out.println("Non-Cyclic Queue Pop: " + myQueue.pop()); // Expected: 20
        System.out.println("Non-Cyclic Queue Size: " + myQueue.size()); // Expected: 1

        // Test cyclic queue
        QueueWithCycle cyclicQueue = new QueueWithCycle(3);
        cyclicQueue.push(100);
        cyclicQueue.push(200);
        cyclicQueue.push(300);
        System.out.println("Cyclic Queue Top: " + cyclicQueue.top()); // Expected: 100
        System.out.println("Cyclic Queue Size: " + cyclicQueue.size()); // Expected: 3
        System.out.println("Cyclic Queue Pop: " + cyclicQueue.pop()); // Expected: 100
        cyclicQueue.push(400);
        System.out.println("Cyclic Queue Top: " + cyclicQueue.top()); // Expected: 200
        System.out.println("Cyclic Queue Size: " + cyclicQueue.size()); // Expected: 3
    }
}
