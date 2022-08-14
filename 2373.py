class Solution:
    def findMaxi(self, grid, x, y):
        # print(x, y)
        maxi = 0
        for i in range(x, x + 3):
            for j in range(y, y + 3):
                maxi = max(maxi, grid[i][j])
        return maxi
        
    def largestLocal(self, grid: List[List[int]]) -> List[List[int]]:
        nr, nc = len(grid), len(grid[0])
        ans = [[0] * (nc - 2) for i in range(nr - 2)]
        
        for i in range(nr):
            for j in range(nc):
                if i + 3 <= nr and j + 3 <= nc:
                    ans[i][j] = self.findMaxi(grid, i, j)
        return ans
