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

/*
** mark every node as root, leftBoundary, rightBoundary, leaf, other
** root -> left child is leftBoundary, right child rightBoundary
** leftBoundary -> if left child exists its the left boundary, else right child is leftBoundary
** rightBoundary -> if right child exists its the right boundary, else left child is rightBoundary
** other -> both children are other
*/
// loccapollo
class Solution {
    private static final int ROOT = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int OTHER = 3;
    
    private void getBoundaryOfBinaryTree(TreeNode root, List<Integer> leftNodes, List<Integer> rightNodes, List<Integer> leafNodes, int nodeType) {
        if (root == null) {
            return;
        }
        
        if (nodeType == ROOT) {
            getBoundaryOfBinaryTree(root.left, leftNodes, rightNodes, leafNodes, LEFT);
            getBoundaryOfBinaryTree(root.right, leftNodes, rightNodes, leafNodes, RIGHT);
            return;
        }
        
        if (root.left == null && root.right == null) {
            leafNodes.add(root.val);
            return;
        }
        
        
        if (nodeType == LEFT) {
            leftNodes.add(root.val);
            if (root.left != null) {
                getBoundaryOfBinaryTree(root.left, leftNodes, rightNodes, leafNodes, LEFT);
                getBoundaryOfBinaryTree(root.right, leftNodes, rightNodes, leafNodes, OTHER);
            } else {
                getBoundaryOfBinaryTree(root.right, leftNodes, rightNodes, leafNodes, LEFT);
            }
        } else if (nodeType == RIGHT) {
            rightNodes.add(root.val);
            if (root.right != null) {
                getBoundaryOfBinaryTree(root.left, leftNodes, rightNodes, leafNodes, OTHER);
                getBoundaryOfBinaryTree(root.right, leftNodes, rightNodes, leafNodes, RIGHT);
            } else {
                getBoundaryOfBinaryTree(root.left, leftNodes, rightNodes, leafNodes, RIGHT);
            }
        } else {
            getBoundaryOfBinaryTree(root.left, leftNodes, rightNodes, leafNodes, OTHER);
            getBoundaryOfBinaryTree(root.right, leftNodes, rightNodes, leafNodes, OTHER);
        }
        return;
    }
    
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> leftNodes = new ArrayList<>();
        List<Integer> rightNodes = new ArrayList<>();
        List<Integer> leafNodes = new ArrayList<>();
        List<Integer> answer = new ArrayList<>();
        
        getBoundaryOfBinaryTree(root, leftNodes, rightNodes, leafNodes, ROOT);
        
        answer.add(root.val);
        
        answer.addAll(leftNodes);
        
        answer.addAll(leafNodes);
        
        Collections.reverse(rightNodes);
        answer.addAll(rightNodes);
        
        return answer;
    }
}

// [1,null,2,3,4]
// [1,2,3,4,5,6,null,null,null,7,8,9,10]
// [1]
