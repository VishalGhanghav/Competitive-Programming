package SdeSheetStackQueue.ImplementationProblems;

import java.util.HashMap;
import java.util.Map;

class LFUCache {
    class Node {
        int key;
        int val;
        Node next;
        Node prev;
        int freq;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1; // Initialize frequency as 1 for a new node
        }
    }

    class DoublyLinkedList {
        int dllSize;  // Size of the doubly linked list
        Node head;    // Dummy head of the linked list
        Node tail;    // Dummy tail of the linked list

        public DoublyLinkedList() {
            this.dllSize = 0;
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        // Insert the node right after the head
        public void addNode(Node node) {
            dllSize++;
            Node headNext = head.next;
            head.next = node;
            node.prev = head;
            node.next = headNext;
            headNext.prev = node;
        }

        // Remove the node from the list
        public void removeNode(Node node) {
            dllSize--;
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
    }

    final int capacity;    // Capacity of the cache
    int currSize;          // Current size of the cache
    int minFreq;           // Minimum frequency of nodes in the cache
    Map<Integer, Node> cache;    // Maps key to the corresponding node
    Map<Integer, DoublyLinkedList> freqMap;  // Maps frequency to the corresponding doubly linked list

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.currSize = 0;
        this.minFreq = 0;

        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node currNode = cache.get(key);
        // Node is accessed, so frequency must be updated.
        updateNode(currNode);
        return currNode.val;
    }

    private void updateNode(Node currNode) {
        int freq = currNode.freq;
        // Get the doubly linked list corresponding to the current frequency and remove the node from it.
        DoublyLinkedList dll = freqMap.get(freq);
        dll.removeNode(currNode);

        // If the current frequency was the minimum frequency and the list becomes empty after removal,
        // increase the minimum frequency.
        if (freq == minFreq && dll.dllSize == 0) {
            minFreq++;
        }

        currNode.freq++;  // Increase the node's frequency
        // Get the list for the new frequency or create a new list if it doesn't exist
        DoublyLinkedList newDll = freqMap.getOrDefault(currNode.freq, new DoublyLinkedList());
        newDll.addNode(currNode);  // Add the node to the new frequency list
        freqMap.put(currNode.freq, newDll);  // Update the frequency map
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        // If the key is already present in the cache, update its value and frequency.
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.val = value;
            updateNode(node);
        } else {
            currSize++;
            // If the cache exceeds its capacity, remove the least frequently used node.
            if (currSize > capacity) {
                DoublyLinkedList minFreqList = freqMap.get(minFreq);
                cache.remove(minFreqList.tail.prev.key);  // Remove from cache map
                minFreqList.removeNode(minFreqList.tail.prev);  // Remove from list
                currSize--;
            }

            minFreq = 1;  // New node has a minimum frequency of 1
            Node newNode = new Node(key, value);
            DoublyLinkedList currList = freqMap.getOrDefault(1, new DoublyLinkedList());
            currList.addNode(newNode);
            freqMap.put(1, currList);
            cache.put(key, newNode);
        }
    }

    // Main method to test the LFU Cache implementation
    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2); // Create a cache with capacity 2

        lfuCache.put(1, 1); // Cache is {1=1}
        lfuCache.put(2, 2); // Cache is {1=1, 2=2}
        System.out.println(lfuCache.get(1)); // Returns 1

        lfuCache.put(3, 3); // Cache is {1=1, 3=3} (2 is evicted because it is least frequently used)
        System.out.println(lfuCache.get(2)); // Returns -1 (2 is not found)
        System.out.println(lfuCache.get(3)); // Returns 3

        lfuCache.put(4, 4); // Cache is {3=3, 4=4} (1 is evicted)
        System.out.println(lfuCache.get(1)); // Returns -1 (1 is not found)
        System.out.println(lfuCache.get(3)); // Returns 3
        System.out.println(lfuCache.get(4)); // Returns 4
    }
}
