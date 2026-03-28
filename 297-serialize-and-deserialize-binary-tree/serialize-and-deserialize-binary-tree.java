/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Codec {

    public String serialize(TreeNode root) {
    if (root == null) {
        return "";
    }

    Queue<TreeNode> q = new LinkedList<>();     // can't use ArrayDeque as it doesn't allow null to be inserted into the queue.
    StringBuilder result = new StringBuilder();

    q.add(root);

    while (!q.isEmpty()) {
        TreeNode node = q.poll();

        if (node == null) {
            result.append("null ");
            continue;
        }

        result.append(node.val).append(" ");
        q.add(node.left);   // safe because node != null
        q.add(node.right);  // safe because node != null
    }

    return result.toString();
}

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        int idx;
        List<String> parsedData = Arrays.stream(data.split(" ")).collect(Collectors.toList());
        TreeNode root, currentNode, left, right;

        idx = 0;

        if (parsedData.get(idx).equals("null")) {
            root = null;
        } else {
            root = new TreeNode(Integer.parseInt(parsedData.get(idx)));
        }
        idx += 1;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);

        while (!q.isEmpty()) {
            currentNode = q.poll();

            if (currentNode == null) {
                continue;
            }

            // Left child
            if (!parsedData.get(idx).equals("null")) {
                left = new TreeNode(Integer.parseInt(parsedData.get(idx)));
                currentNode.left = left;
                q.add(left);
            }
            idx++;

            // Right child
            if (!parsedData.get(idx).equals("null")) {
                right = new TreeNode(Integer.parseInt(parsedData.get(idx)));
                currentNode.right = right;
                q.add(right);
            }
            idx++;
        }

        return root;
    }
}


// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));