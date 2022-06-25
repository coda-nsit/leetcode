"""
for every number i in 1 to n, make it the root. Recursively solve 
(1, i - 1) => left subtree 
(i + 1, n) => right subtree
"""

class Solution:
    def numTreesInRange(self, start, end, dp):
        if (start, end) in dp:
            return dp[(start, end)]
        
        if start >= end:
            return 1
        
        # make i the root
        dp[(start, end)] = 0
        for i in range(start, end + 1):
            dp[(start, end)] += self.numTreesInRange(start, i - 1, dp) * self.numTreesInRange(i + 1, end, dp)
        
        return dp[(start, end)]
        
    def numTrees(self, n: int) -> int:
        dp = {}
        return self.numTreesInRange(1, n, dp)
