// loccapollo
class Grid {
    public int x;
    public int y;
    
    Grid(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    private static final int EMPTY = 0;
    private static final int BUILDING = 1;
    private static final int OBSTACLE = 2;
    
    private int shortestDistanceBfs(int[][] grid, int startX, int startY, int nr, int nc, int totalBuildingCellCount) {
        Queue<Grid> bfsQ = new LinkedList<>();
        int[][] distance = new int[nr][nc];
        int lastDistance, lastX, lastY, visitedBuidingCellCount, totalDistance;
        Grid lastCell;
        
        for (int i = 0; i < nr; ++i) {
            for (int j = 0; j < nc; ++j) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        
        distance[startX][startY] = 0;
        visitedBuidingCellCount = 0;
        bfsQ.add(new Grid(startX, startY));
        while (bfsQ.isEmpty() == false) {
            lastCell = bfsQ.poll();
            lastX = lastCell.x;
            lastY = lastCell.y;
            lastDistance = distance[lastX][lastY];
            if (grid[lastX][lastY] == EMPTY) {
                if (lastX + 1 < nr && distance[lastX + 1][lastY] == Integer.MAX_VALUE && grid[lastX + 1][lastY] != OBSTACLE) {
                    bfsQ.add(new Grid(lastX + 1, lastY));
                    distance[lastX + 1][lastY] = lastDistance + 1;
                }
                if (lastX - 1 >= 0 && distance[lastX - 1][lastY] == Integer.MAX_VALUE && grid[lastX - 1][lastY] != OBSTACLE) {
                    bfsQ.add(new Grid(lastX - 1, lastY));
                    distance[lastX - 1][lastY]  = lastDistance + 1;
                }
                if (lastY + 1 < nc && distance[lastX][lastY + 1] == Integer.MAX_VALUE && grid[lastX][lastY + 1] != OBSTACLE) {
                    bfsQ.add(new Grid(lastX, lastY + 1));
                    distance[lastX][lastY + 1] = lastDistance + 1;
                }
                if (lastY - 1 >= 0 && distance[lastX][lastY - 1] == Integer.MAX_VALUE && grid[lastX][lastY - 1] != OBSTACLE) {
                    bfsQ.add(new Grid(lastX, lastY - 1));
                    distance[lastX][lastY - 1] = lastDistance + 1;
                }
            } else {
                visitedBuidingCellCount += 1;
            }
        }
        
        totalDistance = 0;
        if (visitedBuidingCellCount < totalBuildingCellCount) {
            return Integer.MAX_VALUE;
        } else {
            for (int i = 0; i < nr; ++i) {
                for (int j = 0; j < nc; ++j) {
                    if (grid[i][j] == BUILDING) {
                        totalDistance += distance[i][j];
                    }
                }
            }
            return totalDistance;
        }
    }
    
    public int shortestDistance(int[][] grid) {
        int nr, nc, shortestDistanceValue, buildingCellCount;
        
        nr = grid.length;
        nc = grid[0].length;
        shortestDistanceValue = Integer.MAX_VALUE;
        
        buildingCellCount = 0;
        for (int i = 0; i < nr; ++i) {
            for (int j = 0; j < nc; ++j) {
                if (grid[i][j] == BUILDING) {
                    buildingCellCount += 1;
                }
            }
        }
        
        for (int i = 0; i < nr; ++i) {
            for (int j = 0; j < nc; ++j) {
                if (grid[i][j] == EMPTY) {
                    shortestDistanceValue = Math.min(shortestDistanceBfs(grid, i, j, nr, nc, buildingCellCount), shortestDistanceValue);
                }
            }
        }
        
        if (shortestDistanceValue == Integer.MAX_VALUE) {
            return -1;
        }
        return shortestDistanceValue;
    }
}
