import java.lang.Math;
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numsSet = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            numsSet.add(nums[i]);
        }
        int consecutiveLength = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (numsSet.contains(nums[i])) {
                int maxi, mini;
                int currentNum = nums[i];
                while (numsSet.contains(currentNum)) {
                    currentNum += 1;
                }
                maxi = currentNum - 1;
                
                currentNum = nums[i];
                while (numsSet.contains(currentNum)) {
                    currentNum -= 1;
                }
                mini = currentNum + 1;
                
                for (int j = mini; j <= maxi; ++j) {
                    numsSet.remove(j);
                }
                consecutiveLength = Math.max(consecutiveLength, maxi - mini + 1);
            }
        }
        return consecutiveLength;
    }
}
