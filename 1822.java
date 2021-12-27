class Solution {
    public int arraySign(int[] nums) {
        int numNeg;
        numNeg = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < 0) {
                numNeg += 1;
            } else if (nums[i] == 0) {
                return 0;
            }
        }
        
        if (numNeg % 2 == 0) {
            return 1;
        }
        return -1;
    }
}
