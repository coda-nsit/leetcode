/**
Author: @Rishab Banerjee

Standard Kadane.
 */
class Solution {
    public int maxSubArray(int[] nums) {
        int currMax, maxi;
        boolean nonNegPresent;
        maxi = nums[0];
        nonNegPresent = false;
        currMax = 0;
        for (int num: nums) {
            if (num >= 0) {
                nonNegPresent = true;
            }
            currMax += num;
            if (currMax < 0) {
                currMax = 0;
            }
            if (nonNegPresent) {
                maxi = Math.max(currMax, maxi);
            } else {
                maxi = Math.max(num, maxi);
            }
        }
        return maxi;
    }
}