/**
1. Standard HashMap algo with a array + DLL
2. In the Dll I create a dummy head and tail to ease edge cases.
3. Look at the code, its self explainatory
4. The array size is max randomMod (643) after which there will be collisions.
 */
class DllNode {
    public int key;
    public int value;
    public DllNode left;
    public DllNode right;

    public DllNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

class MyHashMap {
    private Integer randomMod = 643;
    private DllNode[] hashMap;

    public MyHashMap() {
        hashMap = new DllNode[randomMod];

        // create dummy head and tail to avoid head = null & tail = null cases
        for (int i = 0; i < randomMod; ++i) {
            var headNode = new DllNode(-1, -1);
            var tailNode = new DllNode(-1, -1);
            
            // hook the head and tail nodes
            hashMap[i] = headNode;
            hashMap[i].right = tailNode;
            tailNode.left = hashMap[i];
        }
    }

    public void traverseList(String operation, int key) {
        System.out.printf("operation:%s; key:%d\n", operation, key);
        DllNode dllNode =  hashMap[key % randomMod];
        while (dllNode != null) {
            System.out.printf("%d %d;\t", dllNode.key, dllNode.value);
            dllNode = dllNode.right;
        }
        System.out.println();
    }
    
    public void put(int key, int value) {
        // traverseList("put", key);
        int modKey;
        modKey = key % randomMod;
        DllNode dll = hashMap[modKey].right; // first node is head
        while (dll.key != -1) {
            if (dll.key == key) {
                dll.value = value;
                return;
            }
            dll = dll.right;
        }
        // value is not found, dll is at tail node
        // create a new tail node and overwrite the existing tail node
        var newTailNode = new DllNode(-1, -1);
        // update the old tail node to be the new node
        dll.key = key;
        dll.value = value;
        dll.right = newTailNode;
        
        // hook up the new tail node
        newTailNode.left = dll;

        // traverseList("put", key);
        // System.out.println();
        return;
    }
    
    public int get(int key) {
        // traverseList("get", key);
        // System.out.println();
        int modKey;
        modKey = key % randomMod;
        DllNode dll = hashMap[modKey];
        while (dll != null) {
            if (dll.key == key) {
                return dll.value;
            }
            dll = dll.right;
        }
        return -1;
    }
    
    public void remove(int key) {
        // traverseList("remove", key);
        int modKey;
        modKey = key % randomMod;
        DllNode dll = hashMap[modKey];
        while (dll != null) {
            if (dll.key == key) {
                // delete this node
                dll.left.right = dll.right;
                dll.right.left = dll.left;
                dll = null;
                return;
            }
            dll = dll.right;
        }
        // traverseList("remove", key);
        // System.out.println();
        return;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */