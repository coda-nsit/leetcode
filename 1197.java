/**
Author: Rishab Banerjee
Algorithm: Just do standard BFS. Optimization is that since, coordinate is symmetric around (0,0) so just go to one quadrant.
 */
import java.util.*;

class Solution {
    public int minKnightMoves(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        Set<String> seen = new HashSet<>();
        int[] currentNode;
        int depth, i, j, currentX, currentY;
        
        // add starting position
        queue.add(new int[]{0, 0, 0});
        seen.add("0,0");

        int[][] directions = {
            {2, 1}, {1, 2}, {2, -1}, {1, -2},
            {-2, -1}, {-1, -2}, {-2, 1}, {-1, 2}
        };

        x = Math.abs(x);
        y = Math.abs(y);

        while (!queue.isEmpty()) {
            
            currentNode = queue.poll();
            currentX = currentNode[0];
            currentY = currentNode[1];
            depth = currentNode[2];

            if (currentX == x && currentY == y) {
                return depth;
            }

            for (int[] direction: directions) {
                i = Math.abs(currentX + direction[0]);
                j = Math.abs(currentY + direction[1]);

                String stringifiedCordinate = i + "," + j;
                if (!seen.contains(stringifiedCordinate)) {
                    queue.add(new int[]{i, j, depth + 1});
                    seen.add(stringifiedCordinate);
                }
            }
        }

        return -1; // should never happen
    }
}
