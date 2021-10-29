/*
1. Find the size of the existing islands.
2. for every cell in grid: if its 0 try to check if making it 1 connects other islands together using simple dfs
*/
class Cordinate {
    public int x;
    public int y;
    
    Cordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean equals (final Object O) {
        if (!(O instanceof Cordinate)) return false;
        if (((Cordinate) O).x != x) return false;
        if (((Cordinate) O).y != y) return false;
        return true;
    }
    
    public int hashCode() {
        return (x << 16) + y;
    }
    
}

class Solution {
    private int createUnitedNodes(int[][] grid, boolean[][] visited, int[][][] parents, int parentX, int parentY, int x, int y, int nr, int nc) {
        if (x < 0 || x >= nr || y < 0 || y >= nc || visited[x][y] == true || grid[x][y] == 0) {
            return 0;
        }
        visited[x][y] = true;
        parents[x][y][0] = parentX;
        parents[x][y][1] = parentY;
        
        return createUnitedNodes(grid, visited, parents, parentX, parentY, x - 1, y, nr, nc) + 
            createUnitedNodes(grid, visited, parents, parentX, parentY, x + 1, y, nr, nc) +
            createUnitedNodes(grid, visited, parents, parentX, parentY, x, y - 1, nr, nc) +
            createUnitedNodes(grid, visited, parents, parentX, parentY, x, y + 1, nr, nc) + 1;
    }
    
    private int unite(int[][] grid, int[][][] parents, boolean[][] visited, Map<Cordinate, Integer> islandSize, int x, int y, int nr, int nc) {
        Map<Cordinate, Integer> neighborSizes = new HashMap<>();
        // top
        if (x - 1 >= 0 && grid[x - 1][y] == 1) {
            Cordinate top = new Cordinate(parents[x - 1][y][0], parents[x - 1][y][1]);
            neighborSizes.put(top, islandSize.get(top));
        } 
        // bottom
        if (x + 1 < nr && grid[x + 1][y] == 1) {
            Cordinate bottom = new Cordinate(parents[x + 1][y][0], parents[x + 1][y][1]);
            neighborSizes.put(bottom, islandSize.get(bottom));
        } 
        // left
        if (y - 1 >= 0 && grid[x][y - 1] == 1) {
            Cordinate left = new Cordinate(parents[x][y - 1][0], parents[x][y - 1][1]);
            neighborSizes.put(left, islandSize.get(left));
        } 
        // right
        if (y + 1 < nc && grid[x][y + 1] == 1) {
            Cordinate right = new Cordinate(parents[x][y + 1][0], parents[x][y + 1][1]);
            neighborSizes.put(right, islandSize.get(right));
        }
        
        int total = 0;
        for (Cordinate cordinate: neighborSizes.keySet()) {
            total += neighborSizes.get(cordinate);
        }
        return total;
    }
    
    public int largestIsland(int[][] grid) {
        int nr, nc;
        nr = grid.length;
        nc = grid[0].length;
        int[][][] parents = new int[nr][nc][2];
        boolean[][] visited = new boolean[nr][nc];
        Map<Cordinate, Integer> islandSize = new HashMap<>();
        
        for (int i = 0; i < nr; ++i) {
            for (int j = 0; j < nc; ++j) {
                parents[i][j][0] = i;
                parents[i][j][1] = j;
                visited[i][j] = false;
            }
        }
        
        for (int i = 0; i < nr; ++i) {
            for (int j = 0; j < nc; ++j) {
                if(grid[i][j] == 1 && visited[i][j] == false) {
                    int currentIslandSize = createUnitedNodes(grid, visited, parents, i, j, i, j, nr, nc);
                    islandSize.put(new Cordinate(parents[i][j][0], parents[i][j][1]), currentIslandSize);
                }
            }
        }
        
        int maxSize = 0;
        for (int i = 0; i < nr; ++i) {
            for (int j = 0; j < nc; ++j) {
                if (grid[i][j] == 0) {
                    maxSize = Math.max(maxSize, unite(grid, parents, visited, islandSize, i, j, nr, nc) + 1);
                } else {
                    maxSize = Math.max(maxSize, islandSize.get(new Cordinate(parents[i][j][0], parents[i][j][1])));
                }
            }
        }
        return maxSize;
    }
}

// [[1,0],[0,1]]
// [[1,1],[1,0]]
// [[1,1],[1,1]]
