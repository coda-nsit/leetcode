# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findNodeParent(self, root, k, parent, isLeft):
        if root is None:
            return None, None, None
        if root.val == k:
            return parent, root, isLeft
        if root.val > k:
            return self.findNodeParent(root.left, k, root, True)
        if root.val < k:
            return self.findNodeParent(root.right, k, root, False)
            
    
    def findLargestNode(self, parentNode, node, isLeft):
        while node.right is not None:
            parentNode = node
            node = node.right
            isLeft = False
        return parentNode, node, isLeft
    
    def deleteNodeimple(self, nodeParent, node, isLeft):
        if node.left is None:
            if isLeft is True:
                nodeParent.left = node.right
            else:
                nodeParent.right = node.right
        if node.right is None:
            if isLeft is True:
                nodeParent.left = node.left
            else:
                nodeParent.right = node.left
    
    def deleteNode(self, root: Optional[TreeNode], key: int) -> Optional[TreeNode]:
        nodeParent, node, isLeft = self.findNodeParent(root, key, None, False)
        # node not found
        if node == None:
            return root
        
        # delete root node with one or 0 children
        elif nodeParent is None and (node.left is None or node.right is None):
            if root.left is None and root.right is None:
                return None
            elif node.left is None:
                return root.right
            else:
                return root.left
        
        # non root node has to be deleted which has zero or one child
        elif node.left is None or node.right is None:
            self.deleteNodeimple(nodeParent, node, isLeft)
        
        # node with 2 children has to be deleted
        else:
            inorderPredParent, inorderPred, inorderPredIsLeft = self.findLargestNode(node, node.left, True)
            inorderPred.val, node.val = node.val, inorderPred.val
            self.deleteNodeimple(inorderPredParent, inorderPred, inorderPredIsLeft)
        return root
            
