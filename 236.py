"""
findLowestCommonAncestor return:
root, False if root is p or q
root, True if root is the LCA
None, False if subtree or the root doesn't contain p or q
l, False if left subtree contains p or q
r, False if right subtree contains p or q
"""

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def findLowestCommonAncestor(self, root, p, q):
        if root is None:
            return None, False
        
        l, llca = self.findLowestCommonAncestor(root.left, p, q)
        r, rlca = self.findLowestCommonAncestor(root.right, p, q)
        
        if llca:
            return l, llca
        
        if rlca:
            return r, rlca
        
        if (l == p and r == q) or (l == q and r == p):
            return root, True
        
        if root == p or root == q:
            return root, False
        
        if l:
            return l, False
        if r:
            return r, False
        
        return None, False
    
        
        
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        return self.findLowestCommonAncestor(root, p, q)[0]
