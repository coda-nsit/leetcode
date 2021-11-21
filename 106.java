/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int globalIterator;
    
    private TreeNode buildTree(int[] inorder, int[] postorder, Map<Integer, Integer> valueToIndex, int start, int end) {
        if (start > end || this.globalIterator < 0) {
            return null;
        }
        
        int currValue = postorder[this.globalIterator];
        this.globalIterator -= 1;
        TreeNode node = new TreeNode(currValue);
        node.right = buildTree(inorder, postorder, valueToIndex, valueToIndex.get(currValue) + 1, end);
        node.left = buildTree(inorder, postorder, valueToIndex, start, valueToIndex.get(currValue) - 1);
        return node;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.globalIterator = postorder.length - 1;
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i) {
            valueToIndex.put(inorder[i], i);
        }
        
        return buildTree(inorder, postorder, valueToIndex, 0, inorder.length - 1);
    }
}
