// Start from the border nodes which are 0 and make all the 0s that can be reached from these points useless
// now only those 0s are left which cant be accessed from the border 0s, so now do standard count number of islands
class Solution {
    private void neutralizeLeak(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == -1 || grid[x][y] == 1) {
            return;
        }
        grid[x][y] = -1;
        neutralizeLeak(grid, x - 1, y);
        neutralizeLeak(grid, x, y - 1);
        neutralizeLeak(grid, x + 1, y);
        neutralizeLeak(grid, x, y + 1);
        return;
    }
    
    public int closedIsland(int[][] grid) {
        for (int i = 0; i < grid.length; ++i) {
            if (grid[i][0] == 0) {
                neutralizeLeak(grid, i, 0);
            }
            if (grid[i][grid[0].length - 1] == 0) {
                neutralizeLeak(grid, i, grid[0].length - 1);
            }
        }
        
        for (int j = 0; j < grid[0].length; ++j) {
            if (grid[0][j] == 0) {
                neutralizeLeak(grid, 0, j);
            }
            if (grid[grid.length - 1][j] == 0) {
                neutralizeLeak(grid, grid.length - 1, j);
            }
        }
        
        int total = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 0) {
                    neutralizeLeak(grid, i, j);
                    total += 1; 
                }
            }
        }
        return total;
    }
}
