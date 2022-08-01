class Solution:
    def __init__(self):
        self.maxi = 0
        
    def startBacktrack(self, x, y, nr, nc, grid, currTotal, visited):
        if x < 0 or y < 0 or x >= nr or y >= nc or grid[x][y] == 0 or (x, y) in visited:
            return
        currTotal += grid[x][y]
        visited.add((x, y))
        self.maxi = max(self.maxi, currTotal)
        for i, j in [[0, 1], [-1, 0], [0, -1], [1, 0]]:
            self.startBacktrack(x + i, y + j, nr, nc, grid, currTotal, visited)
        currTotal -= grid[x][y]
        visited.remove((x, y))
        
    def getMaximumGold(self, grid: List[List[int]]) -> int:
        nr, nc = len(grid), len(grid[0])
        visited = set()
        for i in range(nr):
            for j in range(nc):
                if grid[i][j] > 0:
                    self.startBacktrack(i, j, nr, nc, grid, 0, visited)
        return self.maxi
