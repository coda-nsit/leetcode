"""
dp[i][j][k] = number of ways to reach i, j with k moves

start from all the boundary cells and terminate when startRow and startColumn is reached.
"""
from queue import Queue
MOD = 10 ** 9 + 7

class Solution:
    def findDfsPaths(self, m, n, x, y, move, maxMove, dp):
        if x < 0 or y < 0 or x >= m or y >= n or move < 0:
            return 0
        
        if (x, y, move) in dp:
            return dp[(x, y, move)]
        
        dp[(x, y, move)] = 0
        for i, j in [[-1, 0], [1, 0], [0, -1], [0, 1]]:
            dp[(x, y, move)] += self.findDfsPaths(m, n, x + i, y + j, move - 1, maxMove, dp)
        dp[(x, y, move)] %= MOD        
        return dp[(x, y, move)]
            
        
    def findPaths(self, m: int, n: int, maxMove: int, startRow: int, startColumn: int) -> int:
        dp = {}
        dp[(startRow, startColumn, 0)] = 1
        total = 0
        
        for move in range(maxMove):
            for i in range(m):
                self.findDfsPaths(m, n, i, 0, move, maxMove, dp)
                total += dp[(i, 0, move)]
            for i in range(m):
                self.findDfsPaths(m, n, i, n - 1, move, maxMove, dp)
                total += dp[(i, n - 1, move)]
            for i in range(n):
                self.findDfsPaths(m, n, 0, i, move, maxMove, dp)
                total += dp[(0, i, move)]
            for i in range(n):
                self.findDfsPaths(m, n, m - 1, i, move, maxMove, dp)
                total += dp[(m - 1, i, move)]
            total %= MOD
        # print(dp)
        return total
        
