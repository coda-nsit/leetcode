class Solution {
    private int numNodes = 0;
    private void findNumGoodNodes(TreeNode root, int maxTillNow) {
        if (root == null) {
            return;
        }
        if (root.val >= maxTillNow) {
            this.numNodes += 1;
        }
        maxTillNow = Math.max(maxTillNow, root.val);
        findNumGoodNodes(root.left, maxTillNow);
        findNumGoodNodes(root.right, maxTillNow);
    } 
    public int goodNodes(TreeNode root) {
        findNumGoodNodes(root, -100005);
        return this.numNodes;
    }
}
