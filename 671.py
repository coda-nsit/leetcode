# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
from queue import Queue
class Solution:
    def findSecondMinimumValue(self, root: Optional[TreeNode]) -> int:
        mini1, mini2 = float("inf"), float("inf")
        bfsQ = Queue()
        bfsQ.put(root)
        while bfsQ.empty() is False:
            root = bfsQ.get()
            if root is None:
                continue
            # All the children also need to be looked. But only update the mini1 and mini2 if we have found a lower value.
            if root.val <= mini1:
                if root.val < mini1:
                    mini2 = mini1
                    mini1 = root.val
                bfsQ.put(root.left)
                bfsQ.put(root.right)
            # No need to look at the children nodes as they will definitely be bigger
            elif mini1 < root.val < mini2:
                mini2 = root.val
                bfsQ.put(root.left)
                bfsQ.put(root.right)
            else:
                continue
        if mini2 == float("inf"):
            return -1
        return mini2
        
