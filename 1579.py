# earlier was doing using Node class but was getting RTE. So converted to list representation.
from collections import defaultdict
from copy import copy
        
PARENT = 0
SIZE = 1

class Solution:
    def findParent(self, idx, nodes):
        while nodes[idx][PARENT] != idx:
            idx = nodes[idx][PARENT]
        return idx
        
    def unite(self, idx1, idx2, nodes):
        parentIdx1 = self.findParent(idx1, nodes)
        parentIdx2 = self.findParent(idx2, nodes)
        root = None
        if nodes[parentIdx1][SIZE] > nodes[parentIdx2][SIZE]:
            nodes[parentIdx2][PARENT] = parentIdx1
            nodes[parentIdx1][SIZE] += nodes[parentIdx2][SIZE]
            root = parentIdx2
        else:
            nodes[parentIdx1][PARENT] = parentIdx2
            nodes[parentIdx2][SIZE] += nodes[parentIdx1][SIZE]
            root = parentIdx1
        return root
    
    def isCycle(self, idx1, idx2, nodes):
        parentIdx1 = self.findParent(idx1, nodes)
        parentIdx2 = self.findParent(idx2, nodes)
        return parentIdx1 == parentIdx2
        
        
    def maxNumEdgesToRemove(self, n: int, edges: List[List[int]]) -> int:
        # [value, parent , size]
        commonNodes = [[i, 1] for i in range(n)]
        aliceNodes = [[i, 1] for i in range(n)]
        bobNodes = [[i, 1] for i in range(n)]
            
        uselessEdges = 0
        
        # first take care of Type 3 edges
        commonEdgesCount = 0
        for edge in edges:
            color, source, dest = edge
            if color == 3:
                if not self.isCycle(source - 1, dest - 1, commonNodes):
                    self.unite(source - 1, dest - 1, commonNodes)
                    self.unite(source - 1, dest - 1, aliceNodes)
                    self.unite(source - 1, dest - 1, bobNodes)
                    commonEdgesCount += 1
                else:
                    uselessEdges += 1
            
        # add the Alice and Bob nodes
        aliceEdgesCount = 0
        bobEdgesCount = 0
        for edge in edges:
            color, source, dest = edge
            if color == 1:
                if not self.isCycle(source - 1, dest - 1, aliceNodes):
                    self.unite(source - 1, dest - 1, aliceNodes)
                    aliceEdgesCount += 1
                else:
                    uselessEdges += 1
            elif color == 2:
                if not self.isCycle(source - 1, dest - 1, bobNodes):
                    self.unite(source - 1, dest - 1, bobNodes)
                    bobEdgesCount += 1
                else:
                    uselessEdges += 1
        
        if commonEdgesCount + aliceEdgesCount == len(commonNodes) - 1 and commonEdgesCount + bobEdgesCount == len(commonNodes) - 1:
            return uselessEdges
        return -1
                
        
