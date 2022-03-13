# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findRoot(self, descriptions):
        parents = [point[0] for point in descriptions]
        children = set([point[1] for point in descriptions])
        # print("parents:", parents)
        # print("children:", children)
        for parent in parents:
            if parent not in children:
                return parent
        
    def createTree(self, root, parentToChildren):
        children = parentToChildren[root.val]
        for point in children:
            c, l = TreeNode(point[0]), point[1]
            if l == 1:
                root.left = self.createTree(c, parentToChildren)
            else:
                root.right = self.createTree(c, parentToChildren)
        return root
        
            
        
    def createBinaryTree(self, descriptions: List[List[int]]) -> Optional[TreeNode]:
        root = TreeNode(self.findRoot(descriptions))
        parentToChildren = defaultdict(list)
        for point in descriptions:
            p, c, l = point
            parentToChildren[p].append([c, l])
        # print("root:", root)
        # print("parentToChildren:", parentToChildren)
        return self.createTree(root, parentToChildren)
