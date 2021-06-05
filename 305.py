class Node:
    def __init__(self, val):
        self.val = val
        self.parent = self
        self.size = 1
        
    
class Solution:
    def unite(self, node1, node2):
        parent1, parent2 = self.findParent(node1), self.findParent(node2)        
        if parent1.size > parent2.size:
            parent2.parent = parent1
            parent2.size += parent1.size
        else:
            parent1.parent = parent2
            parent1.size += parent2.size
            
    def findParent(self, node):
        while node.parent != node:
            node = node.parent
        return node
        
    def numIslands2(self, m: int, n: int, positions: List[List[int]]) -> List[int]:
        islands = 0
        nodes = {}
        ones = set()
        for i in range(m):
            for j in range(n):
                nodes[(i, j)] = Node((i, j))
        answers = []
        for x, y in positions:
            if (x, y) in ones:
                answers.append(islands)
                continue
            ones.add((x, y))
            islands += 1
            for i, j in [(x - 1, y), (x, y + 1), (x + 1, y), (x, y - 1)]:
                if i >= 0 and j >= 0 and i < m and j < n and (i, j) in ones:
                    if self.findParent(nodes[(i, j)]) != self.findParent(nodes[(x, y)]):
                        self.unite(nodes[(i, j)], nodes[(x, y)])
                        islands -= 1
            answers.append(islands)
        return answers
