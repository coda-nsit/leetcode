import java.lang.Math;
class Solution {
    public boolean canJump(int[] nums) {
        int maximalCellVisited = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (maximalCellVisited < i) {
                return false;
            }
            maximalCellVisited = Math.max(maximalCellVisited, i + nums[i]);
        }
        return maximalCellVisited >= nums.length - 1;
    }
}
