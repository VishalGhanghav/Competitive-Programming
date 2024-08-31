package SdeSheetStackQueue.ImplementationProblems;
import java.util.HashMap;
import java.util.Map;

class LRUCache {
    class Node {
        int key;
        int val;
        Node next;
        Node prev;

        Node(int key, int value) {
            this.key = key;
            this.val = value;
            next = prev = null;
        }
    }

    Map<Integer, Node> map = new HashMap<>();
    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        // We will have to take the node and make it recently used
        removeNode(node);
        insertAfterHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        // If the key is already present, update its value and move it to the head
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value; // Update the value of the existing node
            removeNode(node);
            insertAfterHead(node);
        } else {
            // If the LRU cache is full, remove the last used node
            if (map.size() == capacity) {
                map.remove(tail.prev.key); // Remove the LRU node from the map
                removeNode(tail.prev); // Remove the LRU node from the linked list
            }
            // Insert the new node at the head
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            insertAfterHead(newNode);
        }
    }

    private void removeNode(Node node) {
        // Connect prevNode to next and vice versa. This way we cut off all links
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void insertAfterHead(Node node) {
        Node currAfterHead = head.next;
        head.next = node;
        node.next = currAfterHead;
        node.prev = head;
        currAfterHead.prev = node;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2); // capacity of 2

        // Testing put and get methods
        lruCache.put(1, 1); // cache is {1=1}
        lruCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lruCache.get(1));    // returns 1, cache is {2=2, 1=1}
        lruCache.put(3, 3);    // LRU key 2 is evicted, cache is {1=1, 3=3}
        System.out.println(lruCache.get(2));    // returns -1 (not found)
        lruCache.put(4, 4);    // LRU key 1 is evicted, cache is {3=3, 4=4}
        System.out.println(lruCache.get(1));    // returns -1 (not found)
        System.out.println(lruCache.get(3));    // returns 3, cache is {4=4, 3=3}
        System.out.println(lruCache.get(4));    // returns 4, cache is {3=3, 4=4}
    }
}

