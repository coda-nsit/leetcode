/**
Copied core logic from AI. No need to use head. Just explain the algo and start coding. Explain the code while writing it.
Implemented the concurrrency logic myself.
 */
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class DoublyLinkedNode {
    int key;                 
    int value;               
    DoublyLinkedNode prev;   
    DoublyLinkedNode next;

    public DoublyLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}


/**
 * 1. Most recently used items are placed at the tail.
 * 2. Least recently used item is at head.next and is evicted when capacity is exceeded.
 */
class LRUCache {
    private final HashMap<Integer, DoublyLinkedNode> cacheMap;

    private final int capacity;
    public final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
    public final ReentrantReadWriteLock.WriteLock reentrantWriteLock = reentrantReadWriteLock.writeLock();
    public final ReentrantReadWriteLock.ReadLock reentrantReadLock = reentrantReadWriteLock.readLock();

    // Sentinel head and tail nodes to simplify list operations
    private final DoublyLinkedNode headSentinel;
    private final DoublyLinkedNode tailSentinel;

    
    // Initialize the LRU cache with the given capacity.
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();

        // Create sentinel nodes to avoid null checks when adding/removing nodes
        headSentinel = new DoublyLinkedNode(-1, -1);
        tailSentinel = new DoublyLinkedNode(-1, -1);

        // Link sentinels together: head <-> tail
        headSentinel.next = tailSentinel;
        tailSentinel.prev = headSentinel;
    }

    // Add a node right before the tail sentinel.
    private void addNodeToTail(DoublyLinkedNode node) {
        DoublyLinkedNode prevNode = tailSentinel.prev;

        // Connect prevNode <-> node
        prevNode.next = node;
        node.prev = prevNode;

        // Connect node <-> tailSentinel
        node.next = tailSentinel;
        tailSentinel.prev = node;
    }

    // Remove a node from its current position in the doubly linked list.
    // Works for any node except the sentinel nodes.
    private void removeNode(DoublyLinkedNode node) {
        DoublyLinkedNode prevNode = node.prev;
        DoublyLinkedNode nextNode = node.next;

        // Delete the node
        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        // Clear node pointers to avoid accidental reuse and garbage collection
        node.prev = null;
        node.next = null;
    }


    // Move an existing node to the tail to mark it as most recently used.
    private void moveToTail(DoublyLinkedNode node) {
        removeNode(node);
        addNodeToTail(node);
    }

    
    // Retrieve a value by key.
    // If the key exists, move the node to the tail and return its value.
    // If the key does not exist, return -1.
    public int get(int key) {
        try {
            reentrantReadLock.lock();
            if (!cacheMap.containsKey(key)) {
                return -1;
            }

            DoublyLinkedNode node = cacheMap.get(key);
            moveToTail(node);
            return node.value;
        } finally {
            reentrantReadLock.unlock();
        }
    }

    // Insert or update a key-value pair.
    // If the key already exists, update the value and mark it as most recently used.
    // If the key is new, create a node and add it to the tail.
    // If adding the new node exceeds capacity, evict the least recently used node at headSentinel.next.
    public void put(int key, int value) {
        try {
            reentrantWriteLock.lock();
            // Update existing node and move it to tail
            if (cacheMap.containsKey(key)) {
                DoublyLinkedNode existingNode = cacheMap.get(key);
                existingNode.value = value;
                moveToTail(existingNode);
                return;
            }

            // Create new node and add to tail
            DoublyLinkedNode newNode = new DoublyLinkedNode(key, value);
            addNodeToTail(newNode);
            cacheMap.put(key, newNode);

            // Evict least recently used node if capacity exceeded
            if (cacheMap.size() > capacity) {
                DoublyLinkedNode lruNode = headSentinel.next; // first real node
                removeNode(lruNode);
                cacheMap.remove(lruNode.key);
            }
        } finally {
            reentrantWriteLock.unlock();
        }
    }
}

/*
Example usage:

LRUCache cache = new LRUCache(3);
cache.put(1, 100);
cache.put(2, 200);
cache.put(3, 300);
cache.get(2);        // accesses key 2, moves it to most recently used
cache.put(4, 400);   // evicts key 1 which is least recently used
*/
