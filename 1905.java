/*
Basic brute force solution
for all cells in grid2, if cell is 1, then start a simultaneous dfs traversal in grid1
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
    private boolean traverseTogether(int[][] grid1, int[][] grid2, int x, int y, Set<Cordinate> visited) {
        if (x < 0 || y < 0 || x >= grid1.length || y >= grid1[0].length || grid2[x][y] == 0) {
            return true;
        }
        if (grid2[x][y] == 1 && grid1[x][y] == 0) {
            return false;
        }
        Cordinate currentCordinate = new Cordinate(x, y);
        visited.add(currentCordinate);
        
        
        boolean top, left, bottom, right;
        top = left = bottom = right = true;
        if (visited.contains(new Cordinate(x - 1, y)) == false) {
            top = traverseTogether(grid1, grid2, x - 1, y, visited);
        }
        if (visited.contains(new Cordinate(x, y - 1)) == false) {
            left = traverseTogether(grid1, grid2, x, y - 1, visited);
        }
        if (visited.contains(new Cordinate(x + 1, y)) == false) {
            bottom = traverseTogether(grid1, grid2, x + 1, y, visited);
        }
        if (visited.contains(new Cordinate(x, y + 1)) == false) {
            right = traverseTogether(grid1, grid2, x, y + 1, visited);
        }
        
        return top && left && bottom && right;
    }
    
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int count = 0;
        Set<Cordinate> visited = new HashSet<>();
        for (int i = 0; i < grid2.length; ++i) {
            for (int j = 0; j < grid2[0].length; ++j) {
                if (grid2[i][j] == 1 && visited.contains(new Cordinate(i, j)) == false && traverseTogether(grid1, grid2, i, j, visited)) {
                    // System.out.println("new node: " + i + " " + j);
                    count += 1;
                }
            }
        }
        return count;
    }
}
