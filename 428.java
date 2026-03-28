/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

/**
serialize creates a string like: "nodeVal,numChildren nodeVal,numChildren nodeVal,numChildren"

deserialize: 
explained with an example, "1,4 2,0 3,2 4,1 5,2 6,0 7,1 8,1 9,1 10,0 11,1 12,0 13,0 14,0"
Summary:
1. maintain a bfsQ<Node>
2. when a node enters the bfsQ, its not fully formed i.e. the val is set but children is an empty list
3. while bfsQ is not empty
    1. get current root
    2. form the current rot fully i.e. populate children list by iterating through the serialized string
    3. put the children nodes which are not not fully formed into the queue
To traverse the string we maintain an iterator called serializedStringItr which keeps moving forward and picks nodes from the string
 */

class Codec {
    private StringBuilder serialized;

    public Codec() {
        serialized = new StringBuilder();
    }

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        Queue<Node> bfsQ = new ArrayDeque<>();
        bfsQ.add(root);
        while (bfsQ.size() > 0) {
            var currentNode = bfsQ.poll();
            serialized.append(Integer.toString(currentNode.val));
            serialized.append(",");
            serialized.append(Integer.toString(currentNode.children.size()));
            serialized.append(" ");
            for (var child: currentNode.children) {
                bfsQ.add(child);
            }
        }
        return serialized.toString();
    }
	
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        // 1,4 2,0 3,2 4,1 5,2 6,0 7,1 8,1 9,1 10,0 11,1 12,0 13,0 14,0
        List<String> parsedData = Arrays.stream(data.split(" ")).collect(Collectors.toList());

        // When a node goes into the bfsQ its half baked i.e. the "children" is empty list
        // We pop the node and give "children" the correct value
        Queue<Node> bfsQ = new ArrayDeque<>();

        // when we pop Node(1), forwardItr moves till 5,2 i.e. till node 1's children.
        Integer serializedStringItr, currentNodeValue, currentNodeNumChildren, currentChildNodeValue, currentChildNodeNumChildren;
        Node root, currentNode, childNode;
        List<String> nodeData;

        serializedStringItr = 0;
        root = null;

        System.out.println("parsedData:" + parsedData);

        // put the root node in the bfsQ. Note its half baked when it goes into the queue i.e. children is empty list
        nodeData = Arrays.stream(parsedData.get(0).split(",")).collect(Collectors.toList());
        currentNodeValue = Integer.valueOf(nodeData.get(0));
        currentNodeNumChildren = Integer.valueOf(nodeData.get(1));
        root = currentNode = new Node(currentNodeValue, Arrays.asList(new Node[currentNodeNumChildren]));
        bfsQ.add(root);

        while (bfsQ.size() > 0) {
            currentNode = bfsQ.poll();
            currentNodeNumChildren = currentNode.children.size();

            for (int i = 0; i < currentNodeNumChildren; ++i) {
                serializedStringItr += 1;
                nodeData = Arrays.stream(parsedData.get(serializedStringItr).split(",")).collect(Collectors.toList());
                currentChildNodeValue = Integer.valueOf(nodeData.get(0));
                currentChildNodeNumChildren = Integer.valueOf(nodeData.get(1));
                childNode = new Node(currentChildNodeValue, Arrays.asList(new Node[currentChildNodeNumChildren]));
                currentNode.children.set(i, childNode);
                bfsQ.add(childNode);
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
