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
// Simple inorder traversal
class BSTIterator {

    private TreeNode it;
    private Stack<TreeNode> inorderTracker;
    
    // Sets the invariant needed for next() to operate i.e. when next() enters it should have the least element on top
    public BSTIterator(TreeNode root) {
        this.it = root;
        this.inorderTracker = new Stack<>();
        
        while (this.it != null) {
            this.inorderTracker.push(this.it);
            this.it = this.it.left;
        }
    }
    
    // the invariant is,
    // 1. whenever we ENTER this function the stack should have the lowest element on the top. 
    // 2. whenever we EXIT this function the stack should have the lowest element on the top. 
    public int next() {
        TreeNode nextNode = this.inorderTracker.pop();
        int nextVal = nextNode.val;
        if (nextNode.right != null) {
            nextNode = nextNode.right;
            while (nextNode != null) {
                this.inorderTracker.push(nextNode);
                nextNode = nextNode.left;
            }
        }
        return nextVal;
    }
    
    public boolean hasNext() {
        return this.inorderTracker.size() > 0;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
