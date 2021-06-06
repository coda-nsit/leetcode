"""
# Definition for a QuadTree node.
class Node:
    def __init__(self, val, isLeaf, topLeft, topRight, bottomLeft, bottomRight):
        self.val = val
        self.isLeaf = isLeaf
        self.topLeft = topLeft
        self.topRight = topRight
        self.bottomLeft = bottomLeft
        self.bottomRight = bottomRight
"""
import copy
class Solution:
    def construct(self, grid: List[List[int]]) -> 'Node':
        m, n = len(grid), len(grid[0])
        
        # base case: only one cell in grid
        if m == 1 and n == 1:
            return Node(grid[0][0], True, None, None, None, None)
        # base case: all elements are the same
        allSame = True
        for i in range(m):
            for j in range(n):
                if grid[i][j] != grid[0][0]:
                    allSame = False
                    break
        if allSame is True:
            return Node(grid[0][0], True, None, None, None, None)
        
        # break the grid into 4 parts
        topLeftList, topRightList, bottomLeftList, bottomRightList = [], [], [], []
        for i in range(m // 2):
            row = []
            for j in range(n // 2):
                row.append(grid[i][j])
            topLeftList.append(copy.copy(row))
        for i in range(m // 2):
            row = []
            for j in range(n // 2, n):
                row.append(grid[i][j])
            topRightList.append(copy.copy(row))
        for i in range(m // 2, m):
            row = []
            for j in range(n // 2):
                row.append(grid[i][j])
            bottomLeftList.append(copy.copy(row))
        for i in range(m // 2, m):
            row = []
            for j in range(n // 2, n):
                row.append(grid[i][j])
            bottomRightList.append(copy.copy(row))
        
        topLeft = self.construct(topLeftList)
        topRight = self.construct(topRightList)
        bottomLeft = self.construct(bottomLeftList)
        bottomRight = self.construct(bottomRightList)
        return Node(0, False, topLeft, topRight, bottomLeft, bottomRight)
            
