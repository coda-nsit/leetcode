/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Serialize: simple preorder traversal
// Deserialize: construct BST from preorder traversal i.e. 
// if root has min=x,  max=y and root value=k then,
// left children should all be x,k
// right children should all be k+1,y
// if the current index doesnt satisfy this constrain then return null
public class Codec {
    
    private int index;
    
    Codec() {
        this.index = 0;
    }
    
    private void serialize(TreeNode root, StringBuilder serializedStringBuilder) {
        if (root == null) {
            return;
        }
        serializedStringBuilder.append(root.val);
        serializedStringBuilder.append(" ");
        serialize(root.left, serializedStringBuilder);
        serialize(root.right, serializedStringBuilder);
        return;
    }
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder serializedStringBuilder = new StringBuilder();
        serialize(root, serializedStringBuilder);
        return serializedStringBuilder.toString();
    }
    
    private TreeNode deserialize(int[] numbers, int low, int high) {
        if (this.index == numbers.length) {
            return null;
        }
        
        // System.out.println(numbers[this.index] + " " + low + " " + high);
        
        if (numbers[this.index] >= low && numbers[this.index] < high) {
            TreeNode root = new TreeNode(numbers[this.index]);
            this.index += 1;
            root.left = deserialize(numbers, low, root.val);
            root.right = deserialize(numbers, root.val + 1, high);
            return root;
        }
        return null;
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        int[] numbers = Arrays.stream(data.split(" ")).mapToInt(Integer::parseInt).toArray(); 
        this.index = 0;
        return deserialize(numbers, -1, 10001);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;
