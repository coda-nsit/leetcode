// loccapollo
class Solution {
    private void traverseMatrixFromIndex(int[][] grid, int x, int y, StringBuilder currentPath, boolean[][] visited) {
        if (x - 1 >= 0 && grid[x - 1][y] == 1 && visited[x - 1][y] == false) {
            visited[x - 1][y] = true;
            currentPath.append('T');
            traverseMatrixFromIndex(grid, x - 1, y, currentPath, visited);
        }
        if (x + 1 < grid.length && grid[x + 1][y] == 1 && visited[x + 1][y] == false) {
            visited[x + 1][y] = true;
            currentPath.append('D');
            traverseMatrixFromIndex(grid, x + 1, y, currentPath, visited);
        }
        if (y - 1 >= 0 && grid[x][y - 1] == 1 && visited[x][y - 1] == false) {
            visited[x][y - 1] = true;
            currentPath.append('L');
            traverseMatrixFromIndex(grid, x, y - 1, currentPath, visited);
        }
        if (y + 1 < grid[0].length && grid[x][y + 1] == 1 && visited[x][y + 1] == false) {
            visited[x][y + 1] = true;
            currentPath.append('R');
            traverseMatrixFromIndex(grid, x, y + 1, currentPath, visited);
        }
        // this is needed to handle [[1,1,0],[0,1,1],[0,0,0],[1,1,1],[0,1,0]], i.e. capture info when moving back
        currentPath.append('0');
        return;
    }
    
    public int numDistinctIslands(int[][] grid) {
        Set<String> distinctPaths = new HashSet<>();
        int nr, nc;
        
        nr = grid.length;
        nc = grid[0].length;
        
        boolean[][] visited = new boolean[nr][nc];
        
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                if (grid[i][j] == 1 && visited[i][j] == false) {
                    StringBuilder currentPath = new StringBuilder();
                    // System.out.println(i + " " + j);
                    traverseMatrixFromIndex(grid, i, j, currentPath, visited);
                    distinctPaths.add(currentPath.toString());
                }
            }
        }
        return distinctPaths.size();
    }
}
